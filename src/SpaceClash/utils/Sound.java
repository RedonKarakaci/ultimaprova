package SpaceClash.utils;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {

    //---------------------------------------------------------------
    // STATIC ATTRIBUTE
    //---------------------------------------------------------------
    private static Sound instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTE
    //---------------------------------------------------------------

    private Clip clipSoundtrack;
    private Clip clipExplosion;
    private Clip clipShotBullet;
    private Clip clipExplosionBeam;
    private boolean stopAll=false;

    private Sound(){
        try {
            SetFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------
    // PRIVATE INSTANCE METHODS
    //---------------------------------------------------------------
    private void SetFile() throws IOException, UnsupportedAudioFileException {
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/soundtrack.wav"));
            clipSoundtrack = AudioSystem.getClip();
            clipSoundtrack.open(sound);

            AudioInputStream sound1 = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/explosionBullet.wav"));
            clipExplosion = AudioSystem.getClip();
            clipExplosion.open(sound1);

            AudioInputStream sound2 = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/shotBullet.wav"));
            clipShotBullet = AudioSystem.getClip();
            clipShotBullet.open(sound2);

            AudioInputStream sound3 = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/explosionBeam.wav"));
            clipExplosionBeam = AudioSystem.getClip();
            clipExplosionBeam.open(sound3);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------
    public void playSoundtrack(){
        clipSoundtrack.setFramePosition(0);
        clipSoundtrack.start();
        clipSoundtrack.loop(clipSoundtrack.LOOP_CONTINUOUSLY);
    }

    public void stopSoundtrack(){
        clipSoundtrack.setFramePosition(0);
        clipSoundtrack.stop();
    }

    public void playExplosionBullet(){
        clipExplosion.setFramePosition(0);
        clipExplosion.start();
    }

    public void stopExplosionBullet(){
        clipExplosion.setFramePosition(0);
        clipExplosion.stop();
    }

    public void playShotBullet(){
        clipShotBullet.setFramePosition(0);
        clipShotBullet.start();
    }

    public void stopShotBullet(){
        clipShotBullet.setFramePosition(0);
        clipShotBullet.stop();
    }

    public void playExplosionBeam(){
        clipExplosionBeam.setFramePosition(0);
        clipExplosionBeam.start();
    }

    public void stopExplosionBeam(){
        clipExplosionBeam.setFramePosition(0);
        clipExplosionBeam.stop();
    }

    public void startAllSound(){
        playSoundtrack();
        stopAll=false;
    }

    public void stopAllSound(){
        stopSoundtrack();
        stopExplosionBullet();
        stopShotBullet();
        stopExplosionBeam();
        stopAll=true;
    }

    public boolean isStopAll() {
        return stopAll;
    }

    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static Sound getInstance() {
        if (instance == null)
            instance = new Sound();
        return instance;
    }
}
