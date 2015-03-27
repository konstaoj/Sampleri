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

public class Main {

    public static void main(String[] args) throws LineUnavailableException, URISyntaxException, InterruptedException {

        Sample untitled = new Sample("untitled.wav");
        Sample yksi = new Sample("1.wav");
        untitled.start(); 
        yksi.start();
        
        SampleControl untitledVol = new SampleControl(untitled);
        untitledVol.setVolume(1.0); //voluumi 0.0-1.0
        
        //scanneriin "a" ja "s" painamalla voidaan käynnistää sämplejä uudelleen
        //ja uudelleen alusta ja päällekäin, köyhänmiehen prototyyppikäyttöliittymä

        Scanner reader = new Scanner(System.in);
        while (true) {
            String stop = reader.nextLine();
            if (stop.equals("a")) {
                if (untitled.buttonPressed) {
                    untitled.buttonPressed = false;
                } else {
                    untitled.buttonPressed = true;
                }
            }
            
            if (stop.equals("s")) {
                if (yksi.buttonPressed) {
                    yksi.buttonPressed = false;
                } else {
                    yksi.buttonPressed = true;
                }
            }

        }

    }

}
