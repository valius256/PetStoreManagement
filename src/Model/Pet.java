/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import utils.Util;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//The application requires inputting a pet: id, description, import date, unit price, and category. − The system will check the data validation with the following conditions:
//▪ All fields are not allowed null.
//▪ The id field must be unique.
//▪ The length of the description field must be from 3 to 50 characters. ▪ The import date field must be a valid date.
//▪ The unit price field must be a positive number.
//▪ The category field must be one of the following: Cat, Dog, and Parrot
public class Pet {

    private String id;
    private String description;
    private Date date;
    private int unitPrice;
    private Category category;

    public static String getAtrributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order");
        sb.append(Util.SEPARATOR).append("Pet Id");
        sb.append(Util.SEPARATOR).append("Description");
        sb.append(Util.SEPARATOR).append("Date");
        sb.append(Util.SEPARATOR).append("Unit Price ");
        sb.append(Util.SEPARATOR).append("Category");
        return sb.toString();
    }

    public Pet() {
    }

    public Pet(String id, String description, Date date, int unitPrice, Category category) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        if (description.length() >= 5 && description.length() <= 30) {
            this.description = description;
        }
    }

    public Date getDate() {
        return date;
    }

    public final void setDate(Date date) {
        if (date != null && !date.after(new Date())) {
            this.date = date;
        }
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public final void setUnitPrice(int unitPrice) {

        this.unitPrice = unitPrice;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void input() {
        while (true) {
            try {
                setId(Util.inputString("Enter id", false).toUpperCase().trim());
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setDescription(Util.inputString("Enter description", false).toUpperCase().trim());
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setDate(Util.inputDate("Enter the date", true));
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setUnitPrice(Util.inputInt("Enter the unitPrice: "));
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                String cate = Util.inputString("Enter the category", false);
                setCategory(Category.valueOf(cate.toUpperCase()));
                break;
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Category inputCategory() {
        String cate = Util.inputString("Enter the category", false);
        return Category.valueOf(cate.toUpperCase().trim());
    }

    public void inputUpdate() {
        /*
            in update vi key la pet.category nen ta update dc id , des, ... nhung khong update category dc 
        
         */

        do {
            String id = Util.inputString("Enter the new pet ID", true);
            if (!id.isEmpty()) {
                if (Util.checkPetId(id)) {
                    setId(id);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        do {
            String description = Util.inputString("Enter the new pet description", true);
            if (!description.isEmpty()) {
                if (Util.checkPetDescription(description)) {
                    setDescription(description);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        do {
            Date date = Util.inputDate("Enter the new  Date", true);
            if (true) {
                setDate(date);
                break;
            }
        } while (true);

        do {
            int unitprice = Util.inputInt("Enter the new unitPrice: ");
            if (unitprice >= 0) {
                setUnitPrice(unitprice);
                break;
            } else {
                System.out.println("error");
            }
        } while (true);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",");
        sb.append(description).append(",");
        sb.append(Util.convertDate(date)).append(",");
        sb.append(unitPrice).append(",");
        sb.append(category).append(",");
        return sb.toString();
    }

}
