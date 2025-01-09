public class Main {
    public static void main(String[] args) {
        Thread[] jugadors = new Thread[Futbolista.NUM_JUGADORS];
        String[] noms = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan",
                         "Belli", "Arnau", "Aspas", "Messi", "MBapé"};

        // 1. Crear els fils
        for (int i = 0; i < Futbolista.NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
        }

        System.out.println("Inici dels xuts --------------------");

        // 2. Iniciar els fils
        for (Thread jugador : jugadors) {
            jugador.start();
        }

        // 3. Esperar que tots els fils acabin
        for (Thread jugador : jugadors) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");

        // 4. Mostrar estadístiques
        for (Thread jugador : jugadors) {
            Futbolista f = (Futbolista) jugador;
            System.out.println(f.getName() + " -> " + f.getNgols() + " gols");
        }
    }
}
