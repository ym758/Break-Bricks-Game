import javax.sound.sampled.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class Sound {

    public static void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error playing sound: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        playSound("countdown.wav");
    }
}
