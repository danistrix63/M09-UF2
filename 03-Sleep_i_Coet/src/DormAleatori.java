public class DormAleatori extends Thread {
    private long instantCreacio; // Instant en què es crea la instància
    private String nom;

    public DormAleatori(String nom) {
        super(nom); // Assignem el nom al Thread
        this.nom = nom;
        this.instantCreacio = System.currentTimeMillis(); // Guardem l'instant de creació
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int intervalAleatori = (int) (Math.random() * 1000); // Genera un interval aleatori entre 0 i 1000 ms
            long tempsTotal = System.currentTimeMillis() - instantCreacio; // Temps total des de la creació
            System.out.printf("%s(%d) a dormir %dms total %d%n", nom, i, intervalAleatori, tempsTotal);

            try {
                Thread.sleep(intervalAleatori); // Dormim l'interval aleatori
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();

        System.out.println("-- Fi de main -----------");
    }
}
