package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JdbcUtils {
    private static DataSource ds;

    static {
        try {
            Properties prop = new Properties();
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpConfig.properties");
            prop.load(in);
            BasicDataSourceFactory basicDataSourceFactory = new BasicDataSourceFactory();
            ds = basicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
