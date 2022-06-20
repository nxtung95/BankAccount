/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankaccount.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public static List<String> readFile(String filePath) throws Exception {
        BufferedReader br = null;
        List<String> result = new ArrayList<>();
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
                return result;
            } 
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public static Date parseToDate(String date, String format) throws ParseException {
        try {  
            Date date1 = new SimpleDateFormat(format).parse(date);
            return date1;
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public static String formatDate(Date date, String format) {
        String strDate = new SimpleDateFormat(format).format(date);
        return strDate;
    }
    
    public static String customCurrency(String pattern, String value) {
        if (value == null || value.equals("")) {
            return "0";
        }
        try {
            value = value.replace(",", "").trim();
            DecimalFormat myFormatter = new DecimalFormat(pattern);
            String output = myFormatter.format(Double.valueOf(value));
            return output;
        } catch (NumberFormatException e) {
            return value;
        }
    }
}
