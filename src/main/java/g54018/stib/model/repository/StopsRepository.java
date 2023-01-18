/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.model.dao.LinesDao;
import g54018.stib.model.dao.StationsDao;
import g54018.stib.model.dao.StopsDao;
import g54018.stib.model.dto.StopsDto;
import g54018.stib.model.dto.StopsPair;
import g54018.stib.model.repository.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author basile
 */
public class StopsRepository implements Repository<StopsPair, StopsDto>{
    
    private StopsDao stopsDao;
//    private LinesDao linesDao;
//    private StationsDao stationsDao;

    public StopsRepository(StopsDao stopsDao) {
        this.stopsDao = stopsDao;
    }

    public StopsRepository() throws RepositoryException {
        stopsDao = new StopsDao();
    }

    @Override
    public StopsDto get(StopsPair key) throws RepositoryException {
        return stopsDao.get(key);
    }

    @Override
    public List<StopsDto> getAll() throws RepositoryException {
        return stopsDao.getAll();
    }

    @Override
    public boolean contains(StopsPair key) throws RepositoryException {
        return stopsDao.get(key)!=null;
    }

    @Override
    public void add(StopsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(StopsPair key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
