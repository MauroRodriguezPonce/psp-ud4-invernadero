import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Utilidad de log: hora + hilo + mensaje. */
public class Log {
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void println(String msg) {
        System.out.println(LocalTime.now().format(F) + " [" + Thread.currentThread().getName() + "] " + msg);
    }
}
