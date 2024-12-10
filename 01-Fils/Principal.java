public class Principal {
    public static void main(String[] args) {
        System.out.println("Termina thread main");

        // Comportament 1: Intercalació
        System.out.println("=== Comportament 1: Intercalació ===");
        Fil juan1 = new Fil("Juan", 2, false);
        Fil pepe1 = new Fil("Pepe", 3, false);
        juan1.start();
        pepe1.start();
        try {
            juan1.join();
            pepe1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Comportament 2: Pepe Primer
        System.out.println("=== Comportament 2: Pepe Primer ===");
        Fil juan2 = new Fil("Juan", 5, false); // Retard més alt per a Juan
        Fil pepe2 = new Fil("Pepe", 1, false); // Retard més baix per a Pepe
        pepe2.start();
        juan2.start();
        try {
            pepe2.join();
            juan2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Comportament 3: Alternança Estricta
        System.out.println("=== Comportament 3: Alternança Estricta ===");
        Fil juan3 = new Fil("Juan", 0, true); // Mode alternança estricta
        Fil pepe3 = new Fil("Pepe", 0, true); // Mode alternança estricta
        juan3.start();
        pepe3.start();
        try {
            juan3.join();
            pepe3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
