/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.model;

import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.repository.StationsRepository;
import g54018.stib.model.repository.exception.RepositoryException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author basile
 */
public class StationSearchPath {
    
    public static final String PROPERTY_SEARCH_TO_DESTINATION_FINISH = "model.StationsSearchPath.destinationFinish";
    
    private Model model;

    private Graph graph;

    private List<Node> shortestPath;

    public StationSearchPath() throws RepositoryException {
        graph = new Graph();
        shortestPath = new ArrayList<>();
    }

    public StationSearchPath(Graph graph) {
        this.graph = graph;
        shortestPath = new ArrayList<>();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void searchPathToDestination(int stationOriginal, int stationDestination) throws RepositoryException {
        if (graph.searchStation(stationOriginal) == null || graph.searchStation(stationDestination) == null) {
            throw new IllegalArgumentException("La station original ou la destination n'existe pas. Veuillez reessayer avec des stations valides");
        }
        new Thread(() -> {
            graph.clearAllShortestPath();
            Graph graphTmp = graph;
            Node nodeOriginal = graphTmp.searchStation(stationOriginal);
            
            graphTmp = Dijkstra.calculateShortestPathFromSource(graphTmp, nodeOriginal);
            shortestPath = new LinkedList<>(graphTmp.searchStation(stationDestination).getShortestPathToDestination());
            try {
                model.change(PROPERTY_SEARCH_TO_DESTINATION_FINISH, getListOfStation());
            } catch (RepositoryException ex) {
                Logger.getLogger(StationSearchPath.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public void searchPathToDestination(String stationOriginal, String stationDestination) throws RepositoryException {
        if (graph.searchStation(stationOriginal) == null || graph.searchStation(stationDestination) == null || stationOriginal == null || stationDestination == null) {
            throw new IllegalArgumentException("La station original ou la destination n'existe pas. Veuillez reessayer avec des stations valides");
        }
        new Thread(() -> {
            graph.clearAllShortestPath();
            Graph graphTmp = graph;
            Node nodeOriginal = graphTmp.searchStation(stationOriginal);
            graphTmp = Dijkstra.calculateShortestPathFromSource(graphTmp, nodeOriginal);
            shortestPath = new LinkedList<>(graphTmp.searchStation(stationDestination).getShortestPathToDestination());
            try {
                model.change(PROPERTY_SEARCH_TO_DESTINATION_FINISH, getListOfStation());
            } catch (RepositoryException ex) {
                Logger.getLogger(StationSearchPath.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
        
    }
   

    public List<Pair<StationsDto, List<LinesDto>>> getListOfStation() throws RepositoryException {
        List<Pair<StationsDto, List<LinesDto>>> linesByStation = new ArrayList<>();
        StationsRepository stationsRepo = new StationsRepository();
        for (Node node : shortestPath) {
            linesByStation.add(new Pair(node.getStation(), stationsRepo.getLine(node.getStation().getKey())));
        }
        return linesByStation;
    }
  
}
