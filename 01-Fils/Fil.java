public class Fil extends Thread {
    private final String nom;
    private final int retard;
    private static final Object candau = new Object();
    private static boolean tornJuan = true; // Control d'alternança
    private final boolean alternanciaEstricta; // Flag per a alternança estricta

    public Fil(String nom, int retard, boolean alternanciaEstricta) {
        this.nom = nom;
        this.retard = retard;
        this.alternanciaEstricta = alternanciaEstricta;
    }

    @Override
    public void run() {
        if (alternanciaEstricta) {
            executarAlternancia();
        } else {
            executarNormal();
        }
    }

    private void executarNormal() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            try {
                Thread.sleep(retard * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Termina el fil " + nom);
    }

    private void executarAlternancia() {
        for (int i = 1; i <= 9; i++) {
            synchronized (candau) {
                while (tornJuan != nom.equals("Juan")) {
                    try {
                        candau.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(nom + " " + i);
                tornJuan = !tornJuan;
                candau.notifyAll();
            }
        }
        System.out.println("Termina el fil " + nom);
    }
}
