/** Fase 1: prototipo secuencial. Se llama a run() (NO a start()). */
public class Fase1_Secuencial {
    public static void main(String[] args) {
        Sensor t = new Temperatura();
        Sensor h = new Humedad();
        Sensor l = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        long ini = System.currentTimeMillis();
        t.run();
        h.run();
        l.run();
        riego.run();
        long total = System.currentTimeMillis() - ini;

        System.out.println("\n===== INFORME (secuencial) =====");
        System.out.println(t.informe());
        System.out.println(h.informe());
        System.out.println(l.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total: " + total + " ms");
    }
}
