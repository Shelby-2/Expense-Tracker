import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    private static User registeredUser;
    private static ArrayList<Expense> expenses =
            new ArrayList<>();

    private static Scanner scanner =
            new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=== EXPENSE TRACKER ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice =
                    Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void register() {

        System.out.println("\n=== REGISTER ===");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        registeredUser =
                new User(name,email,phone,
                        username,password);

        System.out.println("Registration Successful");
    }

    private static void login() {

        if (registeredUser == null) {
            System.out.println("Register first.");
            return;
        }

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals(
                registeredUser.getUsername())
                &&
                password.equals(
                        registeredUser.getPassword())) {

            dashboard();

        } else {

            System.out.println("Invalid credentials");
        }
    }

    private static void dashboard() {

        while (true) {

            System.out.println("\n=== DASHBOARD ===");

            System.out.println(
                    "Welcome "
                            + registeredUser.getName());

            System.out.println(
                    "Balance: KES "
                            + registeredUser.getBalance());

            System.out.println("1. Deposit Money");
            System.out.println("2. Add Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Logout");

            int choice =
                    Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    depositMoney();
                    break;

                case 2:
                    addExpense();
                    break;

                case 3:
                    viewExpenses();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void depositMoney() {

        System.out.print(
                "Enter amount to deposit: ");

        double amount =
                Double.parseDouble(
                        scanner.nextLine());

        registeredUser.deposit(amount);

        System.out.println(
                "Deposit successful");
    }

    private static void addExpense() {

        System.out.print("Category: ");
        String category =
                scanner.nextLine();

        System.out.print("Description: ");
        String description =
                scanner.nextLine();

        System.out.print("Amount: ");
        double amount =
                Double.parseDouble(
                        scanner.nextLine());

        if (amount >
                registeredUser.getBalance()) {

            System.out.println(
                    "Insufficient balance");

            return;
        }

        Expense expense =
                new Expense(category,
                        description,
                        amount);

        expenses.add(expense);

        registeredUser.deductExpense(amount);

        System.out.println(
                "Expense recorded");
    }

    private static void viewExpenses() {

        System.out.println(
                "\n=== EXPENSE LIST ===");

        if (expenses.isEmpty()) {

            System.out.println(
                    "No expenses recorded");

            return;
        }

        for (Expense expense : expenses) {

            System.out.println(expense);
        }
    }
}