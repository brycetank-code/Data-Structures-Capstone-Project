package ecommerce;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;


public class App {

    public static void main( String[] args ){
        Scanner scan = new Scanner(System.in);
        Product[] inventory = generateData(1000);

        System.out.println("Welcome to EZ Shopping, the best place to find any product by any metric that your heart desires! We have a wide variety of products, and we can help you find the best ones based on your preferences.");
        System.out.println("We have " + inventory.length + " products in our inventory. How would you like to search for products?");
        System.out.println("To search products by ratiings, enter \"r\"."
                            + "To search products by price, enter \"p\"."
                            + "To search products by name, enter \"n\".");
        
        String searchChoice = scan.nextLine();
        
        switch (searchChoice){
            case "r":
                System.out.println("You have chosen to search products by ratings."
                    + "To search for the worse-rated products, enter \"w\"."
                    + "To search for the best-rated products, enter \"b\".");
                String ratingChoice = scan.nextLine();
                if (ratingChoice.equalsIgnoreCase("w")){
                    System.out.println("You have chosen to search for the worst-rated products. How many would you like to see? (Please enter a number between 1 and " + inventory.length + ").");
                    int numProducts = scan.nextInt();
                    if (numProducts < 1 || numProducts > inventory.length){
                        System.out.println("Invalid number of products. Redirected you back to the main menu...\n");
                        break;
                    }
                }
                else if (ratingChoice.equalsIgnoreCase("b")){
                    System.out.println("You have chosen to search for the best-rated products. How many would you like to see? (Please enter a number between 1 and " + inventory.length + ").");
                }
                else{
                    System.out.println("Invalid choice. Please restart the application and choose a valid option.");
                    break;
                }
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