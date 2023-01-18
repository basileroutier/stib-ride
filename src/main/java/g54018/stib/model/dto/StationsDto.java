/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dto;

/**
 *
 * @author basile
 */
public class StationsDto extends Dto<Integer>{
    String name;
    String nameNl;

    public StationsDto(Integer key, String name) {
        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.getKey() + ":"+name;
    }
    
}
