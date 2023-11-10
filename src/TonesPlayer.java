import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class TonesPlayer extends Thread {
    public float[] tones;
    private boolean shouldPlay = false;
    private boolean alive = true;

    final float SAMPLE_RATE;

    final private SourceDataLine sdl;

    public TonesPlayer(float sampleRates) throws LineUnavailableException {
        super();

        SAMPLE_RATE = sampleRates;

        AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
        sdl = AudioSystem.getSourceDataLine(af);
    }

    public void play()
    {
        shouldPlay = true;
    }

    public void pause()
    {
        shouldPlay = false;
        sdl.flush();
    }


    
    public void kill()
    {
        shouldPlay = false;
        alive = false;
        sdl.flush();
    }

    @Override
    public void run()
    {
        try {
            byte[] buf = new byte[1];
            sdl.open();
            sdl.start();

            long iter = 0;

            while (alive) {
                if (shouldPlay) {
                    double sample = 0;
                    for (float tone : tones)
                    {
                        double angle = iter / (SAMPLE_RATE / tone) * 2.0 * Math.PI;
                        sample += Math.sin( angle ) * 100 / tones.length;
                    }
                    buf[ 0 ] = (byte )sample;
                    sdl.write( buf, 0, 1 );
                    iter++;
                } else {
                    iter = 0;
                    sdl.flush();
                }
            }

            sdl.drain();
            sdl.close();
        } catch (Exception ignored) {}
    }
}
