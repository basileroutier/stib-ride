/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.model;

import g54018.stib.model.dto.FavoritesDto;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.repository.FavoritesRepository;
import g54018.stib.model.repository.exception.RepositoryException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author basile
 */
public class Model {
    private StationSearchPath stationSearchPath;
    private FavoritesRepository favoriteRepo;
    
    private final PropertyChangeSupport pcs;
    
    public Model(StationSearchPath stationSearchPath) throws RepositoryException {
        pcs = new PropertyChangeSupport(this);
        this.stationSearchPath = stationSearchPath;
        stationSearchPath.setModel(this);
        favoriteRepo = new FavoritesRepository();
    }

    public Model() throws RepositoryException {
        pcs = new PropertyChangeSupport(this);
        this.stationSearchPath = new StationSearchPath();
        stationSearchPath.setModel(this);
        favoriteRepo = new FavoritesRepository();
    }
    
    public Set<Node> getListOfStation(){
        return stationSearchPath.getGraph().getNodes();
    }
    
    public List<FavoritesDto> getListOfFavorites(){
        try {
            return favoriteRepo.getAll();
        } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void rideToDestination(int origin, int destination) throws RepositoryException{
        stationSearchPath.searchPathToDestination(origin, destination);
    }
    
    public void rideToDestination(String origin, String destination) throws RepositoryException{
        stationSearchPath.searchPathToDestination(origin, destination);
    }
    
    public void insertFavorite(String name, StationsDto origin, StationsDto destination, int key){
        try {
            favoriteRepo.add(new FavoritesDto(name, origin, destination, key));
        } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertFavorite(String name, StationsDto origin, StationsDto destination){
        try {
            favoriteRepo.add(new FavoritesDto(name, origin, destination));
        } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteFavorite(FavoritesDto favorite) {
        try {
            favoriteRepo.remove(favorite.getKey());
        } catch (RepositoryException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add listener to the observator
     * @param listener PropertyChangeListener: the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove listener to the observator
     * @param listener PropertyChangeListener: the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Notify all the observator with a specific message and content
     * @param message String: message
     * @param objectSend Object: content to send
     */
    public void change(String property,Object objectSend) {
        pcs.firePropertyChange(property, null, objectSend);
    }

    

    
    
    
}
