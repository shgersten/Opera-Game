

import java.io.File;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class musicLoop {

    public static void main(String[] args)
    {
        try{
            File audioFile = new File("claudio.wav").getAbsoluteFile();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //Plays audio once
            clip.start();
        }
        catch(Exception e)
        {;}
    }

    
}
    