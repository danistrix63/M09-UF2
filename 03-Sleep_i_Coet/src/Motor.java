public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
        notify(); // Notifiquem el thread perquè reprengui l'execució si està en espera
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (potenciaActual == potenciaObjectiu) {
                    try {
                        wait(); // Espera fins que hi hagi una nova potència objectiu
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (potenciaActual < potenciaObjectiu) {
                potenciaActual++;
                System.out.printf("%s: Incre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
            } else if (potenciaActual > potenciaObjectiu) {
                potenciaActual--;
                System.out.printf("%s: Decre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
            }

            try {
                Thread.sleep(1000 + (int) (Math.random() * 1000)); // Interval aleatori entre 1 i 2 segons
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
