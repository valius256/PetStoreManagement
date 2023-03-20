package Main;

/**
 * Menu item.
 *
 * @author hasu
 */
public enum MenuItem {       
    BACK("Back", UserRole.USER),
    EXIT("Exit", UserRole.USER),
    Pet("PET", UserRole.USER),
    Pet_Add("Add new Pet(dog/cat/parrot): ", UserRole.ADMIN),
    Pet_Find("Find pet by ID", UserRole.USER), // user
    Pet_Update("Update a Pet", UserRole.ADMIN),
    Pet_Delete("Delete a pet", UserRole.ADMIN),
    Pet_SaveToFile("Save Data to File", UserRole.ADMIN),
    Pet_LoadFromFile("Load From File", UserRole.ADMIN),
    Pet_PrintAll("Print All", UserRole.USER), // user 
    //...

    Order("Order", UserRole.USER),
    Order_AddOrder("Add an order", UserRole.USER),
    Order_List("List Orders", UserRole.USER), // user 
    Order_Sort("Sort an Orders", UserRole.USER), // user 
    Order_SaveToFile("Save Data to File", UserRole.ADMIN),
    Order_LoadFromFile("LoadFromFile", UserRole.ADMIN),
    
    
    ;

    private final String label;
    private final UserRole userRole;

    public String getLabel() {
        return label;
    }

    private MenuItem(String label, UserRole userRole) {
        this.label = label;
        this.userRole  = userRole;
    }

}
