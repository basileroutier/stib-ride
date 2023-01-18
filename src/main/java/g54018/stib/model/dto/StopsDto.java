/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dto;

/**
 *
 * @author basile
 */
public class StopsDto extends Dto<StopsPair>{
    LinesDto lines;
    StationsDto stations;
    Integer id_order;

    public StopsDto(Integer idLine, Integer idStation ,Integer idOrder, String nameStation) {
        super(new StopsPair(idLine, idStation));
        id_order = idOrder;
        lines = new LinesDto(idLine);
        stations = new StationsDto(idStation, nameStation);
    }

    public Integer getIdOrder() {
        return id_order;
    }

    public void setIdOrder(Integer id_order) {
        this.id_order = id_order;
    }

    public LinesDto getLines() {
        return lines;
    }

    public void setLines(LinesDto lines) {
        this.lines = lines;
    }

    public StationsDto getStations() {
        return stations;
    }

    public void setStations(StationsDto stations) {
        this.stations = stations;
    }
    
    @Override
    public String toString() {
        return super.getKey()+":"+id_order;
    }
    
    
    
}
