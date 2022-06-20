/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.controller;

import bankaccount.common.Utils;
import bankaccount.model.Account;
import bankaccount.model.CreditAccount;
import bankaccount.model.Customer;
import bankaccount.model.NormalSpendAccount;
import bankaccount.model.VipSpendAccount;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountController {
    private static final String FILE_ACCOUNT_PATH = "D:\\Freelancer\\BankAccount\\data\\account.txt";
    
    private CustomerController customerController;
    
    public AccountController() {
        customerController = new CustomerController();
    }
    
    public List<Account> getAllAccounts() {
        try {
            List<String> result = Utils.readFile(FILE_ACCOUNT_PATH);
            List<Account> accountList = getAccountList(result);
            return accountList;
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public boolean checkExistAccount(Account account) {
        try {
            List<String> result = Utils.readFile(FILE_ACCOUNT_PATH);
            List<Account> accountList = getAccountList(result);
            for (Account acc : accountList) {
                if (account.getAccountNo().equals(acc.getAccountNo())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } 
    }

    private List<Account> getAccountList(List<String> result) throws ParseException {
        List<Account> accountList = new ArrayList<>();
        for (String line : result) {
            String[] res = line.split(",");
            String accountNo = res[0];
            String accountName = res[1];
            Date openDate = Utils.parseToDate(res[2], "dd-MM-yyyy");
            String accountType = res[3];
            Account account;
            if ("NORMAL".equalsIgnoreCase(accountType)) {
                BigDecimal balance = new BigDecimal(res[4]);
                BigDecimal cashLimit = new BigDecimal(res[5]);
                account = new NormalSpendAccount(balance, cashLimit, accountNo, accountName, openDate);
            } else if ("VIP".equalsIgnoreCase(accountType)){
                BigDecimal balance = new BigDecimal(res[4]);
                BigDecimal cashLimit = new BigDecimal(res[5]);
                account = new VipSpendAccount(balance, cashLimit, accountNo, accountName, openDate);
            } else {
                BigDecimal creditLimit = new BigDecimal(res[3]);
                account = new CreditAccount(creditLimit, accountNo, accountName, openDate);
            }
            accountList.add(account);
        }
        return accountList;
    }
    
    private void printAccountListToFile(List<Account> accountList) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_ACCOUNT_PATH));
            for (Account account : accountList) {
                String data = account.toString();
                writer.write(data);
                writer.write("\r\n");
            }
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean add(Account account) {
        try {
            List<Account> accList = getAllAccounts();
            accList.add(account);
            printAccountListToFile(accList);
            return true;
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } 
    }

    public List<Account> search(String accountNo, String accountName) {
        List<Account> accounts = getAllAccounts();
        List<Account> returnAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (!accountNo.isEmpty() && !accountName.isEmpty()) {
                if (account.getAccountNo().contains(accountNo) && account.getAccountName().contains(accountName)) {
                    returnAccounts.add(account);
                }
            } else if (!accountNo.isEmpty()) {
                if (account.getAccountNo().contains(accountNo)) {
                    returnAccounts.add(account);
                }
            } else if (!accountName.isEmpty()) {
                if (account.getAccountName().contains(accountName)) {
                    returnAccounts.add(account);
                }
            }
            
        }
        return returnAccounts;
    }

    public boolean update(Account account, String oldAccountNo) {
        try {
            List<Account> accList = getAllAccounts();
            for (Account acc : accList) {
                if (oldAccountNo.equals(acc.getAccountNo())) {
                    acc.setAccountNo(account.getAccountNo());
                    acc.setAccountName(account.getAccountName());
                    acc.setOpenDate(account.getOpenDate());
                    if (account instanceof NormalSpendAccount) {
                        NormalSpendAccount normalSpendAccount = (NormalSpendAccount) account;
                        normalSpendAccount.setCashLimit(normalSpendAccount.getCashLimit());
                    } else if (account instanceof VipSpendAccount) {
                        VipSpendAccount vipSpendAccount = (VipSpendAccount) account;
                        vipSpendAccount.setCashLimit(vipSpendAccount.getCashLimit());
                    } else {
                        CreditAccount creditAccount = (CreditAccount) account;
                        creditAccount.setCreditLimit(creditAccount.getCreditLimit());
                    }
                    break;
                }
            }
            customerController.update(account, oldAccountNo);
            printAccountListToFile(accList);
            
            return true;
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } 
    }

    public boolean remove(Account account) {
        try {
            List<Account> accList = getAllAccounts();
            for (int i = 0; i < accList.size(); i++) {
                if (account.getAccountNo().equals(accList.get(i).getAccountNo())) {
                    accList.remove(i);
                    break;
                }
            }
            customerController.remove(account);
            return true;
        } catch (Exception e) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } 
    }
}