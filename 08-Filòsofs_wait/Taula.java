import java.util.ArrayList;
import java.util.List;

public class Taula {
    private final List<Filòsof> filòsofs;
    private final List<Forquilla> forquilles;

    public Taula(int numFilòsofs) {
        forquilles = new ArrayList<>();
        for (int i = 0; i < numFilòsofs; i++) {
            forquilles.add(new Forquilla()); // Crea les forquilles
        }

        filòsofs = new ArrayList<>();
        for (int i = 0; i < numFilòsofs; i++) {
            Forquilla esquerra = forquilles.get(i);
            Forquilla dreta = forquilles.get((i + 1) % numFilòsofs);
            filòsofs.add(new Filòsof(i, esquerra, dreta)); // Crea els filòsofs
        }
    }

    public void cridarATaula() {
        for (Filòsof filòsof : filòsofs) {
            new Thread(filòsof).start(); // Inicia cada filòsof en un fil d'execució
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(5); // Crea una taula amb 5 filòsofs
        taula.cridarATaula(); // Inicia el sopar
    }
}
