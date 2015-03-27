/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleri2.aani;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
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
public class SampleTest {

    public SampleTest() {
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

    //HUOM. useissa testeissä on vaikea käyttää muuta kuin if null rakennetta,
    //sillä testattavat "mixInfot" ym. riippuvat täysin käytettävästä koneesta
    //ja vertailua on täten hankala suorittaa
    @Test
    public void isMixerInfoAvailable() {
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        for (int i = 0; i < mixInfos.length; i++) {
            System.out.println(mixInfos[i]);
        }
        //prints mixer.info to see the devices
        assertTrue(mixInfos != null);
    }

    @Test
    public void settingUpMixer() {
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        Mixer testMixer = AudioSystem.getMixer(mixInfos[0]);

        assertTrue(testMixer != null);
    }

    @Test
    public void setUpDataLineForClip() {
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        assertTrue(dataInfo != null);
    }

    @Test
    public void creatingNewFileWorks() throws URISyntaxException {
        File soundFile = new File("C:\\Users\\Konsta\\Sampleri\\Sampleri2\\src\\main\\resources\\1.wav");
        assertTrue(soundFile.toString().equals("C:\\Users\\Konsta\\Sampleri\\Sampleri2\\src\\main\\resources\\1.wav"));
    }

    @Test
    public void testUnSupportedAudioFileException() throws IOException {
        File soundFile = new File("C:\\Users\\Konsta\\Sampleri\\Sampleri2\\src\\main\\resources\\1.wav");       
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException ex) {
            assertTrue(false);
        }
        
        assertTrue(true);
        
    }
    
    @Test
    public void isAudioInputStreamAvailable() throws UnsupportedAudioFileException, IOException {
        File soundFile = new File("C:\\Users\\Konsta\\Sampleri\\Sampleri2\\src\\main\\resources\\1.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        assertTrue(audioStream.getFrameLength() > 0);
    }
    
    @Test
    public void lineAvailableForClip() throws UnsupportedAudioFileException, IOException {
        File soundFile = new File("C:\\Users\\Konsta\\Sampleri\\Sampleri2\\src\\main\\resources\\1.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        Mixer mixer = AudioSystem.getMixer(mixInfos[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        
        Clip testClip;
        try {
            testClip = (Clip) mixer.getLine(dataInfo);
        } catch (LineUnavailableException ex) {
            assertTrue(false);
        }
        
        assertTrue(true);
        
        
        
    }
     
    @Test
    public void openingAudioStreamForClipWorks() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File soundFile = new File("C:\\Users\\Konsta\\Sampleri\\Sampleri2\\src\\main\\resources\\1.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        Mixer mixer = AudioSystem.getMixer(mixInfos[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        
        Clip testClip = (Clip) mixer.getLine(dataInfo);
        
        testClip.open(audioStream);
        assertTrue(testClip.isOpen());
        
        
    }
    
    @Test
    public void isSamplePlayable() throws LineUnavailableException, URISyntaxException {
        Sample untitled = new Sample("1.wav");
        untitled.start();
        untitled.buttonPressed = true;
        assertTrue(untitled.getClip().isActive());
        
    }
    
    @Test
    public void sampleStopIsWorking() throws LineUnavailableException, URISyntaxException, InterruptedException {
        Sample untitled = new Sample("1.wav");
        untitled.start();
        untitled.buttonPressed = true;
        Thread.sleep(100);
        untitled.buttonPressed = false;
        assertTrue(!untitled.getClip().isActive());
    }
    
    @Test
    public void isNewThreadCreated() throws LineUnavailableException, URISyntaxException {
        Sample untitled = new Sample("1.wav");
        untitled.start();
        assertFalse(untitled.getThread() == null);
    }
    
}
    