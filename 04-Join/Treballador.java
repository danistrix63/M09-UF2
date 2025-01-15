import java.util.Random;

public class Treballador extends Thread {
    private final float nouAnualBrut;
    private final int edatIniciTreball;
    private final int edatFiTreball;
    private int edatActual;
    private float cobrat;
    private final Random rnd;

    public Treballador(String nom, float nouAnualBrut, int edatIniciTreball, int edatFiTreball) {
        super(nom);
        this.nouAnualBrut = nouAnualBrut;
        this.edatIniciTreball = edatIniciTreball;
        this.edatFiTreball = edatFiTreball;
        this.edatActual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    private void cobra() {
        cobrat += nouAnualBrut / 12.0;
    }

    private void pagaImpostos() {
        cobrat -= (nouAnualBrut / 12.0) * 0.24;
    }

    @Override
    public void run() {
        for (int any = edatIniciTreball; any <= edatFiTreball; any++) {
            cobra();
            pagaImpostos();
            edatActual++;
            try {
                Thread.sleep(rnd.nextInt(50)); // Simulación de tiempo para un año.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrumpido: " + getName());
            }
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edatActual;
    }
}
