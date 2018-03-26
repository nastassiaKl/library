package by.kalinklish.project.dao.pool;

import by.kalinklish.project.exception.DAOException;
import by.radomskaya.project.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static AtomicBoolean flag = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> connections;
    private PoolManager manager;

    public ConnectionPool() throws DAOException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            init();
        } catch (SQLException e) {
            LOGGER.fatal(e + " DriverManager wasn't found.");
        }
    }

    private void init() throws DAOException {
        manager = new PoolManager();
        connections = new ArrayBlockingQueue<>(manager.poolSize);

        try {
            for (int i = 0; i < manager.poolSize; i++) {
                connections.put(manager.getConnection());
            }
            if (connections.isEmpty()) {
                LOGGER.fatal("Can not create database connection pool");
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }


    public static ConnectionPool getInstance() {
        if (!flag.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    flag.set(true);
                }
            } catch (DAOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.error("Can not take connection from pool: " + e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        connections.offer(connection);
    }

    @Override
    protected void finalize() throws Throwable {
        closePool();
    }

    public void closePool() throws DAOException {
        for (ProxyConnection connection : connections) {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                LOGGER.error("Can not close pool: " + e);
            }
        }
    }
}
