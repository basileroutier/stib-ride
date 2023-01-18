/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import g54018.stib.model.dto.FavoritesDto;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.model.Model;
import g54018.stib.model.model.StationSearchPath;
import g54018.stib.model.repository.exception.RepositoryException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javafx.application.Platform;
import javafx.util.Pair;
import stib.view.ColumnParsingTable;
import stib.view.MainViewController;

/**
 *
 * @author basile
 */
public class Presenter implements PropertyChangeListener{
    private Model model;
    private MainViewController view;
    
    public Presenter(Model model, MainViewController view) {
        this.model = model;
        this.view = view;
        initializeView();
    }

    private void initializeView() {
        view.fillSearchableComboBox(model.getListOfStation());
        view.fillFavoritesComboBox(model.getListOfFavorites());
    }

    public void searchPathTodestination() throws RepositoryException{
        
        if(view.getDestination() != null && !"".equals(view.getOrigin()) && !"".equals(view.getDestination()) && view.getOrigin()!=null){
            view.setSearchStatus("Recherche en cours");
            model.rideToDestination(view.getOrigin().getKey(), view.getDestination().getKey());
        }else{
            view.setSearchStatus("Recherche pas possible");
        }
    }
    
    public void addFavorite(){
        if(view.getDestination() != null  && view.getOrigin()!=null && !"".equals(view.getNameFavorite())){
            model.insertFavorite(view.getNameFavorite(), view.getOrigin(), view.getDestination());
            view.fillFavoritesComboBox(model.getListOfFavorites());
        }
    }
    
    public void modFavorite() {
         if(view.getDestination() != null && view.getOrigin()!=null && !"".equals(view.getNameFavorite()) && view.getFavorite()!=null){
            model.insertFavorite(view.getNameFavorite(), view.getOrigin(), view.getDestination(), view.getFavorite().getKey());
            view.fillFavoritesComboBox(model.getListOfFavorites());
         }
    }
    
    public void delFavorite(){
        if(view.getFavorite()!=null){
            model.deleteFavorite(view.getFavorite());
            view.fillFavoritesComboBox(model.getListOfFavorites());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String eventChange = evt.getPropertyName();
        Object infoTable;
        switch (eventChange) {
            case StationSearchPath.PROPERTY_SEARCH_TO_DESTINATION_FINISH:
                infoTable = evt.getNewValue();
                view.fillTableOfDestination((List<Pair<StationsDto, List<LinesDto>>>) infoTable);
                break;
            default:
                throw new IllegalArgumentException("Une erreur lors la récupération des informations de la recherche est apparue");
        }
    }

    
}
