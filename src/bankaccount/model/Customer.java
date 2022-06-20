/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.model;

import bankaccount.common.Utils;
import java.util.Date;

public class Customer {
    private String id;
    private String name;
    private Date birthday;
    private String cccd;
    private String phoneNumber;
    private String email;
    private Account spendAccount;
    private Account creditAccount;

    public Customer() {
    }
    
    public Customer(String id, String name, Date birthday, String cccd, String phoneNumber, String email, Account spendAccount, Account creditAccount) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.cccd = cccd;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.spendAccount = spendAccount;
        this.creditAccount = creditAccount;
    }

    public Customer(String id, String name, Date birthday, String cccd, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.cccd = cccd;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getSpendAccount() {
        return spendAccount;
    }

    public void setSpendAccount(Account spendAccount) {
        this.spendAccount = spendAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    @Override
    public String toString() {
        String spendAccountNo = "";
        String type = "";
        if (spendAccount != null) {
            spendAccountNo = spendAccount.getAccountNo(); 
            if (spendAccount instanceof NormalSpendAccount) {
                type = "NORMAL";
            } else {
                type = "VIP";
            }
        }
        
        String creditAccountNo = creditAccount != null ? creditAccount.getAccountNo() : "";
        return id + "," + name + "," + Utils.formatDate(birthday, "dd-MM-yyyy") + "," + 
                cccd + "," + phoneNumber + "," + email + "," + type + "," +
                spendAccountNo + "," + creditAccountNo; //To change body of generated methods, choose Tools | Templates.
    }
    
}
