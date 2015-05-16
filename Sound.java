/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 *
 * @author Spencer
 */
public class Sound {

    AudioClip myClip;

    public Sound(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                myClip = Applet.newAudioClip(file.toURI().toURL());
            } else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: malformed URL: " + e);
        }
    }

    public void play() {
        myClip.play();
    }
    
    public void loop() {
        myClip.loop();
    }
    
    public void stop() {
        myClip.stop();
    }
    
    
}
