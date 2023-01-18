/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dto;

import java.util.Objects;

/**
 *
 * @author basile
 */
public class StopsPair {
    Integer id_line;
    Integer id_station;

    public StopsPair(Integer id_line, Integer id_station) {
        this.id_line = id_line;
        this.id_station = id_station;
    }
    
    

    public Integer getIdline() {
        return id_line;
    }

    public void setIdline(Integer id_line) {
        this.id_line = id_line;
    }

    public Integer getIdstation() {
        return id_station;
    }

    public void setIdstation(Integer id_station) {
        this.id_station = id_station;
    }

    @Override
    public String toString() {
        return id_line + ":" + id_station;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id_line);
        hash = 79 * hash + Objects.hashCode(this.id_station);
        return hash;
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
        final StopsPair other = (StopsPair) obj;
        if (!Objects.equals(this.id_line, other.id_line)) {
            return false;
        }
        if (!Objects.equals(this.id_station, other.id_station)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
