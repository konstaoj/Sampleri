package sampleri2.aani;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.EnumControl;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sample implements Runnable {

    private Mixer mixer;
    private Clip clip;
    private String name;
    private Thread t;
    public boolean buttonPressed;

    public Sample(String name) throws LineUnavailableException, URISyntaxException {
        this.name = name;
        buttonPressed = false;

        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        this.mixer = AudioSystem.getMixer(mixInfos[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        try {
            this.clip = (Clip) mixer.getLine(dataInfo);
        } catch (LineUnavailableException lue) {
            System.out.println("clip cannot be connected to a line");
        }
        loadSample();

    }

    private void loadSample() throws URISyntaxException {

        try {

            File soundFile = new File(this.getClass().getResource("/" + this.name).toURI());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            this.clip.open(audioStream);

        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }

    }

    private void playSample() {
        if (buttonPressed) {
        this.clip.setFramePosition(0);
        this.clip.start();
        buttonPressed = false;
        }
        
        while (true) {
            if (buttonPressed) {                
                playSample();

            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sample.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void loopSample() {
        this.clip.setLoopPoints(0, this.clip.getFrameLength() / 20);
        //this.clip.setLoopPoints(0, -1);
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
        playSample();
    }

    public void start() {
        if (this.t == null) {
            this.t = new Thread(this, this.name);
            this.t.start();
        } else {
            t.start();
        }

    }
    
    public Clip getClip() {
        return this.clip;
    }
    
    public Thread getThread() {
        return this.t;
    }
    
    public void stop() {
        this.clip.stop();
        //t.interrupt();
    }

}
