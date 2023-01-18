/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.dto;

/**
 *
 * @author basile
 */
public class LinesDto extends Dto<Integer>{
    
    public LinesDto(Integer key) {
        super(key);
    }
    
    @Override
    public String toString() {
        return super.getKey()+"";
    }
    
}
