/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleri2.aani;

import java.net.URISyntaxException;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Konsta
 */
public class SampleControlTest {
    
    public SampleControlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void isVolumeControlAvailable() throws LineUnavailableException, URISyntaxException {
        Sample untitled = new Sample("1.wav", "1");
        assertTrue(untitled.getClip().isControlSupported((FloatControl.Type.MASTER_GAIN)));
        
    }
    
    @Test
    public void settingNewVolume() throws LineUnavailableException, URISyntaxException {
        Sample untitled = new Sample("1.wav", "untitled");
        FloatControl volControl = (FloatControl) untitled.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        float dB = 0;
        volControl.setValue(dB);
        int shouldBeZero = (int) volControl.getValue();
        assertEquals(0, shouldBeZero);
    }
}
