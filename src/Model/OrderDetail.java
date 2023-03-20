/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Quang Phat
 */
public class OrderDetail {

    private String petID;
    private int quantity;

    public OrderDetail(String petId, int quantity) {
        this.petID = petId;
        this.quantity = quantity;
    }

    public OrderDetail() {
    }

    public String getPet() {
        return petID;
    }

    public void setPet(String pet) {
        if (pet != null) {
            this.petID = pet;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(petID);
        sb.append(",").append(quantity);
        return sb.toString();
    }
}
