/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.jdbc;

import g54018.stib.model.repository.exception.RepositoryException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import g54018.stib.config.ConfigManager;

/**
 *
 * @author basile
 */
public class DBManager {

    private Connection connection;

    private final String url;

    private DBManager() {
        url = ConfigManager.getInstance().getProperties("db.url");
    }

    public Connection getConnection() throws RepositoryException {
        String jdbcUrl = "jdbc:sqlite:" + url;
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException ex) {
                throw new RepositoryException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    public static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    private static class DBManagerHolder {
        private static DBManager INSTANCE = new DBManager();
    }

}