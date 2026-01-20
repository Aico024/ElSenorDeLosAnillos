package controlador;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase para gestionar la reproducción de música de fondo.
 *
 * *<p>Permite cargar archivos de audio en formato WAV, reproducirlos en bucle,
 * detenerlos y ajustar el volumen de salida.</p>
 */
public class ControladorMusica {
    private Clip clip;
    private boolean reproduciendo = false;

    /**
     * Reproduce un archivo de audio.
     *
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
     * Detiene la reproducción actual y libera los recursos del clip.
     */
    public void detener() {
        if (estaReproduciendo()) {
            clip.stop();
            clip.close();
            reproduciendo = false;
        }
    }

    /**
     * Ajusta el volumen de la reproducción actual.
     *
     * @param volumen Nivel de volumen expresado en un rango de 0.0 (silencio) a 1.0 (máximo).
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
     * Indica si hay un audio reproduciéndose actualmente.
     *
     * @return true si el clip está activo, false en caso contrario.
     */
    public boolean estaReproduciendo() {
        return reproduciendo && clip != null && clip.isRunning();
    }
}