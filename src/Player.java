public class Player {
    private int money;
    private int horseOneBetAmount;
    private int horseTwoBetAmount;
    private int horseThreeBetAmount;
    private static int nextPlayerID = 0;
    private int playerID;

    public Player() {
        this.money = 100;
        this.horseOneBetAmount = 0;
        this.horseTwoBetAmount = 0;
        this.horseThreeBetAmount = 0;
        this.playerID = ++nextPlayerID;
    }

    public int getMoney() {
        return money;
    }

    public int getHorseOneBetAmount() {
        return horseOneBetAmount;
    }

    public int getHorseTwoBetAmount() {
        return horseTwoBetAmount;
    }

    public int getHorseThreeBetAmount() {
        return horseThreeBetAmount;
    }

    public void placeBet(int horseChoice, int amount) {
        if (amount <= money) {
            money -= amount;
            if (horseChoice == 1) {
                horseOneBetAmount += amount;
            } else if (horseChoice == 2) {
                horseTwoBetAmount += amount;
            } else if (horseChoice == 3) {
                horseThreeBetAmount += amount;
            }
            System.out.println("Your bet of $" + amount + " has been placed on Horse " + horseChoice + ".");
            System.out.println("You now have $" + money + " remaining.");
        }
        else {
            System.out.print("You do not have enough money, your balance is $" + money);
        }
    }

    public void checkBetAmount() {
        if (getHorseOneBetAmount() > 0) {
            System.out.println("Your current bet is $" + getHorseOneBetAmount() + " on Horse 1.");
        }
        if (getHorseTwoBetAmount() > 0) {
            System.out.println("Your current bet is $" + getHorseTwoBetAmount() + " on Horse 2.");
        }
        if (getHorseThreeBetAmount() > 0) {
            System.out.println("Your current bet is $" + getHorseThreeBetAmount() + " on Horse 3.");
        }
        if (getHorseOneBetAmount() + getHorseTwoBetAmount() + getHorseThreeBetAmount() == 0) {
            System.out.println("You have no bets placed.");
        }
    }

    public void horseOneWon() {
        int amountWon = 2 * horseOneBetAmount;
        this.money += amountWon;
        System.out.println("Player " + playerID + " won $" + amountWon + ".");
    }

    public void horseTwoWon() {
        int amountWon = 2 * horseTwoBetAmount;
        this.money += amountWon;
        System.out.println("Player " + playerID + " won $" + amountWon + ".");
    }

    public void horseThreeWon() {
        int amountWon = 2 * horseThreeBetAmount;
        this.money += amountWon;
        System.out.println("Player " + playerID + " won $" + amountWon + ".");
    }

    public void resetBets() {
        horseOneBetAmount = 0;
        horseTwoBetAmount = 0;
        horseThreeBetAmount = 0;
    }

    public String toString() {
        return "Player " + playerID + " won with $" + money + "!";
    }

}
