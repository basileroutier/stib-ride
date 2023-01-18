/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dao.LinesDao;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.repository.exception.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import stib.app.App;

/**
 *
 * @author basile
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class LinesRepositoryTest {
    
    @Mock
    private LinesDao mock;
    
    @BeforeEach
    void init() throws RepositoryException {
        System.out.println("==== BEFORE EACH =====");
        //Mock behaviour
        
        // SEULEMENT QUAND GET ET GET ALL
        Mockito.lenient().when(mock.get(line.getKey())).thenReturn(line);
        Mockito.lenient().when(mock.get(lineNull.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.getAll()).thenReturn(all);
        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
    }
    
    private final LinesDto line;
    
    private final LinesDto lineNull;

    private static final int KEY = 1;

    private final List<LinesDto> all;
    
    public LinesRepositoryTest() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("==== LinesDao Constructor =====");
        line = new LinesDto(KEY);
        lineNull = new LinesDto(0);

        all = new ArrayList<>();
        all.add(line);   
    }

    @org.junit.jupiter.api.Test
    public void testGetExist() throws Exception {
        // Arrange
        LinesRepository lineRepo = new LinesRepository(mock);
        
        // Action
        LinesDto lineResult = lineRepo.get(KEY);
        LinesDto lineExcepted = line;
        
        // Assert
        assertEquals(lineExcepted, lineResult);
        Mockito.verify(mock, times(1)).get(KEY);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetNotExist() throws Exception {
        // Arrange
        LinesRepository lineRepo = new LinesRepository(mock);
        
        // Action
        LinesDto lineResult = lineRepo.get(lineNull.getKey());
        
        // Assert
        assertNull(lineResult);
        Mockito.verify(mock, times(1)).get(lineNull.getKey());
    }
    
    @org.junit.jupiter.api.Test
    public void testNotContain() throws Exception {
        // Arrange
        LinesRepository lineRepo = new LinesRepository(mock);
        
        // Action
        boolean lineResult = lineRepo.contains(lineNull.getKey());
        
        // Assert
        assertFalse(lineResult);
        Mockito.verify(mock, times(1)).get(lineNull.getKey());
        Mockito.verify(mock, times(0)).getAll();
    }
    
    @org.junit.jupiter.api.Test
    public void testContain() throws Exception {
        // Arrange
        LinesRepository lineRepo = new LinesRepository(mock);
        
        // Action
        boolean lineResult = lineRepo.contains(line.getKey());
        
        // Assert
        assertTrue(lineResult);
        Mockito.verify(mock, times(1)).get(line.getKey());
        Mockito.verify(mock, times(0)).getAll();
    }
    
    @org.junit.jupiter.api.Test
    public void testGetAll() throws Exception {
        // Arrange
        LinesRepository lineRepo = new LinesRepository(mock);
        
        // Action
        List<LinesDto> result = lineRepo.getAll();
        
        assertEquals(all, result);
        Mockito.verify(mock, times(0)).get(KEY);
        Mockito.verify(mock, times(1)).getAll();
    }
    
    
    
}
