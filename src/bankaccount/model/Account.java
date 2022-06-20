/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.model;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Account {
    protected String accountNo;
    protected String accountName;
    protected Date openDate;

    public Account() {
    }

    public Account(String accountNo, String accountName, Date openDate) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.openDate = openDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    
    public abstract String deposit(BigDecimal amount);
    public abstract String withdraw(BigDecimal amount);
    
    @Override
    public abstract String toString();
}
