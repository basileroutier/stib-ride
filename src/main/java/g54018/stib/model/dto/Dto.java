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
public class Dto<K> {
    protected K key;
    
    /**
     * Creates a new instance of <code>Dto</code> with the key of the data.
     *
     * @param key key of the data.
     */
    protected Dto(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Clé absente " + key);
        }
        this.key = key;
    }

    
    public K getKey(){
        return key;
    }
    

      @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.key);
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
        if(getClass()!=obj.getClass()){
            return false;
        }
        final Dto<?> other = (Dto<?>) obj;
        return Objects.equals(this.key, other.key);
    }
}
