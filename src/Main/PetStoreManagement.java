/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Services.OrderManagement;
import Services.PetManagement;

import java.io.IOException;

public class PetStoreManagement {

    private PetManagement petManagement;
    private OrderManagement orderManagement;

    public PetStoreManagement() {
        this.petManagement = petManagement.getInstance();
        this.orderManagement = orderManagement.getInstance();
    }

    public static void load() {
        PetManagement.getInstance().loadDataFromFile();
        OrderManagement.getInstance().LoadDataFromFile();
    }

    private void addNewPet() {
        System.out.println("Add a pet");
        petManagement.addPet();
    }

    private void updatePet() {
        System.out.println("update Pet By Category: ");
        petManagement.updatePet();
    }

    private void deletePet() {
        System.out.println("delete Pet");
        petManagement.deletePet();
    }

    private void searchPet() {
        System.out.println("filterPetById");
        petManagement.searchPet();
    }

    private void printAllPet() {
        System.out.println("Print All");
        petManagement.printAll();
    }

    private void savePetToFile() throws IOException {
        System.out.println("Save to file");
        petManagement.savePetsToFile();
    }

    private void LoadfromFile() {
        System.out.println("Load data from file");
        petManagement.loadDataFromFile();
        petManagement.printAll();
    }

    // order
    private void addOrder() {
        System.out.println("Add an orders: ");
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN)) {
            orderManagement.inputNewOrder();
        }
    }

    private void showByDate() {
        System.out.println("Show by date");
        orderManagement.showbyDate();
    }

    private void saveOrderToFile() throws IOException {
        System.out.println("Save to file");
        orderManagement.saveOrdersToFile();
    }

    private void LoadOrdersFromFile() throws IOException {
        System.out.println("Load from file");
        orderManagement.LoadDataFromFile();
    }

    private void process() throws Exception {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                // pet
                case Pet_Add:
                    addNewPet();
                    break;
                case Pet_Find:
                    searchPet();
                    break;
                case Pet_Update:
                    updatePet();
                    break;
                case Pet_Delete:
                    deletePet();
                    break;
                case Pet_PrintAll:
                    printAllPet();
                    break;
                case Pet_SaveToFile:
                    savePetToFile();
                    break;
                case Pet_LoadFromFile:
                    LoadfromFile();
                    break;

                // order    
                case Order_AddOrder:
                    addOrder();
                    break;
                case Order_List:
                    showByDate();
                    break;
                case Order_SaveToFile:
                    saveOrderToFile();
                    break;
                case Order_LoadFromFile:
                    LoadOrdersFromFile();
                    break;

                case EXIT:
                    System.out.println("Exited!");
                    break;

                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    public void start() throws Exception {
        if (UserManagetment.getInstance().login()) {
            UserManagetment.getInstance().getCurrentUser().output();

            process();
        } else {
            System.out.println("Login failed!");
        }
    }

    public static void main(String[] args) throws Exception {
//        load();
        new PetStoreManagement().start();

    }

}
