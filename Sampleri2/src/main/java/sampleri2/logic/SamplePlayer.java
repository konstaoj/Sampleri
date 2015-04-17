/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleri2.logic;

import sampleri2.aani.Sample;

/**
 *
 * @author Konsta
 */
public class SamplePlayer {
    private Sample g;
    private Sample h;
    
    public SamplePlayer(Sample g, Sample h) {
        this.g = g;
        this.h = h;
        
        this.g.start();
        this.h.start();
    }
    
    public void playSampleH() {
        if (h.buttonPressed) {
            h.buttonPressed = false;
        } else {
            h.buttonPressed = true;
        }
            
    }
    
    public void playSampleG() {
        if (g.buttonPressed) {
            g.buttonPressed = false;
        } else {
            g.buttonPressed = true;
        }
            
    }
    
}
