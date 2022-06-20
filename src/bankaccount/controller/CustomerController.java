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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerController {
    private static final String FILE_PATH = "D:\\Freelancer\\BankAccount\\data\\customer.txt";
    
    public List<Customer> getAllCustomer() {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            return cusList;
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public boolean update(Customer customer) {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            for (Customer cus : cusList) {
                if (customer.getId().equalsIgnoreCase(cus.getId())) {
                    cus.setName(customer.getName());
                    cus.setBirthday(customer.getBirthday());
                    cus.setPhoneNumber(customer.getPhoneNumber());
                    cus.setCccd(customer.getCccd());
                    cus.setEmail(customer.getEmail());
                    break;
                }
            }
            printCustomerListToFile(cusList);
            return true;
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Customer> getCustomerList(List<String> result) throws ParseException {
        List<Customer> cusList = new ArrayList<>();
        for (String line : result) {
            String[] res = line.split(",");
            String id = res[0];
            String name = res[1];
            Date birthday = Utils.parseToDate(res[2], "dd-MM-yyyy");
            String cccd = res[3];
            String phone = res[4];
            String email = res[5];
            Customer customer = new Customer(id, name, birthday, cccd, phone, email);
            String type = "";
            try {
                type = res[6]; 
            } catch (Exception e) {
                
            }           
            if (type != "" && type != null) {
                Account spendAccount;
                if ("NORMAL".equalsIgnoreCase(type)) {
                    spendAccount = new NormalSpendAccount();
                } else {
                    spendAccount = new VipSpendAccount();
                }
                String debitAccountNo = res[7];
                spendAccount.setAccountNo(debitAccountNo);
                customer.setSpendAccount(spendAccount);
            }
            String creditAccountNo = "";
            try {
                creditAccountNo = res[8];
            } catch (Exception e) {
                
            }
            if (creditAccountNo != null && creditAccountNo != "") {
                Account creditAccount = new CreditAccount();
                creditAccount.setAccountNo(creditAccountNo);
                customer.setCreditAccount(creditAccount);
            }
            cusList.add(customer);
        }
        return cusList;
    }

    public void printCustomerListToFile(List<Customer> cusList) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Customer customer : cusList) {
                String data = customer.toString();
                writer.write(data);
                writer.write("\r\n");
            }
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean remove(Customer customer) {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            for (int i = 0; i < cusList.size(); i++) {
                if (customer.getId().equalsIgnoreCase(cusList.get(i).getId())) {
                    cusList.remove(i);
                    break;
                }
            }
            printCustomerListToFile(cusList);
            return true;
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean add(Customer customer) {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            cusList.add(customer);
            printCustomerListToFile(cusList);
            return true;
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Customer> search(String cusId) {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            List<Customer> cusReturnList = new ArrayList<>();
            for (Customer customer : cusList) {
                if (customer.getId().contains(cusId)) {
                    cusReturnList.add(customer);
                }
            }
            return cusReturnList;
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<Customer> sortByName(String cusId, int type) {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            List<Customer> cusReturnList = new ArrayList<>();
            for (Customer customer : cusList) {
                if (customer.getId().contains(cusId)) {
                    cusReturnList.add(customer);
                }
            }
            Collections.sort(cusReturnList, (o1, o2) -> {
                int id1 = Integer.parseInt(o1.getId().substring(8));
                int id2 = Integer.parseInt(o2.getId().substring(8));
                if (type == 1) {
                    return id1 - id2;
                } else {
                    return id2 - id1;
                }
            });
            return cusReturnList;
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public String getNextCusId() {
        List<Customer> cusList = getAllCustomer();
        Customer lastCus = cusList.get(cusList.size() - 1);
        int cusId = Integer.parseInt(lastCus.getId().substring(8));
        return "CUSTOMER" + (cusId + 1);
    }

    public void update(Account acc, String oldAccountNo) {
        try {
            List<String> result = Utils.readFile(FILE_PATH);
            List<Customer> cusList = getCustomerList(result);
            for (Customer cus : cusList) {
                if (acc instanceof NormalSpendAccount) {
                    NormalSpendAccount newNormalAcc = (NormalSpendAccount) acc;
                    if (cus.getSpendAccount().getAccountNo().equals(oldAccountNo)) {
                        NormalSpendAccount originalNormalAcc = (NormalSpendAccount) cus.getSpendAccount();
                        originalNormalAcc.setAccountNo(acc.getAccountNo());
                        originalNormalAcc.setAccountName(acc.getAccountName());
                        originalNormalAcc.setOpenDate(acc.getOpenDate());
                        originalNormalAcc.setCashLimit(newNormalAcc.getCashLimit());
                        break;
                    }
                } else if (acc instanceof VipSpendAccount) {
                    VipSpendAccount newVipAcc = (VipSpendAccount) acc;
                    if (cus.getSpendAccount().getAccountNo().equals(oldAccountNo)) {
                        VipSpendAccount originalVipAcc = (VipSpendAccount) cus.getSpendAccount();
                        originalVipAcc.setAccountNo(acc.getAccountNo());
                        originalVipAcc.setAccountName(acc.getAccountName());
                        originalVipAcc.setOpenDate(acc.getOpenDate());
                        originalVipAcc.setCashLimit(newVipAcc.getCashLimit());
                        break;
                    }
                } else {
                    CreditAccount newCreditAccount = (CreditAccount) acc;
                    if (cus.getSpendAccount().getAccountNo().equals(oldAccountNo)) {
                        CreditAccount originalCreditAccount = (CreditAccount) cus.getCreditAccount();
                        originalCreditAccount.setAccountNo(acc.getAccountNo());
                        originalCreditAccount.setAccountName(acc.getAccountName());
                        originalCreditAccount.setOpenDate(acc.getOpenDate());
                        originalCreditAccount.setCreditLimit(newCreditAccount.getCreditLimit());
                        break;
                    }
                }
            }
            printCustomerListToFile(cusList);
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void remove(Account account) {
        try {
            List<Customer> cusList = getAllCustomer();
            for (int i = 0; i < cusList.size(); i++) {
                Customer cus = cusList.get(i);
                if (cus.getSpendAccount() != null) {
                    if (cus.getSpendAccount().getAccountNo().equals(account.getAccountNo())) {
                        cusList.remove(i);
                        break;
                    }
                } else if (cus.getCreditAccount()!= null) {
                    if (cus.getCreditAccount().getAccountNo().equals(account.getAccountNo())) {
                        cusList.remove(i);
                        break;
                    }
                }
            }
            printCustomerListToFile(cusList);
        } catch (Exception e) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
