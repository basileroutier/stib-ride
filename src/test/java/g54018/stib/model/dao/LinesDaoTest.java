/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g54018.stib.model.dao;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.repository.exception.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import stib.app.App;

/**
 *
 * @author basile
 */
public class LinesDaoTest {
    
    private final LinesDto line;

    private static final int KEY = 1;
    private static final String url_db = "db.urltest";

    private final List<LinesDto> all;
    
    public LinesDaoTest() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("==== LinesDao Constructor =====");
        line = new LinesDto(KEY);

        all = new ArrayList<>();
        all.add(line);    
    }

    @Test
    public void testGetExist() throws Exception {
        LinesDao lineDao = new LinesDao(url_db);
        LinesDto lineResult = lineDao.get(KEY);
        LinesDto lineExcepted = line;
        assertEquals(lineExcepted, lineResult);
    }
    
    @Test
    public void testGetThrowException() throws Exception {
        LinesDao lineDao = new LinesDao(url_db);
        assertThrows(RepositoryException.class, ()->lineDao.get(null)); 
    }
    
    @Test
    public void testGetNotExist() throws Exception {
         LinesDao lineDao = new LinesDao(url_db);
        LinesDto lineResult = lineDao.get(2);
        assertNull(lineResult);
    }
    
    @Test
    public void testGetAll() throws Exception {
        LinesDao lineDao = new LinesDao(url_db);
        List<LinesDto> result = lineDao.getAll();
        assertEquals(all, result);
    }
    
}
