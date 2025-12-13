package controlador;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase para gestionar la reproduccion de musica de fondo
 */
public class ControladorMusica {
    private Clip clip;
    private boolean reproduciendo = false;

    /**
     * Reproduce un archivo de audio
     * @param rutaArchivo ruta al archivo de audio (WAV)
     */
    public void reproducir(String rutaArchivo) {
        try {
            // Detener musica actual si esta reproduciendose
            detener();

            // Cargar el archivo de audio
            File archivoAudio = new File(rutaArchivo);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoAudio);
            
            // Obtener el clip
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            // Reproducir en bucle infinito
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            
            reproduciendo = true;
            
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio no soportado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Línea de audio no disponible: " + e.getMessage());
        }
    }

    /**
     * Detiene la reproduccion actual
     */
    public void detener() {
        if (estaReproduciendo()) {
            clip.stop();
            clip.close();
            reproduciendo = false;
        }
    }

    /**
     * Ajusta el volumen (0.0 a 1.0)
     */
    public void ajustarVolumen(float volumen) {
        if (estaReproduciendo()) {
            try {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volumen) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            } catch (Exception e) {
                System.err.println("No se pudo ajustar el volumen: " + e.getMessage());
            }
        }
    }

    /**
     * Verifica si hay musica reproduciendose
     */
    public boolean estaReproduciendo() {
        return reproduciendo && clip != null && clip.isRunning();
    }
}