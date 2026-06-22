public class User {
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private double balance;

    public User(String name, String email, String phone,
                String username, String password) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void deductExpense(double amount) {
        balance -= amount;
    }

    public String getName() {
        return name;
    }
}