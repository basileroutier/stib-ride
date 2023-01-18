/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dao;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dto.Dto;
import g54018.stib.model.dto.StopsDto;
import g54018.stib.model.dto.StopsPair;
import g54018.stib.model.jdbc.DBManager;
import g54018.stib.model.repository.exception.RepositoryException;
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
public class StopsDao implements Dao<StopsPair, StopsDto>{
    
    String url;

    public StopsDao() {
        url = ConfigManager.getInstance().getProperties("db.url");
    }
    
    public StopsDao(String dbUrl) {
        this.url = ConfigManager.getInstance().getProperties(dbUrl);
    }
    
    @Override
    public void insert(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(StopsPair key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StopsDto get(StopsPair key) throws RepositoryException {
        if(key==null ||key.getIdline()==null || key.getIdstation()==null){
            throw new RepositoryException("Les paires des stations ne peuvent pas être null");
        }
        StopsDto stop = null;
        try{
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url); 
            String query = "SELECT * FROM STOPS LEFT JOIN STATIONS as sta on sta.id = STOPS.id_station WHERE id_line = ? AND id_station = ? ";
            PreparedStatement stmt = connexion.prepareStatement(query);

            stmt.setInt(1, key.getIdline());
            stmt.setInt(2, key.getIdstation());

            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                Integer idOrder = result.getInt("id_order");
                String nameStation = result.getString("name");
                stop = new StopsDto(key.getIdline(), key.getIdstation(), idOrder, nameStation);
            }
            //connexion.close();
        }catch(SQLException ex){
            System.out.println("");
        }
        return stop;
    }

    @Override
    public List<StopsDto> getAll() throws RepositoryException {
        List<StopsDto> listStops = new ArrayList<>();
        try {
            Connection connexion = DBManager.getInstance().getConnection();

            String query = "SELECT * FROM STOPS LEFT JOIN STATIONS as sta on sta.id = STOPS.id_station LEFT JOIN LINES as lin on lin.id = STOPS.id_line ORDER BY id_line, id_order";
            PreparedStatement stmt = connexion.prepareStatement(query);

            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                Integer idOrder = result.getInt("id_order");
                Integer idLine = result.getInt("id_line");
                Integer idStation = result.getInt("id_station");
                String nameStation = result.getString("name");
                StopsDto stop = new StopsDto(idLine, idStation, idOrder, nameStation);
                listStops.add(stop);
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GETALL_PREPARE_STATIONS | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return listStops;
    }
    
}
