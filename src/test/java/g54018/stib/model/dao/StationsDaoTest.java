/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g54018.stib.model.dao;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dto.StationsDto;
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
public class StationsDaoTest {
    
    private final StationsDto station;

    private static final int KEYNUM = 8014;
    private static final String KEYNAME = "MADOU";
    
    private static final String url_db = "db.urltest";
    
    private final List<StationsDto> all;
    
    public StationsDaoTest() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("==== StationsDao Constructor =====");
        station = new StationsDto(KEYNUM, KEYNAME);

        all = new ArrayList<>();
        all.add(station);    
    }

    @Test
    public void testGetExist() throws Exception {
        StationsDao stationsDao = new StationsDao(url_db);
        StationsDto stationResult = stationsDao.get(KEYNUM);
        StationsDto stationExcepted = station;
        assertEquals(stationResult, stationExcepted);
    }
    
    @Test
    public void testGetThrowException() throws Exception {
        StationsDao stationsDao = new StationsDao(url_db);
        assertThrows(RepositoryException.class, ()->stationsDao.get(null)); 
    }
    
    @Test
    public void testGetNotExist() throws Exception {
        StationsDao stationsDao = new StationsDao(url_db);
        StationsDto stationResult = stationsDao.get(0);
        assertNull(stationResult);
    }
    
    @Test
    public void testGetAll() throws Exception {
        StationsDao stationsDao = new StationsDao(url_db);
        List<StationsDto> result = stationsDao.getAll();
        assertEquals(all, result);
    }
    
}
