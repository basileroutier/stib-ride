/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dao;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dto.FavoritesDto;
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
public class FavoritesDao implements Dao<Integer, FavoritesDto> {

    private String url;

    public FavoritesDao(String dbUrl) {
        url = ConfigManager.getInstance().getProperties(dbUrl);
    }

    public FavoritesDao() {
        url = ConfigManager.getInstance().getProperties("db.url");
    }

    @Override
    public void insert(FavoritesDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }

        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "INSERT INTO FAVORITES(nom, origin, destination) VALUES (?,?,?)";
            PreparedStatement stmt = connexion.prepareStatement(query);
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getOrigin().getKey());
            stmt.setInt(3, item.getDestination().getKey());

            int count = stmt.executeUpdate();
            if (count <= 0) {
                System.out.println("Une erreur est arrivé lors de l'ajout d'un favoris");
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GET_PREPARE_FAVORITES | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }

        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "DELETE FROM FAVORITES where id=?";
            PreparedStatement stmt = connexion.prepareStatement(query);
            stmt.setInt(1, key);
            int count = stmt.executeUpdate();
            if (count <= 0) {
                System.out.println("Une erreur est arrivé lors de la suppression d'un favoris");
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GET_PREPARE_DELETE_FAVORITES | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public void update(FavoritesDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "UPDATE FAVORITES SET nom=?, origin=?, destination=? where id=?";
            PreparedStatement stmt = connexion.prepareStatement(query);
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getOrigin().getKey());
            stmt.setInt(3, item.getDestination().getKey());
            stmt.setInt(4, item.getKey());
            int count = stmt.executeUpdate();
            if (count <= 0) {
                System.out.println("Une erreur est arrivé lors de la mise à jour d'un favoris");
            }
            
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GET_PREPARE_UPDATE_FAVORITES | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public FavoritesDto get(Integer key) throws RepositoryException {
        FavoritesDto favorite = null;
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "select * from FAVORITES fav left join STATIONS sta on sta.id = fav.origin left join STATIONS stat on stat.id = fav.destination where fav.id=?";
            PreparedStatement stmt = connexion.prepareStatement(query);

            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Integer id = result.getInt(1);
                String name = result.getString(2);
                StationsDto origin = new StationsDto(result.getInt(5), result.getString(6));
                StationsDto destination = new StationsDto(result.getInt(7), result.getString(8));
                favorite = new FavoritesDto(name, origin, destination, id);
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GET_PREPARE_FAVORITES | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return favorite;
    }

    @Override
    public List<FavoritesDto> getAll() throws RepositoryException {
        List<FavoritesDto> listFavorites = new ArrayList<>();
        try {
            Connection connexion = DBManager.getInstance().getConnection();//DriverManager.getConnection("jdbc:sqlite:" + url);

            String query = "select * from FAVORITES fav left join STATIONS sta on sta.id = fav.origin left join STATIONS stat on stat.id = fav.destination";
            PreparedStatement stmt = connexion.prepareStatement(query);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Integer id = result.getInt(1);
                String name = result.getString(2);
                StationsDto origin = new StationsDto(result.getInt(5), result.getString(6));
                StationsDto destination = new StationsDto(result.getInt(7), result.getString(8));
                FavoritesDto favorite = new FavoritesDto(name, origin, destination, id);
                listFavorites.add(favorite);
            }
            //connexion.close();
        } catch (SQLException ex) {
            System.out.println("GETALL_PREPARE_FAVORITES | Erreur " + ex.getMessage() + " SQLState " + ex.getSQLState());
        }
        return listFavorites;
    }

}
