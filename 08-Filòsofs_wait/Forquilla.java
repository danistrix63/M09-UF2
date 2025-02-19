public class Forquilla {
    private boolean disponible = true;

    public synchronized void agafar() throws InterruptedException {
        while (!disponible) {
            wait(); // Espera hasta que la forquilla esté disponible
        }
        disponible = false;
    }

    public synchronized void deixar() {
        disponible = true;
        notifyAll(); // Notifica a los demás filósofos en espera
    }
}
