package com.Survive.main;


import javax.sound.sampled.AudioInputStream;
import java.applet.Applet;
import java.applet.AudioClip;



public class Sounds {



}
class dmgTaken
{
    private AudioClip clip1;
    public dmgTaken()
    {


        clip1 = Applet.newAudioClip(getClass().getClassLoader().getResource("dmg1.wav"));

    }

    public void play()
    {
        new Thread()
        {
            public void run()
            {
                clip1.play();
            }
        }.start();

    }
}

class backgroundMusic
{
    private AudioClip clip2;
    public backgroundMusic()
    {
        clip2 = Applet.newAudioClip(getClass().getClassLoader().getResource("Music.wav"));
    }

    public void play()
    {
        new Thread()
        {
            public void run()
            {

                clip2.loop();
            }
        }.start();
    }

    public void stop()
    {
        clip2.stop();
    }
}