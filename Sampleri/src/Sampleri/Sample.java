package Sampleri;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;

public class Sample extends JFrame implements Runnable {

    private Mixer mixer;
    private Clip clip;
    private String name;
    private Thread t;

    public Sample(String name) {
        this.name = name;

        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        this.mixer = AudioSystem.getMixer(mixInfos[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        try {
            this.clip = (Clip) mixer.getLine(dataInfo);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
            System.out.println("clip cannot be connected to a line");
        }
        
        loadSample();

    }

    public void loadSample() {

        try {

            URL soundURL = Main.class.getResource(this.name);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            this.clip.open(audioStream);

        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }

    }

    public void playSample() {
        this.clip.start();

        do {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
                //stop
            }

        } while (clip.isActive());
    }

    public void loopSample() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);

        do {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
                //stop
            }

        } while (clip.isActive());
    }

    @Override
    public void run() {
        if (true) {
            loopSample();
        } else {
            playSample();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, this.name);
            t.start();
        }

    }
    
    public void stop() {
        this.clip.stop();
        t.interrupt();
    }

}
