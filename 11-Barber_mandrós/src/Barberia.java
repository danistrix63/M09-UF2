import java.util.LinkedList;
import java.util.Queue;

class Barberia {
    private final Queue<Client> salaEspera;
    private final int maxCadires;
    private final Object condBarber = new Object();
    private static Barberia instance;

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
        instance = this;
    }

    public static Barberia getInstance() {
        return instance;
    }

    public Client seguentClient() {
        synchronized (salaEspera) {
            return salaEspera.poll();
        }
    }

    public void entrarClient(Client client) {
        synchronized (salaEspera) {
            if (salaEspera.size() < maxCadires) {
                salaEspera.offer(client);
                System.out.println("Client " + client.getNom() + " en espera");
                synchronized (condBarber) {
                    condBarber.notify();
                }
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    public void iniciar() {
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    entrarClient(new Client(i));
                    Thread.sleep(500);
                }
                Thread.sleep(10000);
                for (int i = 11; i <= 20; i++) {
                    entrarClient(new Client(i));
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Object getCondBarber() {
        return condBarber;
    }
}
