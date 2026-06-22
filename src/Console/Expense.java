import java.time.LocalDate;

public class Expense {

    private String category;
    private String description;
    private double amount;
    private LocalDate date;

    public Expense(String category,
                   String description,
                   double amount) {

        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {

        return date +
                " | " +
                category +
                " | KES " +
                amount +
                " | " +
                description;
    }
}