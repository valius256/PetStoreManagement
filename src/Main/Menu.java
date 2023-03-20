package Main;


import utils.Util;


public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.Pet,
        MenuItem.Order,
    };

    private final MenuItem[] petOptions = {
        MenuItem.BACK,
        MenuItem.Pet_Add,
        MenuItem.Pet_Find,
        MenuItem.Pet_Update,
        MenuItem.Pet_Delete,
        MenuItem.Pet_PrintAll,
        MenuItem.Pet_SaveToFile,
        MenuItem.Pet_LoadFromFile,
    };

    private final MenuItem[] OrderOptions = {
        MenuItem.BACK,
        MenuItem.Order_AddOrder,
        MenuItem.Order_List,
        MenuItem.Order_SaveToFile,
        MenuItem.Order_LoadFromFile,
    };

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }


   public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                if (!isRepeatAction()) {
                    subOption = getChoice(primaryOption);
                }
              
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Order Management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);

        return optionList[choice];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        for (int i = 1; i < optionList.length; i++) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case Pet ->
                    petOptions;
                case Order ->
                    OrderOptions;
                default ->
                    primaryOptions;
            };
        }

        return optionList;
    }

    private boolean isRepeatAction() {
        switch (subOption) {
            case Pet_Add:
            case Pet_Find:
            case Pet_Update:
            case Pet_Delete:
                String confirm = Util.inputString("Repeat action (Y/N)", false);
                return confirm.trim().toLowerCase().startsWith("y");
        }
        return false;
    }

    private boolean needAdmin() {
        switch (subOption) {
//            case CUSTOMER_CREATE_NEW_CUSTOMER:
//            case CUSTOMER_UPDATE:
//            case ORDER_CREATE_NEW_ORDER:
//            case ORDER_DELETE:
//            case ORDER_UPDATE:
        }
        return false;
    }

}
