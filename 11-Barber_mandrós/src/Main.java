public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");

        Thread barberThread = new Thread(barber);
        barberThread.start();

        barberia.iniciar();
    }
}
