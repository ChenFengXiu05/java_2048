package tools;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPool extends Thread{
    public static final String Fozuxiao = "fozuxiao.wav";
    public static final String Muyu = "muyu.wav";
    public static final String Dabeizhou = "dabeizou.wav";



    public SoundPool(String Path)
    {
        try
        {
            File musicPath = new File(Path);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else
                System.out.println("不存在");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



    @Override
    public void run() {

    }
}
