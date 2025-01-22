// Compte.java
public class Compte {
    private static Compte instance;
    private float saldo = 0;

    private Compte() {}

    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
