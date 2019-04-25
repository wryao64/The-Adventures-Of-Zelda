package Controller;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

public class Sound {
    public static final String SOUND_LOCATION = "Assets/Sound/";

    public static void playSound(String soundLocation) {
        InputStream input;
        AudioStream stream = null;

        try {
            input = new FileInputStream(soundLocation);
            stream = new AudioStream(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(stream);
    }
}
