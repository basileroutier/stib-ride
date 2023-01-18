/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dto;

/**
 *
 * @author basile
 */
public class FavoritesDto extends Dto<Integer> {

    String name;
    StationsDto origin;
    StationsDto destination;

    public FavoritesDto(String name, StationsDto origin, StationsDto destination, Integer key) {
        super(key);
        this.name = name;
        this.origin = origin;
        this.destination = destination;
    }
    
    public FavoritesDto(String name, StationsDto origin, StationsDto destination) {
        super(0);
        this.name = name;
        this.origin = origin;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StationsDto getOrigin() {
        return origin;
    }

    public void setOrigin(StationsDto origin) {
        this.origin = origin;
    }

    public StationsDto getDestination() {
        return destination;
    }

    public void setDestination(StationsDto destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return super.getKey() + " - " + name + " - " + origin.getName() + " -> " + destination.getName();
    }

}
