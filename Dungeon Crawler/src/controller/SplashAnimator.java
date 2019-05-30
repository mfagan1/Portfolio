package controller;

import java.util.concurrent.TimeUnit;

public class SplashAnimator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 30;
    public AudioPlayer theme;
    private AudioPlayer accept;
    private long fadeStart;
    private long fadeEnd;
    private double volume = 5;

    // Animation for the Splash Screen.
    @Override
    public void run() {

        theme = new AudioPlayer("src/view/resources/Audio/theme.mp3", volume);
        accept = new AudioPlayer("src/view/resources/Audio/start.wav", 1.0);

        theme.play();

        while (running) {
            long startTime = System.currentTimeMillis();

            Main.splashPanel.gameRender();
            Main.splashPanel.printScreen();

            // Checks to see if song is still playing
            // and if the current frame is fading out.
            // If the song is finished we want to replay it.
            if (!theme.poll() && !GameStaticState.isFading())
            {
                theme.play();
            }

            // Checks for enter key pressed to
            // start game. If the enter key is
            // pressed the theme song is stopped
            // and the accept tone is played.
            // Also sets the "isFading" property
            // to true. This begins the fade out
            // transition of the JPanel.
            if(!GameStaticState.isPaused() && !GameStaticState.isFading())
            {
                fadeStart = System.currentTimeMillis();
                fadeEnd = 1500;
                GameStaticState.setIsFading(true);
                theme.stop();
                accept.play();
            }

            if(GameStaticState.isFading() && (System.currentTimeMillis() > fadeStart + fadeEnd))
            {
                GameStaticState.setIsFading(false);
                break;
            }

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}