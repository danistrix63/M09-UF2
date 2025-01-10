import java.util.Scanner;

public class Coet {
    private Motor[] motors;

    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor();
            motors[i].setName("Motor " + i);
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: Potència fora de rang (ha de ser entre 0 i 10)");
            return;
        }
        System.out.println("Passant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introdueix la potència objectiu (0 per sortir): ");
            int potencia = scanner.nextInt();
            coet.passaAPotencia(potencia);
            if (potencia == 0) {
                break;
            }
        }
        scanner.close();
    }
}
