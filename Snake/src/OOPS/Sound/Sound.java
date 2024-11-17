package OOPS.Sound;
import java.io.File;
import javax.sound.sampled.*;
/**
 * Classe responsável pelo som do jogo Snake
 *
 * @version 1.0 22/04/2024
 * @author Márcio Felício, Afonso Sousa, Miguel Rosa
 */
public class Sound {
    public void Sound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Snake/src/OOPS/Sound/audio.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); //Para repetir o som.
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
}