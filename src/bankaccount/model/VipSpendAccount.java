/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.model;

import bankaccount.common.Utils;
import java.math.BigDecimal;
import java.util.Date;

public class VipSpendAccount extends Account {
    private BigDecimal balance;
    private BigDecimal cashLimit;

    public VipSpendAccount() {
    }
    
    public VipSpendAccount(BigDecimal balance, BigDecimal cashLimit, String accountNo, String accountName, Date openDate) {
        super(accountNo, accountName, openDate);
        this.balance = balance;
        this.cashLimit = cashLimit;
    }

    public VipSpendAccount(String accountNo, String accountName, Date openDate) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String withdraw(BigDecimal amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String transfer(String toAccountNo, BigDecimal amount) {
        return "";
    }

    @Override
    public String toString() {
        return accountNo + "," + accountName + "," + 
                Utils.formatDate(openDate, "dd-MM-yyyy") + ","  + "VIP" + ","
                + String.valueOf(balance) + "," 
                + String.valueOf(cashLimit);
    }
}
