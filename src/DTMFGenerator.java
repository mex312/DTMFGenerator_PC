import javax.sound.sampled.LineUnavailableException;
import java.util.concurrent.TimeUnit;

public class DTMFGenerator {

    private final static float[] LEAST = new float[] {1209, 1336, 1477, 1633};
    private final static float[] MOST = new float[] {697, 770, 852, 941};
    private final static String SYMBOLS = "123A456B789C*0#D";

    private class Actor extends Thread
    {
        private final String toPlay;

        public Actor(String toPlay)
        {
            this.toPlay = toPlay;
        }

        @Override
        public void run()
        {
            try { for (char c : toPlay.toCharArray()) {
                player.tones = getTonesFor(c);
                player.play();
                TimeUnit.MILLISECONDS.sleep(200);
                player.pause();
                TimeUnit.MILLISECONDS.sleep(50);
            } } catch (InterruptedException ignored) {}
            player.pause();
        }
    }

    private static float[] getTonesFor(char c)
    {
        int index = SYMBOLS.indexOf(c);

        if(index < 0) return new float[0];

        return new float[] { LEAST[index % 4], MOST[index / 4] };
    }

    TonesPlayer player;
    Actor actor;

    public DTMFGenerator(float sampleRate) throws LineUnavailableException {
        player = new TonesPlayer(sampleRate);
        player.start();
    }

    public void play(String toPlay)
    {
        if(actor != null) actor.interrupt();
        actor = new Actor(toPlay);
        actor.start();
    }

    public void stop()
    {
        if(actor != null) actor.interrupt();
    }
}
