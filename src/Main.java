import java.util.*;

class Main {
    public static void optionMenu() {
        System.out.println("1: Check balance");
        System.out.println("2: Place a bet");
        System.out.println("3: Check current bet amount");
        System.out.println("4: Continue");
        System.out.println("The race will commence when all players have chosen continue.");
    }

    public static void addDelay() {
        System.out.println();
        try {
            Thread.sleep(1000);
        }
        catch(Exception e) {}
    }

    public static void winner(ArrayList<Player> hi) {
        int winningAmount = 0;
        for (Player i : hi) {
            if (i.getMoney() > winningAmount) {
                winningAmount = i.getMoney();
            }
        }
        for (Player i : hi) {
            if (i.getMoney() == winningAmount) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[Horse Betting]");
        System.out.println("Welcome to Horse Betting, the starting deposit is 100.");
        System.out.println("The betting multiplier is 2x.");
        System.out.println("How many players will be playing?");

        int playerCount = sc.nextInt();
        ArrayList<Player> hi = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            Player playa = new Player();
            hi.add(playa);
        }

        Horse h1, h2, h3;

        Thread t1, t2, t3;

        boolean happy;
        boolean happy2 = false;

        do {
            for (int i = 0; i <= hi.size() - 1; i++) {
                happy = false;
                System.out.println("Player " + (i + 1) + " Choose an Option: ");
                optionMenu();
                do {
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("You currently have $" + hi.get(i).getMoney() + ".");
                            addDelay();
                            System.out.println("Player " + (i + 1) + " Choose an Option: ");
                            optionMenu();
                            break;
                        case 2:
                            System.out.println("Which horse would you like to bet on?");
                            System.out.println("Options are: 1, 2 or 3.");
                            int horseChoice = sc.nextInt();
                            while (horseChoice != 1 && horseChoice != 2 && horseChoice != 3) {
                                System.out.println("You must choose Horse 1, 2 or 3.");
                                addDelay();
                                System.out.println("Which horse would you like to bet on?");
                                System.out.println("Options are: 1, 2 or 3.");
                                horseChoice = sc.nextInt();
                            }
                            System.out.println("How much would you like to bet?");
                            int amount = sc.nextInt();
                            hi.get(i).placeBet(horseChoice, amount);
                            addDelay();
                            System.out.println("Player " + (i + 1) + " Choose an Option: ");
                            optionMenu();
                            break;
                        case 3:
                            hi.get(i).checkBetAmount();
                            addDelay();
                            System.out.println("Player " + (i + 1) + " Choose an Option: ");
                            optionMenu();
                            break;
                        case 4:
                            happy = true;
                            System.out.println("Player " + (i + 1) + " your turn is over.");
                            addDelay();
                            break;
                        default:
                            System.out.println("Choose an Option from 1-4!");
                            addDelay();
                            optionMenu();
                            break;
                    }
                } while (!happy);
            }
            System.out.println("The race will now begin.");

            h1 = new Horse();
            h2 = new Horse();
            h3 = new Horse();

            t1 = new Thread(h1);
            t2 = new Thread(h2);
            t3 = new Thread(h3);

            t1.start();
            t2.start();
            t3.start();

            while (!h1.reachedEnd() && !h2.reachedEnd() && !h3.reachedEnd()) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                h1.printHorse();
                h2.printHorse();
                h3.printHorse();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            if (h1.reachedEnd()) {
                System.out.println("Horse 1 won!");
                for (Player i : hi) {
                    i.horseOneWon();
                }
            }
            if (h2.reachedEnd()) {
                System.out.println("Horse 2 won!");
                for (Player i : hi) {
                    i.horseTwoWon();
                }
            }
            if (h3.reachedEnd()) {
                System.out.println("Horse 3 won!");
                for (Player i : hi) {
                    i.horseThreeWon();
                }
            }
            for (Player i : hi) {
                i.resetBets();
            }
            addDelay();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("All bets are now reset to $0.");
            for (int i = 0; i < hi.size(); i++) {
                System.out.println("Player " + (i + 1) + " now has $" + hi.get(i).getMoney() + ".");
            }
            Horse.resetHorses();
            int stop = 0;
            for (int i = 1; i <= hi.size(); i++) {
                System.out.println("Player " + i + " would you like to continue? Y/N");
                System.out.println("Note: All players must select \"N\" to stop the game.");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("N")) {
                    stop++;
                }
            }
            if (stop == hi.size()) {
                happy2 = true;
                winner(hi);
            }
        } while (!happy2);
    }
}