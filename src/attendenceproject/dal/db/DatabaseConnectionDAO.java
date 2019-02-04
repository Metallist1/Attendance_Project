/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.dal.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;
import attendenceproject.dal.exceptions.daoException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author nedas
 */
public class DatabaseConnectionDAO {

    private final Properties configProp = new Properties();
    SQLServerDataSource ds;

    private DatabaseConnectionDAO() {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("data/loginInfo.properties");
        try {
            configProp.load(in);
            setConnection();
        } catch (IOException e) {
            try {
                throw new daoException(e.getMessage());
            } catch (daoException ex) {
                Logger.getLogger(ex.getMessage());
            }
        }
    }

    private static class LazyHolder {

        private static final DatabaseConnectionDAO INSTANCE = new DatabaseConnectionDAO();
    }

    public static DatabaseConnectionDAO getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

    private void setConnection() {
        this.ds = new SQLServerDataSource();
        ds.setDatabaseName(getInstance().getProperty("databaseName"));
        ds.setUser(getInstance().getProperty("userName"));
        ds.setPassword(getInstance().getProperty("password"));
        ds.setServerName(getInstance().getProperty("ip"));
        ds.setPortNumber(Integer.parseInt(getInstance().getProperty("port")));
    }
    public SQLServerDataSource getConnection(){
        return ds;
    }
}
