package SimpleSound;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * A simple sound player
 * 
 * @author Peyton Camman
 */
public class SoundPlayer {
	
	//Variables
	private Clip clip;
	private String SoundName;

	/**
	 * Creates a clip of a sound file
	 * @param name the name of the file located in SoundFiles (must end in .wav)
	 */
	public SoundPlayer (String name) {
		SoundName = name; 
		try {
			File file = new File("src/SimpleSound/SoundFiles/" + SoundName);
			URL url = file.toURI().toURL();
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			System.out.print("Sound caused: " + e + " error.");
		}
	}
	
	/**
	 * Play a specific sound clip
	 */
	public void play() {
		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(0);
		clip.start();
	}

	/** 
	 * Play a specific sound clip at a certain time frame
	 * @param time Time in milliseconds to start clip at
	 */
	public void playAt(int time) {
		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(time);
		clip.start();
	}
	
	/**
	 * Finds out whether the specific clip is playing
	 * @return true or false of whether sound is playing
	 */
	public boolean isPlaying() {
		return clip.isRunning();
	}

}
