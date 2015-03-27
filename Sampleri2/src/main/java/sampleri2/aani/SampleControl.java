/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleri2.aani;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.sound.sampled.EnumControl;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Konsta
 */
public class SampleControl {

    private Thread t;
    private Sample sample;
    private String name;

    public SampleControl(Sample sample) {
        this.sample = sample;
        this.name = "moro";
    }

    public void setVolume(double gain) {

        FloatControl volControl = (FloatControl) this.sample.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        //double gain controls volume (use value between 0.0-1.0) 
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        volControl.setValue(dB);
    }

//    @Override
//    public void run() {
//        volSweep();
//    }
//
//    public void start() {
//        if (t == null) {
//            t = new Thread(this, this.name);
//            t.start();
//        }
//    }

//    public void volSweep() {
//        FloatControl volControl = (FloatControl) this.sample.getClip().getControl(FloatControl.Type.MASTER_GAIN);
//        for (int i = 0; i <= 100; i++) {
////            float v = (float) (i / 10000);
//            volControl.setValue((float) 0);
//
////            try {
////                Thread.sleep(10);
////            } catch (InterruptedException ex) {
////                System.out.println("lool");
////            }
//
//        }
//    }

}
