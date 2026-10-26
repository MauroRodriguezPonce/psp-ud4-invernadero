import java.util.concurrent.ThreadLocalRandom;

public class Luz extends Sensor {
    public Luz() {
        super("Luz", "lux", 500, 5, 200, 900);
    }

    @Override
    protected double medir() {
        return ThreadLocalRandom.current().nextDouble(0.0, 1000.0);
    }
}
