import java.util.Random;

class Client {
    private final String nom;

    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public void tallarseElCabell() {
        try {
            Random rand = new Random();
            double tempsTall = 0.9 + rand.nextDouble() * 0.1;
            System.out.println("Tallant cabell a " + nom);
            Thread.sleep((long) (tempsTall * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }
}
