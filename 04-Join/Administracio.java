import java.util.ArrayList;
import java.util.List;

public class Administracio {
    private static final int NUM_POBLACIO_ACTIVA = 50;
    private final List<Treballador> poblacioActiva;

    public Administracio() {
        poblacioActiva = new ArrayList<>();
        for (int i = 0; i < NUM_POBLACIO_ACTIVA; i++) {
            poblacioActiva.add(new Treballador("Ciutadà-" + i, 25000.0f, 20, 65));
        }
    }

    public void iniciar() {
        // Iniciem tots els threads
        for (Treballador treballador : poblacioActiva) {
            treballador.start();
        }

        // Esperem que tots acabin amb join()
        for (Treballador treballador : poblacioActiva) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error esperant el fil: " + treballador.getName());
            }
        }

        // Mostrem els resultats
        for (Treballador treballador : poblacioActiva) {
            System.out.printf("%s -> edat: %d / total: %.2f\n",
                    treballador.getName(), treballador.getEdat(), treballador.getCobrat());
        }
    }

    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.iniciar();
    }
}
