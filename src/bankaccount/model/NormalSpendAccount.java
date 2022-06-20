/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.model;

import bankaccount.common.Utils;
import java.math.BigDecimal;
import java.util.Date;

public class NormalSpendAccount extends Account {
    private BigDecimal balance;
    private BigDecimal cashLimit;

    public NormalSpendAccount() {
    }
    
    public NormalSpendAccount(BigDecimal balance, BigDecimal cashLimit, String accountNo, String accountName, Date openDate) {
        super(accountNo, accountName, openDate);
        this.balance = balance;
        this.cashLimit = cashLimit;
    }

    public NormalSpendAccount(String accountNo, String accountName, Date openDate) {
        super(accountNo, accountName, openDate);
    }
    
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCashLimit() {
        return cashLimit;
    }

    public void setCashLimit(BigDecimal cashLimit) {
        this.cashLimit = cashLimit;
    }

    @Override
    public String deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "01";
        }
        BigDecimal newBalance = this.balance.add(amount);
        this.balance = newBalance;
        return "00";
    }

    @Override
    public String withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return "01";
        }
        BigDecimal newBalance = this.balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) <= 0) {
            return "02";
        }
        if (newBalance.subtract(new BigDecimal(50.000)).compareTo(BigDecimal.ZERO) <= 0) {
            return "03";
        }
        this.balance = newBalance;
        return "00";
    }
    
    public String transfer(String toAccountNo, BigDecimal amount) {
        return null;
    }

    @Override
    public String toString() {
        return accountNo + "," + accountName + "," + 
                Utils.formatDate(openDate, "dd-MM-yyyy") + "," + "NORMAL" + ","
                + String.valueOf(balance) + "," 
                + String.valueOf(cashLimit);    
    }
}
