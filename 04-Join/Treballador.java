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
        this.edatActual = edatIniciTreball; // Iniciem en edatIniciTreball
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
        while (edatActual <= edatFiTreball) {
            cobra();
            pagaImpostos();
            edatActual++; // Incrementem l'edat correctament
            try {
                Thread.sleep(rnd.nextInt(50)); // Simulació d'un any
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrumpit: " + getName());
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
