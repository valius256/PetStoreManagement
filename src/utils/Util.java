/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang Phat
 */
public class Util {

    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "dd/MM/yyyy"; // xem docs cua SimpleDateFormat 

    public static Date toDate(String strDate) throws ParseException {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        df.setLenient(false);
        return df.parse(strDate);
    }

    public static String toString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        return df.format(date);
    }

    public static Date inputDate(String message) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (date == null);
        return date;
    }

    public static boolean inputBoolean(String message) {
        System.out.print(message + "(" + Boolean.TRUE.toString() + "/" + Boolean.FALSE.toString() + "): ");
        Scanner sc = new Scanner(System.in);
        return Boolean.parseBoolean(sc.nextLine());
    }

    public static boolean validateDate(Date inputDate) {
        if (!inputDate.toString().matches(DATE_FORMAT)) {
            return false;
        }
        return true;
    }

    public static String inputString(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (!allowEmpty && str.isBlank());
//        } while (!allowEmpty && str.trim().isEmpty());
        return str.trim();
    }

    public static int inputInteger(String message, int minValue, int maxValue) {
        int val = minValue - 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static Date inputDate(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {

            }
        } while (date == null & !allowEmpty);
        return date;
    }

    public static int inputInt(String msg) {
        int data = 0;
        boolean flag;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                flag = false;
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(e);
                flag = true;
            }
        } while (flag);
        return data;
    }

    public static boolean checkPetDescription(String description) {
        if (description.length() > 5 && description.length() <= 30) {
            return true;
        }
        return false;
    }

    public static boolean checkPetId(String id) {
        if (!id.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String convertDate(Date d) {
        SimpleDateFormat da = new SimpleDateFormat(DATE_FORMAT);
        if (d != null) {
            return da.format(d);
        }
        return null;
    }

    public static String inputString(String message) {
        String inputString;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            inputString = sc.nextLine();
        } while (inputString.isBlank());
        return inputString;
    }

    public static boolean checkOrderId(String id) {
        if (!id.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public static boolean checkCustomerName(String name) {
        if (name.length() > 0) {
            return true;
        }
        return false;
    }


}
