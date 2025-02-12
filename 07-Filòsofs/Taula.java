import java.util.ArrayList;
import java.util.List;

public class Taula {
    private final List<Filòsof> filòsofs;
    private final List<Forquilla> forquilles;

    public Taula(int numFilòsofs) {
        filòsofs = new ArrayList<>();
        forquilles = new ArrayList<>();

        for (int i = 0; i < numFilòsofs; i++) {
            forquilles.add(new Forquilla(i));
        }

        for (int i = 0; i < numFilòsofs; i++) {
            Forquilla esquerra = forquilles.get(i);
            Forquilla dreta = forquilles.get((i + 1) % numFilòsofs);
            filòsofs.add(new Filòsof("fil" + i, esquerra, dreta));
        }
    }

    public void showTaula() {
        for (Filòsof f : filòsofs) {
            System.out.println("Comensal:" + f.getNom() +
                    " esq:" + f.getEsquerra().getNumero() +
                    " dret:" + f.getDreta().getNumero());
        }
    }

    public void cridaraTaula() {
        for (Filòsof f : filòsofs) {
            new Thread(f).start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridaraTaula();
    }
}
