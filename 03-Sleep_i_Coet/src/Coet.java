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

    public void pararMotors() {
        for (Motor motor : motors) {
            motor.parar(); // Detén cada motor
        }
    }

    // Verifica si tots els motors han arribat a 0
    public boolean totsElsMotorsAturats() {
        for (Motor motor : motors) {
            if (motor.getPotenciaActual() != 0) {
                return false; // Si qualsevol motor no està a 0, retorna false
            }
        }
        return true; // Si tots els motors estan a 0, retorna true
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introdueix la potència objectiu (0 per sortir): ");
            int potencia = scanner.nextInt();
            coet.passaAPotencia(potencia);

            // Si la potència és 0, verificar motors
            if (potencia == 0) {
                // Esperar fins que tots els motors estiguin a 0
                while (!coet.totsElsMotorsAturats()) {
                    try {
                        Thread.sleep(500); // Verificar cada 500ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Tots els motors estan aturats. Sortint del programa...");
                coet.pararMotors(); // Detenemos todos los hilos antes de salir
                return; // Salir del programa
            }
        }
    }
}
