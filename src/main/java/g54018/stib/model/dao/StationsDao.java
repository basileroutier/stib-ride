/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dao;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.jdbc.DBManager;
import g54018.stib.model.repository.exception.RepositoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basile
 */
public class StationsDao implements Dao<Integer, StationsDto>{
    
    private String url;

    public StationsDao(String dbUrl) {
        url = ConfigManager.getInstance().getProperties(dbUrl);
    }

    public StationsDao() {
        url = ConfigManager.getInstance().getProperties("db.url");
    }

    @Override
    public void insert(StationsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(StationsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
        if(key==null){
            throw new RepositoryException("Le champ de la clé ne peut pas être null");
        }
        StationsDto station = null;
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "SELECT * FROM STATIONS WHERE id = ?";
            PreparedStatement stmt = connexion.prepareStatement(query);

            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                Integer id = result.getInt("id");
                String name = result.getString("name");
                station = new StationsDto(id, name);
            }
            ///connexion.close();
        } catch (SQLException ex) {
            System.out.println("GET_PREPARE_STATIONS | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        
        return station;
    }

    @Override
    public List<StationsDto> getAll() throws RepositoryException {
        List<StationsDto> listStations = new ArrayList<>();
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "SELECT * FROM STATIONS";
            PreparedStatement stmt = connexion.prepareStatement(query);

            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                Integer id = result.getInt("id");
                String name = result.getString("name");
                StationsDto station = new StationsDto(id, name);
                listStations.add(station);
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GETALL_PREPARE_STATIONS | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return listStations;
    }
    
    public List<LinesDto> getLine(Integer key) throws RepositoryException{
        List<LinesDto> lines =null;
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "SELECT id_line FROM STATIONS LEFT JOIN STOPS as stop on STATIONS.id = stop.id_station LEFT JOIN LINES as lin on lin.id = stop.id_line WHERE id_station=? ORDER BY id_line";
            PreparedStatement stmt = connexion.prepareStatement(query);

            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();
            lines = new ArrayList<>();
            while(result.next()){
                Integer id = result.getInt("id_line");
                lines.add(new LinesDto(id));
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GET_PREPARE_STATIONS | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return lines;
    }
    
}
