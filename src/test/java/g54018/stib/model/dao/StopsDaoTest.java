/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g54018.stib.model.dao;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.dto.StopsDto;
import g54018.stib.model.dto.StopsPair;
import g54018.stib.model.repository.exception.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import stib.app.App;

/**
 *
 * @author basile
 */
public class StopsDaoTest {
    
    private final StopsDto stop;

    private static final int KEY = 1;
    private static final String url_db = "db.urltest";

    private final List<StopsDto> all;
    
    
    public StopsDaoTest() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("==== StopsDao Constructor =====");
        stop = new StopsDto(1, 8014, 1, "MADOU");

        all = new ArrayList<>();
        all.add(stop);    
    }

    @Test
    public void testGetExist() throws Exception {
        StopsDao stopsDao = new StopsDao(url_db);
        StopsDto stopResult = stopsDao.get(new StopsPair(1, 8014));
        StopsDto stopExcepted = stop;
        assertEquals(stopResult, stopExcepted);
    }
    
    @Test
    public void testGetThrowException() throws Exception {
        StopsDao stopsDao = new StopsDao(url_db);
        assertThrows(RepositoryException.class, ()->stopsDao.get(null)); 
    }
    
    @Test
    public void testGetNotExist() throws Exception {
        StopsDao stopsDao = new StopsDao(url_db);
        StopsDto stopResult = stopsDao.get(new StopsPair(2,3));
        assertNull(stopResult);
    }
    
    @Test
    public void testGetAll() throws Exception {
        StopsDao stopsDao = new StopsDao(url_db);
        List<StopsDto> result = stopsDao.getAll();
        assertEquals(all, result);
    }
    
}
