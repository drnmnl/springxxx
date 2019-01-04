package com.accenture.tcf.bars.darren.e.b.manuel.domain;
import java.util.Date;

public class Record {

    private int id;
    private int billingCycle;
    private Date startDate;
    private Date endDate;
    private String accountName;
    private String lastName;
    private String firstName;
    private double amount;

    public Record(int billingCycle, Date startDate, Date endDate, String accountName, String lastName, String firstName, double amount) {
        this.billingCycle = billingCycle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountName = accountName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.amount = amount;
    }

    public Record() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(int billingCycle) {
        this.billingCycle = billingCycle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return ("Record Cycle: " +billingCycle + "\n Start Date: " + startDate + "\n End Date: " + endDate + "\n Last Name: "
                + lastName + "\n First Name: " + firstName + "\n Amount" + amount + "\n Account Name: " + accountName);
    }


}
