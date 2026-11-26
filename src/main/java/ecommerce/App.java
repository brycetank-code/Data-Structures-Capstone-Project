package ecommerce;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;


public class App {

    public static void main( String[] args ){
        Product[] inventory = generateData(1000);

        HighestRatings top = new HighestRatings(10);
        for (Product p : inventory) {
            top.search(p);
        }

        for (int i = 0; i < inventory.length; i++){
            priceMap.put(inventory[i].id, inventory[i].price);
        }




        for (int i = 0; i < top.size; i++) {
            Product p = top.topRated[i];
            System.out.println("ID: " + p.id + 
                            ", Price: " + p.price + 
                            ", Rating: " + p.rating);
        }

    }

    public static Product[] generateData(int dataSize){
        Product [] data = new Product[dataSize];
        Random rand = new Random();

        for (int i=0; i<dataSize; i++){
            BigInteger id = BigInteger.valueOf(i);
            BigDecimal price = BigDecimal.valueOf(rand.nextInt(30001), 2);
            BigDecimal rating = BigDecimal.valueOf(rand.nextInt(51), 1);

            data[i] = new Product(id, price, rating);
        }
        return data;

    }
}




public class MaximumHeap {
   public static void main(String[] args) {
       int[] insertionOrder = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};
       int[] heap = new int[insertionOrder.length];
       int size = 0;

       // 0-based indexing
       for (int value : insertionOrder) {
           heap[size] = value;
           int current = size;
           size++;

           // percolate up for a max heap
           while (current > 0) {
               int parent = (current - 1) / 2;
               // if the parent is greater than or equal to the value of the latest added index, we are done
               if (heap[parent] >= heap[current]) {
                   break;
               }

               // otherwise, swap the parent and current and parent index values and continue percolating up
               int temp = heap[parent];
               heap[parent] = heap[current];
               heap[current] = temp;
               current = parent;
           }

           // print the current state of the heap after each insertion
           for (int i = 0; i < size; i++) {
               System.out.print(heap[i] + " ");
           }
           System.out.println();
       }
   }
}