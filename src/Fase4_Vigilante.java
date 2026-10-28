/** Fase 4: vigilante demonio que muestra los estados de los cuatro hilos. */
public class Fase4_Vigilante {
    public static void main(String[] args) throws InterruptedException {
        Sensor t = new Temperatura();
        Sensor h = new Humedad();
        Sensor l = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hT = new Thread(t, "sensor-Temperatura");
        Thread hH = new Thread(h, "sensor-Humedad");
        Thread hL = new Thread(l, "sensor-Luz");
        Thread[] hilos = {hT, hH, hL, riego};

        Thread vigilante = new Thread(() -> {
            while (true) {
                mostrarEstados("vigilante", hilos);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "vigilante");
        vigilante.setDaemon(true);   // ANTES de start(): la JVM no lo espera

        mostrarEstados("ANTES de arrancar", hilos);
        hT.start();
        hH.start();
        hL.start();
        riego.start();
        vigilante.start();

        hT.join();
        hH.join();
        hL.join();
        riego.join();
        mostrarEstados("DESPUÉS de esperar", hilos);
        System.out.println(t.informe());
        System.out.println(h.informe());
        System.out.println(l.informe());
        System.out.println(riego.informe());
    }

    static void mostrarEstados(String etiqueta, Thread[] hilos) {
        StringBuilder sb = new StringBuilder(etiqueta + " -> ");
        for (Thread th : hilos) {
            sb.append(th.getName()).append('=').append(th.getState()).append("  ");
        }
        Log.println(sb.toString());
    }
}
