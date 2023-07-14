import java.util.*;

class Account {
    static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------------");
        System.out.println("Please enter your full name:");
        ATMInterface.fullName = scanner.nextLine();
        System.out.println("Please enter the username:");
        String username = scanner.nextLine();
        System.out.println("Input your password:");
        String password = scanner.nextLine();
        System.out.println("Please enter your account number:");
        ATMInterface.accountNumber = scanner.nextLine();
        System.out.println("Account Logged In ✅ ");

        ATMInterface.prompt();
        while (true) {
            display(ATMInterface.fullName);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> login(username, password);
                case 2 -> System.exit(0);
                default -> System.out.println("Invalid input!!!");
            }
        }
    }

    static void display(String name) {
    }

    static void login(String username, String password) {
    }
}

class Transactions {
    static void withdrawAmount() {
        System.out.println("--------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much amount do you want to withdraw?");
        int withdrawnAmount = scanner.nextInt();
        if (withdrawnAmount <= ATMInterface.accountBalance) {
            ATMInterface.accountBalance -= withdrawnAmount;
            ATMInterface.transactionHistory.add("Amount Withdrawn: ");
            ATMInterface.transactionHistory.add(Integer.toString(withdrawnAmount));

            System.out.println("₹" + withdrawnAmount + " withdrawn [✔]");
        } else {
            System.out.println("ENTERED AMOUNT IS GREATER THAN CURRENT BALANCE ❎");
        }

        ATMInterface.prompt();
    }

    static void depositAmount() {
        System.out.println("--------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much cash do you want to deposit?");
        int depositedCash = scanner.nextInt();
        ATMInterface.updateBalance(depositedCash);
        ATMInterface.transactionHistory.add("Amount Deposited: ");
        ATMInterface.transactionHistory.add(Integer.toString(depositedCash));
        System.out.println("₹" + depositedCash + "/- deposited [✔]");
        ATMInterface.prompt();
    }

    static void transfer() {
        System.out.println("--------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the receiver:");
        String receiverName = scanner.nextLine();
        System.out.println("Please enter the account number of the receiver:");
        int receiverAccNum = scanner.nextInt();
        System.out.println("Please enter the amount to be transferred:");
        int transferredAmount = scanner.nextInt();
        if (transferredAmount <= ATMInterface.accountBalance) {
            ATMInterface.accountBalance -= transferredAmount;
            ATMInterface.transactionHistory.add("Amount Transferred: ");
            ATMInterface.transactionHistory.add(Integer.toString(transferredAmount));

            System.out.println("₹" + transferredAmount + " transferred [✔]");
        } else {
            System.out.println("ENTERED AMOUNT IS GREATER THAN CURRENT BALANCE ❎");
        }
        ATMInterface.prompt();
    }
}

class DisplayBalance {
    static void displayBalance() {
        System.out.println("--------------------------------------------------------");
        System.out.println("CURRENT BALANCE:");
        ATMInterface.displayBalance();
        ATMInterface.prompt();
    }
}

class TransactionHistory {
    static void transactionHistory() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Transaction History:");
        int count = 0;
        if (ATMInterface.accountBalance > 0) {
            for (int i = 0; i < (ATMInterface.transactionHistory.size() / 2); i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.print(ATMInterface.transactionHistory.get(count) + " ");
                    count++;
                }
                System.out.println();
            }
        } else {
            System.out.println("Your Balance is ₹0");
        }
        ATMInterface.prompt();
    }
}

public class ATMInterface {
    public static String fullName;
    public static int accountBalance = 0;
    public static String accountNumber;
    public static ArrayList<String> transactionHistory = new ArrayList<>();

    static void updateBalance(int depositedCash) {
        accountBalance += depositedCash;
    }

    static void displayBalance() {
        System.out.println(accountBalance);
    }

    public static void loginPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("—————————— WELCOME USER ——————————");
        System.out.println("Choose your choice:");
        System.out.println("(1) Log-In");
        System.out.println("(2) Log-Out");
        System.out.println("Your choice:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> Account.login();
            case 2 -> System.exit(0);
            default -> {
                System.out.println("Invalid Choice ❎");
                loginPage();
            }
        }
    }

    static void prompt() {
        System.out.println("--------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + ATMInterface.fullName);
        System.out.println("Choose from the options below:");
        System.out.println("[1] Withdraw");
        System.out.println("[2] Deposit");
        System.out.println("[3] Transfer");
        System.out.println("[4] Display Current Balance");
        System.out.println("[5] Transaction History");
        System.out.println("[6] Quit");
        System.out.println("Your Choice:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> Transactions.withdrawAmount();
            case 2 -> Transactions.depositAmount();
            case 3 -> Transactions.transfer();
            case 4 -> DisplayBalance.displayBalance();
            case 5 -> TransactionHistory.transactionHistory();
            case 6 -> System.exit(0);
        }
    }

    public static void main(String[] args) {
        loginPage();
    }
}
