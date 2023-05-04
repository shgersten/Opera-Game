import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileInputStream; 
import java.io.File; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;




public class RunGame {
	public static void main(String[] args) throws IOException, InterruptedException
	{
		Game g = new Game();
		
        Thread music = new Thread() {
            public void run() {
                Clip clip;
                try {
                    AudioInputStream input = AudioSystem.getAudioInputStream(new File("/Users/shaynagersten/Desktop/Opera/claudio.wav"));
                    clip = AudioSystem.getClip();
                    clip.open(input);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        music.start();
        
		g.play();

		

	}

    
}
