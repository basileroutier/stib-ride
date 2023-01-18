/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package g54018.stib.model.repository;

import g54018.stib.config.ConfigManager;
import g54018.stib.model.dao.StationsDao;
import g54018.stib.model.dto.StationsDto;
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
public class StationsRepositoryTest {
    
    @Mock
    private StationsDao mock;
    
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
    
    private final StationsDto line;
    
    private final StationsDto lineNull;

    private static final int KEY = 8014;

    private final List<StationsDto> all;
    
    public StationsRepositoryTest() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("==== LinesDao Constructor =====");
        line = new StationsDto(KEY, "MADOU");
        lineNull = new StationsDto(0, "OUIOUI");

        all = new ArrayList<>();
        all.add(line);   
    }

    @org.junit.jupiter.api.Test
    public void testGetExist() throws Exception {
        // Arrange
        StationsRepository lineRepo = new StationsRepository(mock);
        
        // Action
        StationsDto lineResult = lineRepo.get(KEY);
        StationsDto lineExcepted = line;
        
        // Assert
        assertEquals(lineExcepted, lineResult);
        Mockito.verify(mock, times(1)).get(KEY);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetThrowException() throws Exception {
        // Arrange
        StationsRepository lineRepo = new StationsRepository(mock);
        
        // Action
        assertThrows(RepositoryException.class, ()->lineRepo.get(null)); 
        Mockito.verify(mock, times(1)).get(null);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetNotExist() throws Exception {
        // Arrange
        StationsRepository lineRepo = new StationsRepository(mock);
        
        // Action
        StationsDto lineResult = lineRepo.get(lineNull.getKey());
        
        // Assert
        assertNull(lineResult);
        Mockito.verify(mock, times(1)).get(lineNull.getKey());
    }
    
    @org.junit.jupiter.api.Test
    public void testContain() throws Exception {
        // Arrange
        StationsRepository lineRepo = new StationsRepository(mock);
        
        // Action
        boolean lineResult = lineRepo.contains(lineNull.getKey());
        
        // Assert
        assertFalse(lineResult);
        Mockito.verify(mock, times(1)).get(lineNull.getKey());
        Mockito.verify(mock, times(0)).getAll();
    }
    
    @org.junit.jupiter.api.Test
    public void testGetAll() throws Exception {
        // Arrange
        StationsRepository lineRepo = new StationsRepository(mock);
        
        // Action
        List<StationsDto> result = lineRepo.getAll();
        
        assertEquals(all, result);
        Mockito.verify(mock, times(0)).get(KEY);
        Mockito.verify(mock, times(1)).getAll();
    }
    
    
}
