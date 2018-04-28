package by.kalinklish.project.dao.pool;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ResourceBundle;

public class PoolManagerTest {
    private static ResourceBundle resource;
    private static String user;
    private static String password;
    private static int poolSize;
    private static String url;
    private static String useUnicode;
    private static String encoding;

    @BeforeClass
    public static void init() {
        resource = ResourceBundle.getBundle("database");
        user = "root";
        password = "root";
        poolSize = 20;
        url = "jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false";
        useUnicode = "true";
        encoding = "UTF-8";
    }

    @Test
    public void testProperties() {
        Assert.assertEquals(user, resource.getString("db.user"));
        Assert.assertEquals(password, resource.getString("db.password"));
        Assert.assertEquals(poolSize, Integer.parseInt(resource.getString("db.poolSize")));
        Assert.assertEquals(url, resource.getString("db.url"));
        Assert.assertEquals(useUnicode, resource.getString("db.useUnicode"));
        Assert.assertEquals(encoding, resource.getString("db.encoding"));
    }


}
