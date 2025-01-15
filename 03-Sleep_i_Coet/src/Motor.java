public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private boolean executant = true; // Variable per controlar l'execució del thread

    public void setPotencia(int p) {
        this.potenciaObjectiu = p;
    }

    public void parar() {
        executant = false; // Atura el bucle quan es cridi a aquest mètode
    }

    @Override
    public void run() {
        while (executant) {
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
        System.out.printf("%s: Motor aturat%n", getName());
    }
}
