import java.util.Random;

public class Horse extends Thread {
    private static int nextHorseID = 0;
    private int horseID;
    private int location;

    public Horse() {
        horseID = ++nextHorseID;
        location = 0;
    }

    public void run() {
        Random rand = new Random();
        while (location < 50) {
            int sleepTime = 100 * rand.nextInt(10);
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            location++;
        }
    }

    public void printHorse() {
        for (int i = 0; i < location; i++) {
            System.out.print(" ");
        }
        System.out.print(horseID);
        for (int i = location; i < 50; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public boolean reachedEnd() {
        return location >= 50;
    }

    public static void resetHorses() {
        nextHorseID = 0;
    }
}