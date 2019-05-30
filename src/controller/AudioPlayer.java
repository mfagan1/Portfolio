package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Wrapper for MediaPlayer class.
 */
public class AudioPlayer
{
    private MediaPlayer mediaPlayer;
    private double volume;
    private String bip;
    private Media file;

    public AudioPlayer(String fileName, double volume)
    {
        this.bip = fileName;
        this.file = new Media(new File(bip).toURI().toString());
        this.volume = volume;
    }

    /**
     * Handles creation of MediaPlayer object and
     * plays audio.
     */
    public void play()
    {
        this.mediaPlayer = new MediaPlayer(file);
        this.mediaPlayer.setVolume(volume);

        // Listener for end of media.
        mediaPlayer.setOnEndOfMedia(() -> {
            this.mediaPlayer.stop();
        });

        mediaPlayer.play();
    }

    /**
     * Stops playing of media.
     */
    public void stop()
    {
        if (this.mediaPlayer != null && mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
        {
            this.mediaPlayer.stop();
        }
    }

    /**
     * Checks status of the media.
     * @return Returns true if audio is playing. Else, false.
     */
    public boolean poll()
    {
        if (this.mediaPlayer == null)
        {
            return false;
        }

        return mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING);
    }

    /**
     * Sets the volume for the player.
     * @param volume
     */
    public void setVolume(double volume)
    {
        if (this.mediaPlayer == null)
        {
            return;
        }

        this.mediaPlayer.setVolume(volume);
    }
}
