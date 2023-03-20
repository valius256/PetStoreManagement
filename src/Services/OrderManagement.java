/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Main.CompareType;
import Model.Order;
import Model.OrderDetail;
import Model.Pet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import utils.Util;

public class OrderManagement {
    
    private static final String File_Path = "Orders.txt";
    
    private class CompareASCOrderByDate implements Comparator<Order> {
        
        @Override
        public int compare(Order o1, Order o2) {
            return o1.getOrderDate().compareTo(o2.getOrderDate());
        }
    }
    
    private class CompareDESCOrderByDate implements Comparator<Order> {
        
        @Override
        public int compare(Order o1, Order o2) {
            return o2.getOrderDate().compareTo(o1.getOrderDate());
        }
    }

    // orderMap - Lưu trữ danh sách Order theo từng tháng-năm
    // key - year month
    // value - order lít
    private static final OrderManagement instance = new OrderManagement();
    
    public static OrderManagement getInstance() {
        return instance;
    }
    private Map<YearMonth, List<Order>> orderMap;
    
    public OrderManagement() {
        this.orderMap = new HashMap<>();
    }
    List<Order> listOrders;
    
    public boolean inputNewOrder() {
        String id = Order.inpurId();
        Order order = getOrdersById(id);
        
        if (order == null) {
            order = new Order();
            order.setOrderId(id);
            order.input();
            addOrders(order);
//            Date orDate = order.getOrderDate();
//            YearMonth ym = YearMonth.of(orDate.getYear(), orDate.getMonth());
//            List<Order> orList = this.orderMap.get(ym);
//            if (orList == null) {
//                orList = new ArrayList();
//                this.orderMap.put(ym, orList);
//            }
//            if (!orList.contains(order)) {
//                return orList.add(order);
//            }
        }
        return false;
    }
    
    public List<Order> getSortedList(CompareType type) {
        switch (type) {
            case COMPARE_ASC_BY_DATE:
                return this.orderMap.values().stream().flatMap(e -> e.stream()).sorted(new CompareASCOrderByDate()).collect(Collectors.toList());
            case COMPARE_DESC_BY_DATE:
                return this.orderMap.values().stream().flatMap(e -> e.stream()).sorted(new CompareDESCOrderByDate()).collect(Collectors.toList());
            
            default:
                throw new AssertionError();
        }
    }
    
    public void showbyDate() {
        int idx = 0;
        for (CompareType value : CompareType.values()) {
            System.out.println(idx++ + ": " + value);
        }
        idx = Util.inputInteger("Select index item", 0, CompareType.values().length - 1);
        System.out.println("--------------------------------------------------------------");
        int count = 0;
        double totalPrice = 0;
        for (Order order : getSortedList(CompareType.values()[idx])) {
            System.out.println(order);
        }
        System.out.println("--------------------------------------------------------------");
        
    }
    
    public Order getOrdersById(String orderID) {
        if (orderID != null && !orderID.isBlank()) {
            for (List<Order> orderList : this.orderMap.values()) {
                for (Order orders : orderList) {
                    if (orderID.equalsIgnoreCase(orders.getOrderId())) {
                        return orders;
                    }
                }
            }
        }
        return null;
    }
    
    public void saveOrdersToFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(File_Path))) {
            for (List<Order> orderList : orderMap.values()) {
                for (Order order : orderList) {
                    pw.println(order.toString());
                }
            }
            System.out.println("Success");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void LoadDataFromFile() {
        try {
            FileReader fr = new FileReader(File_Path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                String id = arr[0].trim();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = Util.toDate(arr[1].trim());
                String CustomerName = arr[2].trim();
                String petId = arr[3].trim();
                // khong parse dc ca cai orderdetailist len nay
                Pet ord = PetManagement.getInstance().getPetById(petId);
                int quantity = Integer.MIN_VALUE;
                try {
                    quantity = Integer.parseInt(arr[4]);
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                OrderDetail orde = new OrderDetail(petId, quantity);
                List<OrderDetail> orderList = new ArrayList();
                orderList.add(orde);
                Order order = new Order(petId, date, CustomerName, orderList);
                
                addOrders(order);
            }
            br.close();
            fr.close();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
    
    public void addOrders(Order order) {
        Date orDate = order.getOrderDate();
        YearMonth ym = YearMonth.of(orDate.getYear(), orDate.getMonth());
        List<Order> orderList = orderMap.get(ym);
        if (orderList == null) {
            orderList = new ArrayList();
//            YearMonth ym = YearMonth.of(orDate.getYear(), orDate.getMonth());
//            this.orderMap.get(ym);
            orderMap.put(ym, orderList);
        }
        Order existOrder = null;
        for (Order order1 : orderList) {
            if (order.equals(order1)) {
                existOrder = order1;
                break;
            }
        }
        if (existOrder == null) {
            orderList.add(order);
        } else {
//            order.inpuUpdate();
            existOrder.setOrderDetailList(order.getOrderDetailList());
        }
    }
    
}
