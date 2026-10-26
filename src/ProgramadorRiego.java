import java.util.Locale;

/** El riego ES un hilo (extends Thread). */
public class ProgramadorRiego extends Thread {
    private static final double LITROS_POR_APERTURA = 2.5;
    private static final long PERIODO_MS = 700;

    private final int ciclos;
    private int ciclosHechos = 0;
    private double litros = 0;

    public ProgramadorRiego() {
        this(5);
    }

    public ProgramadorRiego(int ciclos) {
        super("riego");              // nombre del hilo, como en el Cohete del Ejemplo 0A
        this.ciclos = ciclos;
    }

    @Override
    public void run() {
        for (int c = 1; c <= ciclos; c++) {
            boolean abre = (c % 2 == 1);
            if (abre) litros += LITROS_POR_APERTURA;
            ciclosHechos++;
            Log.println(String.format(Locale.US, "Riego ciclo %d/%d: válvula %s (acumulado %.1f L) [getName()=%s]",
                    c, ciclos, abre ? "ABIERTA" : "cerrada", litros, getName()));
            try {
                Thread.sleep(PERIODO_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public String informe() {
        return String.format(Locale.US, "%-12s ciclos=%d litros=%.1f L", "Riego", ciclosHechos, litros);
    }
}
