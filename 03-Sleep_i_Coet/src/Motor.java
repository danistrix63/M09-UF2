public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private boolean executant = true; // Variable per controlar l'execució del thread
    private boolean jaFerRes = false;  // Variable per controlar si ja s'ha enviat el missatge "FerRes"

    public void setPotencia(int p) {
        this.potenciaObjectiu = p;
        jaFerRes = false; // Reiniciem perquè es pugui tornar a enviar el missatge "FerRes"
    }

    public int getPotenciaActual() {
        return potenciaActual; // Retorna la potència actual
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
                jaFerRes = false; // Reset per assegurar que el missatge es tornarà a enviar
            } else if (potenciaActual > potenciaObjectiu) {
                potenciaActual--;
                System.out.printf("%s: Decre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                jaFerRes = false; // Reset per assegurar que el missatge es tornarà a enviar
            } else if (!jaFerRes) {
                System.out.printf("%s: FerRes Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                jaFerRes = true; // Evitem que es torni a enviar el missatge fins que canviï la potència
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
