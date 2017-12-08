package juliensjukebox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

import java.util.Random;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Playlist{
    
    // Set Objects
    final Random r = new Random();
    final String[] files;
    final Clip[] clips;
    LineListener listener;
    String list;
    
    
    public Playlist(String list) {
        
        this.list = list;
        files = new String[readAmount()];
        clips = new Clip[files.length];
        
        try {
            // Read file
            BufferedReader reader = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("juliensjukebox/resources/" + list).toURI())));
            String line;
            int index = 0;
            
            // Save names in array
            while((line = reader.readLine()) != null) {
                files[index] = line;
                index++;
            }
        } catch (Exception ex) { 
            System.out.println("Error: " + ex);
        }
        
        // Line Listener, necessary in case song ends another one plays
        listener = new LineListener() {
            
            
            int currentSong, lastSong = r.nextInt(files.length);

            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {

                    // Don't repeat a Song
                    do
                        currentSong = r.nextInt(files.length);
                    while(lastSong == currentSong);
                    lastSong = currentSong;

                    // Play clip
                    Clip currentClip = clips[currentSong];
                    currentClip.setFramePosition(0);
                    currentClip.start();
                }
            }
        };   
    }
    
    // Detect how many lines(i.e songs) there are
    public int readAmount() {
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(new File(getClass().getClassLoader().getResource("juliensjukebox/resources/" + list).toURI())));
            lnr.skip(Long.MAX_VALUE);
            return (lnr.getLineNumber()+1);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return 0;
    }
    
    // Open audio Files in Java
    public void openClips() {
        
        // Open clips of every music file in list
        for(int i = 0; i < files.length; i++) {
            try {
                clips[i] = AudioSystem.getClip();
                clips[i].open(AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("juliensjukebox/resources/audio/" + files[i])));
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
        
        // Add LineListeners to every clip
        for(int i = 0; i < clips.length; i++) {
            if(files[i] != null) {
                clips[i].addLineListener(listener);
            }
        }
    }
    
    // Start playing random song
    public void startPlaying() {
        openClips();
        clips[r.nextInt(files.length)].start();
    }
    
    // Stop playing
    public void stopPlaying() {
        for(int i = 0; i < clips.length; i++) {
            clips[i].stop();
            clips[i].close();
        }
    }
}