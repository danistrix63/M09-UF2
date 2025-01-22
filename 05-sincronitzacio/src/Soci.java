// Soci.java
import java.util.Random;

public class Soci extends Thread {
    private static final float APORTACIO = 10f;
    private static final int ESPERA_MAX = 100;
    private static final int MAX_ANY = 10;
    private final Compte compte;
    private final Random random = new Random();

    public Soci() {
        this.compte = Compte.getInstance();
    }

    @Override
    public void run() {
        for (int any = 0; any < MAX_ANY; any++) {
            for (int mes = 0; mes < 12; mes++) {
                if (mes % 2 == 0) {
                    compte.setSaldo(compte.getSaldo() + APORTACIO);
                } else {
                    compte.setSaldo(compte.getSaldo() - APORTACIO);
                }
                try {
                    Thread.sleep(random.nextInt(ESPERA_MAX));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
