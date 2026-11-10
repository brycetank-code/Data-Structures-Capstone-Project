package com.mycompany.orderedsearch;

import java.math.BigDecimal;
import java.util.*;

public class OrderedSearch {

   private TreeMap<BigDecimal, String> priceMap = new TreeMap<>();
   
   //Add item with key and value
   public void addItem(BigDecimal price, String item) {
       priceMap.put(price, item);
   }
   
    //Find item or return "Item not found."
   public String findByPrice(BigDecimal price) {
       return priceMap.getOrDefault(price, "Item not found.");
   }
   
    //Return the closest value above the minimum amount
   public Map.Entry<BigDecimal, String> getCeilingItem(BigDecimal minimum) {
       return priceMap.ceilingEntry(minimum);
   }
   
   //Find most expensive item available under the specified value
   public Map.Entry<BigDecimal, String> getFloorItem(BigDecimal maxBudget) {
       return priceMap.floorEntry(maxBudget);
   }
   
   //Returns all items within a price window
   public NavigableMap<BigDecimal, String> getPricesInRange(BigDecimal minPrice, 
           BigDecimal maxPrice) {
       //Parameters: lowKey, lowInclusive, highKey, highInclusive
       return priceMap.subMap(minPrice, true, maxPrice, true);
   }
   
   //Print all prices ascending
   public void printAllAscending() {
       for (Map.Entry<BigDecimal, String> entry : priceMap.entrySet()) {
           System.out.println(entry.getValue() + ": $" + 
               entry.getKey().toPlainString());
       }
   }
   
   //Print all prices descending
   public void printAllDescending() {
       NavigableMap<BigDecimal, String> reverseMap = priceMap.descendingMap();
       
       for (Map.Entry<BigDecimal, String> entry : reverseMap.entrySet()) {
           System.out.println(entry.getValue() + ": $" + 
               entry.getKey().toPlainString());
       }
   }
   
   public static void main(String[] args) {
       OrderedSearch inventory = new OrderedSearch();
       
       inventory.addItem(new BigDecimal("4.99"), "Card Pack");
       inventory.addItem(new BigDecimal("49.99"), "Booster Box");
       inventory.addItem(new BigDecimal("99.99"), "Elite Trainer Bundle");
       inventory.addItem(new BigDecimal("249.99"), "Ultra Premium Collection");
       
       //$50.00 budget
       var floor = inventory.getFloorItem(new BigDecimal("50.00"));
       System.out.println("Floor (<= $50.00): " + floor.getValue() + " $" + 
               floor.getKey());
       
       //Lowest item at or above $50.00
       var ceiling = inventory.getCeilingItem(new BigDecimal ("50.00"));
       System.out.println("Ceiling (>= $50.00): " + ceiling.getValue() + " $" + 
               ceiling.getKey());
       
       //Items between $45 and $100
       var range = inventory.getPricesInRange(new BigDecimal("45.00"),
               new BigDecimal ("100.00"));
       range.forEach((price,item) -> System.out.println(item + ": $" + price));
     
       System.out.println("-----Merchandise-----");
       inventory.printAllAscending();
       inventory.printAllDescending();
       
   }
}
