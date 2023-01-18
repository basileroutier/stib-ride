/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.model.dto.Dto;
import g54018.stib.model.repository.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author basile
 */
public interface Repository<K, T extends Dto<K>> {
    public void add(T item) throws RepositoryException;
    public void remove(K key) throws RepositoryException;
    public T get(K key) throws RepositoryException;
    public List<T> getAll() throws RepositoryException;
    public boolean contains(K key) throws RepositoryException;
}
