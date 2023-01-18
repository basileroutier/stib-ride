/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stib.view;

import g54018.stib.model.dto.LinesDto;
import java.util.List;


/**
 *
 * @author basile
 */
public class ColumnParsingTable {
    private String stationTableColumn;
    private List<LinesDto> ligneTableColumn; 

    public ColumnParsingTable(String stationTableColumn, List<LinesDto> ligneTableColumn) {
        this.stationTableColumn = stationTableColumn;
        this.ligneTableColumn = ligneTableColumn;
    }

    public String getStationTableColumn() {
        return stationTableColumn;
    }

    public List<LinesDto> getLigneTableColumn() {
        return ligneTableColumn;
    }
    
    
    
}
