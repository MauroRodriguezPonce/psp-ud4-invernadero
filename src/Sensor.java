import java.util.Locale;

/** Tarea (Runnable) genérica de un sensor. Cada objeto guarda SUS estadísticas. */
public abstract class Sensor implements Runnable {
    protected final String nombre;
    protected final String unidad;
    protected final long periodoMs;
    protected final int numLecturas;
    protected final double umbralMin;
    protected final double umbralMax;

    // Estadísticas propias (no static: no se comparten entre sensores)
    private int lecturasHechas = 0;
    private int alertas = 0;
    private double suma = 0;
    private double minimo = Double.MAX_VALUE;
    private double maximo = -Double.MAX_VALUE;

    protected Sensor(String nombre, String unidad, long periodoMs, int numLecturas,
                     double umbralMin, double umbralMax) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodoMs = periodoMs;
        this.numLecturas = numLecturas;
        this.umbralMin = umbralMin;
        this.umbralMax = umbralMax;
    }

    /** Devuelve una lectura simulada */
    protected abstract double medir();

    @Override
    public void run() {
        for (int i = 1; i <= numLecturas; i++) {
            double valor = medir();
            boolean alerta = valor < umbralMin || valor > umbralMax;
            registrar(valor, alerta);
            // Runnable no tiene getName(): se pregunta al hilo actual (lo hace Log)
            Log.println(String.format(Locale.US, "%s lectura %d/%d: %.1f %s%s",
                    nombre, i, numLecturas, valor, unidad, alerta ? "  ⚠ ALERTA" : ""));
            try {
                Thread.sleep(periodoMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void registrar(double valor, boolean alerta) {
        lecturasHechas++;
        suma += valor;
        if (valor < minimo) minimo = valor;
        if (valor > maximo) maximo = valor;
        if (alerta) alertas++;
    }

    public String informe() {
        if (lecturasHechas == 0) {
            return String.format("%-12s sin lecturas todavía", nombre);
        }
        return String.format(Locale.US, "%-12s lecturas=%d media=%.1f%s min=%.1f%s max=%.1f%s alertas=%d",
                nombre, lecturasHechas, suma / lecturasHechas, unidad, minimo, unidad, maximo, unidad, alertas);
    }
}
