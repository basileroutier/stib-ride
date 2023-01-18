/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.model.dao.StationsDao;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.repository.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author basile
 */
public class StationsRepository implements Repository<Integer, StationsDto> {

    private StationsDao stationsDao;

    public StationsRepository() throws RepositoryException {
        stationsDao = new StationsDao();
    }

    public StationsRepository(StationsDao stationsDao) {
        this.stationsDao = stationsDao;
    }

    @Override
    public StationsDto get(Integer key) throws RepositoryException {
        return stationsDao.get(key);
    }

    @Override
    public List<StationsDto> getAll() throws RepositoryException {
        return stationsDao.getAll();
    }
    
    public List<LinesDto> getLine(Integer key) throws RepositoryException{
        return stationsDao.getLine(key);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return stationsDao.get(key)!=null;
    }

    @Override
    public void add(StationsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
