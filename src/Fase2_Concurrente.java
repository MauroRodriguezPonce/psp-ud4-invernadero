/** Fase 2: un hilo por dispositivo y main espera con join(). */
public class Fase2_Concurrente {
    public static void main(String[] args) throws InterruptedException {
        Sensor t = new Temperatura();
        Sensor h = new Humedad();
        Sensor l = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        Thread hT = new Thread(t, "sensor-Temperatura");
        Thread hH = new Thread(h, "sensor-Humedad");
        Thread hL = new Thread(l, "sensor-Luz");

        long ini = System.currentTimeMillis();
        hT.start();
        hH.start();
        hL.start();
        riego.start();          // el riego ya es un Thread: no hace falta new Thread(...)

        hT.join();
        hH.join();
        hL.join();
        riego.join();
        long total = System.currentTimeMillis() - ini;

        System.out.println("\n===== INFORME (concurrente) =====");
        System.out.println(t.informe());
        System.out.println(h.informe());
        System.out.println(l.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total: " + total + " ms");
    }
}
