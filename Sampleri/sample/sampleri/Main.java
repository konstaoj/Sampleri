package sampleri;

import java.io.*;
import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Sample untitled = new Sample("untitled.wav");
        Sample yksi = new Sample("1.wav");
        
        

        yksi.start();
        untitled.start();


        Scanner reader = new Scanner(System.in);
        while (true) {
            String stop = reader.nextLine();
            if (stop.equals("stop")) {
                
            }

        }
    }

}
