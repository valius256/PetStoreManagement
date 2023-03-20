/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Category;
import Model.Pet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PetManagement {

    private static final PetManagement instance = new PetManagement();
    private static final String FILE_PATH = "Pet.txt";

    public static PetManagement getInstance() {
        return instance;
    }
    // key la category kieu enum : cat/ dog / parrot 
    // moi 1 cai Category nay la 1 list 
    // voi cate la dog: thi danh sach cua no toan la nhung con cho 
    // cat vs parrot cung v 
    // co 3 danh sach 

    private final Map<Category, List<Pet>> petMap;

    public PetManagement() {
        this.petMap = new HashMap();
    }

    public List<Pet> getpetlistBycategory(Category category) {
        return category != null ? this.petMap.get(category) : null;
    }
//    List<Pet> list;
//
//    public Map<Enum<Category>, List<Pet>> getPetMap() {
//        return petMap;
//    }

    public void addPet() {
        Pet pet = new Pet();

        List<Pet> petList = petMap.get(pet.getCategory());

        if (petList == null) {
            petList = new ArrayList();
            Category cate = pet.inputCategory();
            petMap.put(cate, petList);
        }

        pet.input();
        if (!petList.contains(pet)) {
            petList.add(pet);
            appendToFile(FILE_PATH, pet);
            System.out.println(pet);
        }

    }

    public void addPetFromFileToPetList(Pet pet) {
        List<Pet> petList = petMap.get(pet.getCategory());
        if (petList == null) {
            petList = new ArrayList();
            petMap.put(pet.getCategory(), petList);
        }
        petList.add(pet);
    }

    private boolean checkDuplicate(String Id) {
        if (petMap.containsKey(Category.valueOf(Id))) {
            return true;
        }
        return false;
    }

    public void searchPet() {
        String categoryStr = utils.Util.inputString("Enter the Category to find(DOG, CAT, PARROT): ", false);
        Category category = Category.valueOf(categoryStr.toUpperCase());

        List<Pet> pets = petMap.get(category);
        for (Pet pet : pets) {
            if (pets != null) {
                String id = utils.Util.inputString("Enter The ID: ", true);
                if (pet.getId() == null ? id == null : pet.getId().equals(id)) {
                    System.out.println("Pet found: " + pet);
                    return;
                }
            }
        }
    }

    public void updatePet() {
        String categoryStr = utils.Util.inputString("Enter the Category to find(DOG, Cat, PARROT): ", false);
        Category category = Category.valueOf(categoryStr.toUpperCase());

        List<Pet> pets = petMap.get(category);
        for (Pet pet : pets) {
            if (pets != null) {
                String id = utils.Util.inputString("Enter The ID: ", true);
                if (pet.getId() == null ? id == null : pet.getId().equals(id)) {
                    System.out.println("Pet found: " + pet);
                    pet.inputUpdate();
                    System.out.println("After update:");
                    System.out.println(pet);
                    return;
                }
            }
        }
    }

    public void deletePet() {
        String categoryStr = utils.Util.inputString("Enter the Category to find(DOG, Cat, PARROT): ", false);
        Category category = Category.valueOf(categoryStr.toUpperCase());

        List<Pet> pets = petMap.get(category);
        int i = 0;
        while (true) {
            Pet pet = pets.get(i++);
            if (pet != null) {
                String id = utils.Util.inputString("Enter The ID: ", true);
                if (pet.getId() == null ? id == null : pet.getId().equals(id)) {
                    System.out.println("The pet is not in order.");
                    pets.remove(pet);
                    System.out.println("Delete success");
                    System.out.println(pets);
                    break;
                }

            }
        }
    }

    public void printAll() {
        for (List<Pet> value : petMap.values()) { // value nay dang la 1 list chu ko phai 1 obj
            // dung vong for thu 2 de no tra ra tung object
            for (Pet pet : value) {
                System.out.println(pet);

            }
        }
    }

    public Pet getPetById(String petID) {
        if (petID != null && !petID.isBlank()) {
            for (List<Pet> petList : this.petMap.values()) {
                for (Pet pet : petList) {
                    if (petID.equalsIgnoreCase(pet.getId())) {
                        return pet;
                    }
                }
            }
        }
        return null;
    }

    public void savePetsToFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (List<Pet> petList : petMap.values()) {
                for (Pet pet : petList) {
                    pw.println(pet.toString());
                }
            }
            System.out.println("Success");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void readFromFile() {
        try {
            FileReader fr = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                String id = arr[0].trim();
                String description = arr[1].trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(arr[2].trim());
                int unitPrice = Integer.parseInt(arr[3].trim());
                Category category = Category.valueOf(arr[4].trim());
//                this.add(new Orders(id, description, date, price, category));
                Pet p = new Pet(id, description, date, unitPrice, category);
                addPetFromFileToPetList(p);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            Logger.getLogger(PetManagement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void loadDataFromFile() {
        try {
            FileReader fr = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                String id = arr[0].trim();
                String description = arr[1].trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(arr[2].trim());
                int price = Integer.parseInt(arr[3].trim());
                Category category = Category.valueOf(arr[4].trim());
                Pet p = new Pet(id, description, date, price, category);
                addPetFromFileToPetList(p);
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException | ParseException e) {
            Logger.getLogger(PetManagement.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void appendToFile(String filename, Pet pet) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            writer.write(pet + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

}
