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
public class LinesDao implements Dao<Integer, LinesDto>{
    
    String url;

    public LinesDao(String dbUrl) {
        url = ConfigManager.getInstance().getProperties(dbUrl);
    }

    public LinesDao() {
        url = ConfigManager.getInstance().getProperties("db.url");
    }

    @Override
    public LinesDto get(Integer key) throws RepositoryException {
        if(key==null){
            throw new RepositoryException("Le champ de la clé ne peut pas être null");
        }
        LinesDto lines = null;
        try{
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "SELECT * FROM LINES WHERE id = ?";
            PreparedStatement stmt = connexion.prepareStatement(query);

            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                Integer id = result.getInt("id");
                lines = new LinesDto(id);
            }
            
            //connexion.close();
        }catch(SQLException ex){
            System.out.println("GET_PREPARE_LINES | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return lines;
    }

    @Override
    public List<LinesDto> getAll() throws RepositoryException {
        List<LinesDto> listStations = new ArrayList<>();
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "SELECT * FROM LINES";
            PreparedStatement stmt = connexion.prepareStatement(query);

            ResultSet result = stmt.executeQuery();
            
            while(result.next()){
                Integer id = result.getInt("id");
                LinesDto station = new LinesDto(id);
                listStations.add(station);
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GETALL_PREPARE_STATIONS | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return listStations;
    }

    @Override
    public void insert(LinesDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LinesDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
