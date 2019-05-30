package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteAnimation
{
    public int frameCount;
    public int frameDelay;
    public int currentFrame;
    public int animationDirection;
    public int totalFrames;
    public boolean loop;
    private boolean stopped;
    private List<AnimationFrame> frames = new ArrayList<>();
    
    public SpriteAnimation(BufferedImage[] frames, int frameDelay)
    {
        this.frameDelay = frameDelay;
        this.stopped = true;
        
        for (int i = 0; i < frames.length; i++)
        {
            addFrame(frames[i], frameDelay);
        }
        
        this.frameCount = 0;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = frames.length;
    }
    
    private void addFrame(BufferedImage frame, int duration)
    {
        if (duration <= 0)
        {
            System.err.println("Duration must be greater than zero.");
            throw new RuntimeException("Invalid duration: " + duration);
        }
        
        frames.add(new AnimationFrame(frame, duration));
        //currentFrame = 0;
    }
    
    public BufferedImage getSprite() 
    {
        return frames.get(currentFrame).getFrame();
    }
    
    public void update()
    {
        if (!stopped)
        {
            frameCount++;
            
            if (frameCount > frameDelay)
            {
                frameCount = 0;
                currentFrame += animationDirection;
                
                if (currentFrame > totalFrames - 1)
                {
                    currentFrame = 0;
                }
                else if (currentFrame < 0)
                {
                    currentFrame = totalFrames - 1;
                }
            }
        }
    }
    
    public void start()
    {
        if (!stopped || frames.isEmpty())
        {
            return;
        }
        
        stopped = false;
    }
    
    public void stop()
    {
        if (frames.isEmpty())
        {
            return;
        }
        
        stopped = true;
    }
    
    public void restart()
    {
        if (frames.isEmpty())
        {
            return;
        }
        
        stopped = false;
        currentFrame = 0;
    }
    
    public void reset()
    {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }
    
}
