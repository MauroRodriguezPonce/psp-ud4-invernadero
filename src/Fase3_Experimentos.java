/** Fase 3: tres experimentos. */
public class Fase3_Experimentos {
    public static void main(String[] args) throws InterruptedException {
        experimentoA();
        experimentoB();
        experimentoC();
    }

    /** A: informe() sin esperar al hilo. */
    static void experimentoA() throws InterruptedException {
        System.out.println("\n##### EXPERIMENTO A: informe sin join #####");
        Sensor s = new Temperatura();
        Thread h = new Thread(s, "sensor-Temperatura");
        h.start();
        System.out.println("Informe SIN esperar: " + s.informe());
        h.join();
        System.out.println("Informe tras join:   " + s.informe());
    }

    /** B: run() en vez de start(). */
    static void experimentoB() {
        System.out.println("\n##### EXPERIMENTO B: run() en vez de start() #####");
        ProgramadorRiego riego = new ProgramadorRiego(3);
        long ini = System.currentTimeMillis();
        riego.run();
        long t = System.currentTimeMillis() - ini;
        System.out.println("Tiempo: " + t + " ms");
        System.out.println("getState() = " + riego.getState());
        System.out.println(riego.informe());
    }

    /** C: start() dos veces sobre el mismo objeto. */
    static void experimentoC() throws InterruptedException {
        System.out.println("\n##### EXPERIMENTO C: doble start() #####");
        ProgramadorRiego riego = new ProgramadorRiego(2);
        riego.start();
        riego.join();
        try {
            riego.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e);
        }
    }
}
