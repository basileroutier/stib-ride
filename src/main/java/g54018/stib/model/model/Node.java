/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.model;

import g54018.stib.model.dto.StationsDto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author basile
 */
public class Node {

    private StationsDto station;

    private List<Node> shortestPath; //= new LinkedList<>();

    private Integer distance;// = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes;// = new HashMap<>();

    public Node(StationsDto station) {
        this.station = station;
        shortestPath = new LinkedList<>();
        distance = Integer.MAX_VALUE;
        adjacentNodes = new HashMap<>();
    }
    
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
    
    public void clearShortestPath(){
        shortestPath.clear();
    }
    
    public boolean containDestination(Node node){
        return adjacentNodes.containsKey(node);
    }

    public StationsDto getStation() {
        return station;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }
    
    public List<Node> getShortestPathToDestination() {
        if(!shortestPath.contains(this)){
            shortestPath.add(this);
        }
        return shortestPath;
    }
    

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.station, other.station)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+station.getName();
    }
    
    

}
