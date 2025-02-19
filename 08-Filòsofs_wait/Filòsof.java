public class Filòsof implements Runnable {
    private final int numero;
    private final Forquilla forquillaEsquerra;
    private final Forquilla forquillaDreta;

    public Filòsof(int numero, Forquilla esquerra, Forquilla dreta) {
        this.numero = numero;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                agafarForquilles();
                menjar();
                deixarForquilles();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Filòsof " + numero + " ha estat interromput.");
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof " + numero + " està pensant.");
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Espera entre 1s i 2s
    }

    private void agafarForquilles() throws InterruptedException {
        forquillaEsquerra.agafar(); // Intenta agafar la forquilla esquerra
        System.out.println("Filòsof " + numero + " ha agafat la forquilla esquerra.");

        try {
            forquillaDreta.agafar(); // Intenta agafar la forquilla dreta
            System.out.println("Filòsof " + numero + " ha agafat la forquilla dreta.");
        } catch (InterruptedException e) {
            forquillaEsquerra.deixar(); // Si no pot agafar la dreta, deixa l'esquerra
            throw e; // Relança l'excepció
        }
    }

    private void menjar() throws InterruptedException {
        System.out.println("Filòsof " + numero + " està menjant.");
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Menja entre 1s i 2s
    }

    private void deixarForquilles() {
        forquillaEsquerra.deixar(); // Deixa la forquilla esquerra
        forquillaDreta.deixar(); // Deixa la forquilla dreta
        System.out.println("Filòsof " + numero + " ha deixat les forquilles.");
    }
}
