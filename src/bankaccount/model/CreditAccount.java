/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.model;

import bankaccount.common.Utils;
import java.math.BigDecimal;
import java.util.Date;

public class CreditAccount extends Account {
    private BigDecimal creditLimit;

    public CreditAccount() {
    }
    
    public CreditAccount(BigDecimal creditLimit, String accountNo, String accountName, Date openDate) {
        super(accountNo, accountName, openDate);
        this.creditLimit = creditLimit;
    }

    public CreditAccount(String accountNo, String accountName, Date openDate) {
        super(accountNo, accountName, openDate);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String deposit(BigDecimal amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String withdraw(BigDecimal amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return accountNo + "," + accountName + "," + 
                Utils.formatDate(openDate, "dd-MM-yyyy") + "," 
                + String.valueOf(creditLimit);
    }
    
}
