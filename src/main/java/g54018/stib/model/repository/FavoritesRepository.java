/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.model.dao.FavoritesDao;
import g54018.stib.model.dao.LinesDao;
import g54018.stib.model.dao.StationsDao;
import g54018.stib.model.dao.StopsDao;
import g54018.stib.model.dto.FavoritesDto;
import g54018.stib.model.dto.StopsDto;
import g54018.stib.model.dto.StopsPair;
import g54018.stib.model.repository.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author basile
 */
public class FavoritesRepository implements Repository<Integer, FavoritesDto>{
    
    private FavoritesDao favoritesDao;

    public FavoritesRepository(FavoritesDao favoritesDao) {
        this.favoritesDao = favoritesDao;
    }

    public FavoritesRepository() throws RepositoryException {
        favoritesDao = new FavoritesDao();
    }

    @Override
    public void add(FavoritesDto item) throws RepositoryException {
         if(item.getKey()!=null && contains(item.getKey())){
            favoritesDao.update(item);
        }else{
            favoritesDao.insert(item);
        }
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        favoritesDao.delete(key);
    }

    @Override
    public FavoritesDto get(Integer key) throws RepositoryException {
        return favoritesDao.get(key);
    }

    @Override
    public List<FavoritesDto> getAll() throws RepositoryException {
        return favoritesDao.getAll();
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return favoritesDao.get(key)!=null;
    }

    
}
