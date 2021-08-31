package sound;

import org.junit.jupiter.api.Test;
import ui.sound.Sound;

public class SoundTest {

    @Test
    void testSound() {
        String filepath = "./data/Tower215bit.wav";

        Sound musicObj = new Sound(filepath);
        musicObj.playSoundContinuous();
        musicObj.playSound();

        String filepathWorks = "./data/Tower16bit.wav";
        Sound musicObj2 = new Sound(filepathWorks);
        musicObj2.playSound();
        musicObj2.playSoundContinuous();
    }

}
