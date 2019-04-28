package Controller;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

public class Sound {
    public static final String SOUND_LOCATION = "Resources/Assets/Sound/";
    private static AudioPlayer ap = AudioPlayer.player;
    private static AudioStream stream = null;

    public static void playSound(String soundLocation) {
        AudioPlayer ap = AudioPlayer.player;
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

        ap.start(stream);
    }

    public static void playBackgroundMusic() {
        InputStream input;

        try {
            input = new FileInputStream(SOUND_LOCATION + "background.wav");
            stream = new AudioStream(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ap.start(stream);
    }

    public static void stopBackgroundMusic() {
        if (stream != null) {
            ap.stop(stream);
        }
    }
}
