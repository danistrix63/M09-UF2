// Compte.java (Amb sincronització)
public class Compte {
    private static Compte instance;
    private float saldo = 0;

    private Compte() {}

    public static synchronized Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public synchronized float getSaldo() {
        return saldo;
    }

    public synchronized void ingressar(float quantitat) {
        saldo += quantitat;
    }

    public synchronized void retirar(float quantitat) {
        saldo -= quantitat;
    }
}
