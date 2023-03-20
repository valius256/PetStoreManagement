/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Quang Phat
 */
public enum Category {
    PARROT("PARROT"),
    CAT("CAT"),
    DOG("DOG");

    private final String label;

    private Category() {
        this.label = null;
    }
    
    public String getLabel() {
        return label;
    }

    private Category(String label) {
        this.label = label;
    }

}
