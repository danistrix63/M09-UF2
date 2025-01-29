import java.util.Random;

public class Assistent implements Runnable {
    private String nom;
    private Esdeveniment esdeveniment;
    private Random random;

    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.nom = nom;
        this.esdeveniment = esdeveniment;
        this.random = new Random();
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int accio = random.nextInt(2); // 0 o 1
                if (accio == 0) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
                Thread.sleep(random.nextInt(1000)); // Espera entre 0 i 1 segon
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
