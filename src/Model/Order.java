/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Services.PetManagement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Util;

public class Order {
    
    private String orderId;
    private Date orderDate;
    private String customerName;
    private List<OrderDetail> orderDetailList;
    
    public Order() {
        this.orderId = "";
        this.orderDate = null;
        this.customerName = "";
        this.orderDetailList = new ArrayList();
    }
    
    public Order(String orderId, Date orderDate, String customerName, List<OrderDetail> orderDetailList) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCustomerName(customerName);
        this.orderDetailList = new ArrayList();
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public final void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public final void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public final void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }
    
    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        if (orderDetailList != null) {
            this.orderDetailList.addAll(orderDetailList);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrderDetail orderDetail : orderDetailList) {
            sb.append("\n");
            sb.append(orderId);
            sb.append(",").append(Util.toString(orderDate));
            sb.append(",").append(customerName);
            sb.append(",").append(orderDetail.toString());
        }
        return sb.toString().substring(1);
    }
    
    public String getMonthAndYearFromInputDate() {
        int month = getOrderDate().getMonth();
        int year = getOrderDate().getYear();
        return Integer.toString(month + year);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Order) {
            return this.orderId.equalsIgnoreCase(((Order) obj).orderId);
        }
        return super.equals(obj);
    }
    
    public static String inpurId() {
        String id = null;
        while (true) {
            try {
                id = Util.inputString("Enter id", false).toUpperCase().trim();
                break;
            } catch (Exception ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public void input() {
        if (this.orderId == null) {
            setOrderId(Order.inpurId());
        }
        while (true) {
            try {
                setOrderDate(Util.inputDate("Enter date: "));
                break;
            } catch (Exception ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while (true) {
            try {
                setCustomerName(Util.inputString("Enter the customer name: ", false).toUpperCase().trim());
                break;
            } catch (Exception ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // petList
        System.out.println("Pet List:");
        PetManagement.getInstance().loadDataFromFile();
        PetManagement.getInstance().printAll();
        String petID;
        do {
            
            petID = Util.inputString("Input pet's id", true);
            Pet pet = PetManagement.getInstance().getPetById(petID);
            if (pet != null) {
//                Orders pet = new Orders();
//                pet.input();
                int quantity = Util.inputInteger("Input pet quantity", 0, Integer.MAX_VALUE);
                this.orderDetailList.add(new OrderDetail(petID, quantity));
                
            } else {
                System.out.println("Product not found.");
            }
        } while (petID.isBlank() || this.orderDetailList.isEmpty());
        
    }
    
    public static Comparator<Order> compareByOrderDate = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return o1.getOrderDate().compareTo(o2.getOrderDate());
        }
    };
    
    public void inpuUpdate() {
        do {
            String id = Util.inputString("Enter the new orderId ", true);
            if (!id.isEmpty()) {
                if (Util.checkOrderId(id)) {
                    setOrderId(id);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);
        
        do {
            Date date = Util.inputDate("Enter the new orderDate ", true);
            if (true) {
                setOrderDate(date);
                break;
            }
        } while (true);
        
        do {
            String name = Util.inputString("Enter the new customer name", true);
            if (!name.isEmpty()) {
                if (Util.checkCustomerName(name)) {
                    setCustomerName(name);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);
        
        String petID;
        do {
            
            petID = Util.inputString("Input pet's id", true);
            Pet pet = PetManagement.getInstance().getPetById(petID);
            if (pet != null) {
//                Orders pet = new Orders();
//                pet.input();
                int quantity = Util.inputInteger("Input pet quantity", Integer.MIN_VALUE, Integer.MAX_VALUE);
                
                for (OrderDetail orderDetail : orderDetailList) {
                    if (petID == orderDetail.getPet()) {
                        quantity += orderDetail.getQuantity();
                        orderDetail.setQuantity(quantity);
                    } else {
                        this.orderDetailList.add(new OrderDetail(petID, quantity));
                    }
                }
                
            } else {
                System.out.println("Product not found.");
            }
        } while (petID.isBlank() || this.orderDetailList.isEmpty());
    }
    
}
