package sampleri2.aani;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import sampleri2.gui.Interface;
import sampleri2.logic.SamplePlayer;

public class Main {
    
    public Sample g;

    public static void main(String[] args) throws LineUnavailableException, URISyntaxException, InterruptedException {
        Sample g = new Sample("untitled.wav", "g");
        Sample h = new Sample("1.wav", "h");                
        
        SamplePlayer player = new SamplePlayer(g,h);
        
        Interface gui = new Interface(player);
        gui.setVisible(true);
        
        
        
        
        
        
        
        //SampleControl untitledVol = new SampleControl(g);
        //untitledVol.setVolume(0.3); //voluumi 0.0-1.0
        
        //scanneriin "a" ja "s" painamalla voidaan käynnistää sämplejä uudelleen
        //ja uudelleen alusta ja päällekäin, köyhänmiehen prototyyppikäyttöliittymä

        Scanner reader = new Scanner(System.in);
        while (true) {
            String stop = reader.nextLine();
            if (stop.equals("a")) {
                if (g.buttonPressed) {
                    g.buttonPressed = false;
                } else {
                    g.buttonPressed = true;
                }
            }
            
            if (stop.equals("s")) {
                if (h.buttonPressed) {
                    h.buttonPressed = false;
                } else {
                    h.buttonPressed = true;
                }
            }
            

        }

    }

}
