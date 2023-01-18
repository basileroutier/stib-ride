/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.model.dao.LinesDao;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.repository.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author basile
 */
public class LinesRepository implements Repository<Integer, LinesDto>{

    private LinesDao linesDao;

    public LinesRepository(LinesDao linesDao) {
        this.linesDao = linesDao;
    }

    public LinesRepository() {
        
    }
    
    

    @Override
    public LinesDto get(Integer key) throws RepositoryException {
        return linesDao.get(key);
    }

    @Override
    public List<LinesDto> getAll() throws RepositoryException {
        return linesDao.getAll();
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return linesDao.get(key)!=null;
    }

    @Override
    public void add(LinesDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
