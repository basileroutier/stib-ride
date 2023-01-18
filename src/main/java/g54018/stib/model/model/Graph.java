/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.model;

import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.dto.StopsDto;
import g54018.stib.model.repository.StationsRepository;
import g54018.stib.model.repository.StopsRepository;
import g54018.stib.model.repository.exception.RepositoryException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author basile
 */
public class Graph {
    private Set<Node> nodes;

    public Graph() throws RepositoryException {
        nodes = new HashSet<>();
        initGraph();
        initDestination();
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }
    
    public Node searchStation(int idStation){
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            if ((idStation == node.getStation().getKey())) {
                return node;
            }
        }
        return null;
    }
    
    public Node searchStation(String nameStation){
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            if ((nameStation.equals(node.getStation().getName()))) {
                return node;
            }
        }
        return null;
    }
    
    public void clearAllShortestPath(){
        for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
            Node node = it.next();
            node.clearShortestPath();
            node.setDistance(Integer.MAX_VALUE);
        }
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
    
    private void initGraph() throws RepositoryException {
        StationsRepository stationsRepo = new StationsRepository();
        List<StationsDto> stations = stationsRepo.getAll();
        for (StationsDto station : stations) {
            addNode(new Node(station));
        }
    }

    private void initDestination() throws RepositoryException {
        StopsRepository stopsRepo = new StopsRepository();
        List<StopsDto> stops = stopsRepo.getAll();

        for (int i = 0; i < stops.size() - 1; i++) {
            int lineOriginal = stops.get(i).getLines().getKey();
            int lineDesti = stops.get(i).getLines().getKey();
            int orderOriginal = stops.get(i).getIdOrder();
            int orderDesti = stops.get(i + 1).getIdOrder();
            if (orderOriginal == orderDesti-1 && lineOriginal==lineDesti) {
                Integer station = stops.get(i).getStations().getKey();
                Integer stationDesti = stops.get(i + 1).getStations().getKey();

                addDestination(station, stationDesti);
            }
        }
    }

    private void addDestination(int stationOriginal, int stationDestination) {
        Node nodeOri = searchStation(stationOriginal);
        Node nodeDesti = searchStation(stationDestination);
        if(!nodeOri.containDestination(nodeDesti)){
            nodeOri.addDestination(nodeDesti, 1);
        }
        if(!nodeDesti.containDestination(nodeOri)){
            nodeDesti.addDestination(nodeOri, 1);
        }
    }
  
}
