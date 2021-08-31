package ui.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

// Class for creating sound objects that play files from file path
public class Sound {
    String fileP;

    // EFFECTS: Constructs the object and takes in the given file path
    // to get the sound file
    public Sound(String filePath) {
        fileP = filePath;
    }

    /**
     * EFFECTS: Plays the given sound file infinitely until app stops
     */
    public void playSoundContinuous() {

        try {
            File soundPath = new File(fileP);

            if (soundPath.exists()) {

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                JOptionPane.showMessageDialog(null, "Play");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * EFFECTS: plays the sound file once
     */
    public void playSound() {

        try {
            File soundPath = new File(fileP);

            if (soundPath.exists()) {

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
