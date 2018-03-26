package by.kalinklish.project.dao.pool;

import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.PropertyKeys;
import by.kalinklish.project.exception.DAOException;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class PoolManager {
    private static final Logger LOGGER = LogManager.getLogger(PoolManager.class);
    private String url;
    int poolSize;
    private Properties properties;

    PoolManager() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(PropertyKeys.DB_NAME_BUNDLE);
            url = resource.getString(PropertyKeys.DB_URL);
            poolSize = Integer.parseInt(resource.getString(PropertyKeys.DB_POOL_SIZE));
            properties = new Properties();
            properties.put(ParameterConstants.PARAM_USER, resource.getString(PropertyKeys.DB_USER));
            properties.put(ParameterConstants.PARAM_PASSWORD, resource.getString(PropertyKeys.DB_PASSWORD));
            properties.put(ParameterConstants.PARAM_AUTO_RECONNECT, ParameterConstants.PARAM_TRUE);
            properties.put(ParameterConstants.PARAM_CHARACTER_ENCODING, resource.getString(PropertyKeys.DB_CHARACTER_ENCODING));
            properties.put(ParameterConstants.PARAM_USE_UNICODE, resource.getString(PropertyKeys.DB_USE_UNICODE));

        } catch (MissingResourceException e) {
            LOGGER.fatal( "Can not find properties file " + e.getMessage());
            throw new RuntimeException("Can not find properties file " + e.getMessage());
        }
    }

    ProxyConnection getConnection() throws DAOException {
        ProxyConnection proxyConnection;
        try {
            proxyConnection = new ProxyConnection(DriverManager.getConnection(url, properties));
        } catch (SQLException e) {
            throw new DAOException("Can not create connection " + e.getMessage());
        }
        return proxyConnection;
    }
}
