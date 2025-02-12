import java.util.Random;

public class Filòsof implements Runnable {
    private final String nom;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private int gana = 0;
    private final Random rand = new Random();

    public Filòsof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.esquerra = esquerra;
        this.dreta = dreta;
    }

    public String getNom() {
        return nom;
    }

    public Forquilla getEsquerra() {
        return esquerra;
    }

    public Forquilla getDreta() {
        return dreta;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof: " + nom + " pensant");
        Thread.sleep(rand.nextInt(1000) + 1000);
    }

    private void menjar() throws InterruptedException {
        System.out.println("Filòsof: " + nom + " menja");
        Thread.sleep(rand.nextInt(1000) + 1000);
        gana = 0;
    }

    private boolean intentarAgafarForquilles() throws InterruptedException {
        if (esquerra.agafar()) {
            System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + esquerra.getNumero());
            if (dreta.agafar()) {
                System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + dreta.getNumero());
                return true;
            } else {
                esquerra.deixar();
                System.out.println("Filòsof: " + nom + " deixa l'esquerra(" + esquerra.getNumero() +
                    ") i espera (dreta ocupada)");
            }
        }
        gana++;
        System.out.println("Filòsof: " + nom + " gana=" + gana);
        Thread.sleep(rand.nextInt(500) + 500);
        return false;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                boolean teForquilles = false;
                while (!teForquilles) {
                    teForquilles = intentarAgafarForquilles();
                }
                menjar();
                dreta.deixar();
                esquerra.deixar();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
