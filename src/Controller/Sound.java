package Controller;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.*;

public class Sound {
    public static final String SOUND_LOCATION = "Assets/Sound/";

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
        AudioPlayer ap = AudioPlayer.player;
        InputStream input;
        AudioStream stream = null;
        ContinuousAudioDataStream loop = null;

        try {
            input = new FileInputStream(SOUND_LOCATION + "background.wav");
            stream = new AudioStream(input);
//            AudioData data = stream.getData();
//            loop = new ContinuousAudioDataStream(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ap.start(stream);
    }
}
