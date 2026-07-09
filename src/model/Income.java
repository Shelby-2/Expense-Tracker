package model;

import java.time.LocalDate;

public class Income {

    private int incomeID;
    private int userID;
    private double amount;
    private String source;
    private LocalDate incomeDate;

    public Income(int userID,
                  double amount,
                  String source,
                  LocalDate incomeDate) {

        this.userID = userID;
        this.amount = amount;
        this.source = source;
        this.incomeDate = incomeDate;
    }

    public Income(int incomeID,
                  int userID,
                  double amount,
                  String source,
                  LocalDate incomeDate) {

        this.incomeID = incomeID;
        this.userID = userID;
        this.amount = amount;
        this.source = source;
        this.incomeDate = incomeDate;
    }

    public int getIncomeID() {
        return incomeID;
    }

    public int getUserID() {
        return userID;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }
}