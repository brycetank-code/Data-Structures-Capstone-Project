package ecommerce;
import java.util.Scanner;
import java.math.BigDecimal;
import java.util.Random;


public class App {

    public static void main( String[] args ){
        Scanner scan = new Scanner(System.in);
        Product[] inventory = generateData(1000000);

        System.out.println("Welcome to EZ Shopping, the best place to find any product by any metric that your heart desires! We have a wide variety of products, and we can help you find the best ones based on your preferences.");

        String sentinel = "y";

        while (sentinel.equalsIgnoreCase("y")){
            System.out.println("We have " + inventory.length + " products in our inventory. How would you like to search for products?");
            System.out.println("To search products by ratiings, enter \"r\".\n"
                                + "To search products by price, enter \"p\".\n"
                                + "To search products by name, enter \"n\".");

            String searchChoice = scan.nextLine();
            System.out.println();

            switch (searchChoice){
                case "r":
                    System.out.println("You have chosen to search products by ratings.\n"
                        + "To search for the worse-rated products, enter \"w\".\n"
                        + "To search for the best-rated products, enter \"b\".");
                    String ratingChoice = scan.nextLine();
                    System.out.println();

                    //Input validation for ratingChoice
                    while(!ratingChoice.equalsIgnoreCase("w") && !ratingChoice.equalsIgnoreCase("b")) {
                        System.out.println("Invalid choice. Please enter \"w\" or \"b\".");
                        ratingChoice = scan.nextLine();
                        System.out.println();
                    }

                    if (ratingChoice.equalsIgnoreCase("w")){
                        System.out.println("You have chosen to search for the worst-rated products. How many would you like to see? (Please enter a number between 1 and " + inventory.length + ").");
                        int numProducts = scan.nextInt(); //Crashes if the user inputs a non-integer value
                        scan.nextLine(); //Consume the newline character
                        System.out.println();

                        //Input validation for numProducts
                        while (numProducts < 1 || numProducts > inventory.length){
                            System.out.println("Invalid number of products. Please enter a number between 1 and " + inventory.length + ".");
                            numProducts = scan.nextInt(); //Crashes if the user inputs a non-integer value
                            scan.nextLine(); //Consume the newline character
                            System.out.println();
                        }

                        //Using a minHeap for the worst-rated products
                        RatingsHeap heap = new RatingsHeap(numProducts, inventory);
                        //Start Timer; this is where we can measure efficiency of the algorithm; we can also swap it out for our RatingsArray class to compare the two
                        heap.maxHeap();
                        //End Timer
                        heap.print();
                    }
                    else if (ratingChoice.equalsIgnoreCase("b")){
                        System.out.println("You have chosen to search for the best-rated products. How many would you like to see? (Please enter a number between 1 and " + inventory.length + ").");
                        int numProducts = scan.nextInt(); //Crashes if the user inputs a non-integer value
                        scan.nextLine(); //Consume the newline character
                        System.out.println();

                        //Input validation for numProducts
                        while (numProducts < 1 || numProducts > inventory.length){
                            System.out.println("Invalid number of products. Please enter a number between 1 and " + inventory.length + ".");
                            numProducts = scan.nextInt(); //Crashes if the user inputs a non-integer value
                            scan.nextLine(); //Consume the newline character
                            System.out.println();
                        }

                        //Using a maxHeap for the best-rated products
                        RatingsHeap heap = new RatingsHeap(numProducts, inventory);
                        //Start Timer
                        heap.minHeap();
                        //End Timer
                        heap.print();
                    }

                case "p":
                    //Brenden's code for searching by price

                case "n":
                    //Code for searching by name/ID
            }

            System.out.println("\nTo search again, enter \"y\".\n"
                                + "To exit, enter \"n\".");
            
            sentinel = scan.nextLine();
            System.out.println();
        }

        //Reached when sentinel is not "y"
        System.out.println("Thank you for using EZ Shopping! We hope you found what you were looking for.");
        System.out.println("Program terminated.");
        scan.close();
    }


    //Generates an array of products with random prices and ratings, and incremental IDs
    public static Product[] generateData(int dataSize){
        Product [] data = new Product[dataSize];
        Random rand = new Random();

        for (int i=0; i<dataSize; i++){
            String id = toId(i);
            BigDecimal price = BigDecimal.valueOf(rand.nextInt(30001), 2);
            BigDecimal rating = BigDecimal.valueOf(rand.nextInt(101), 1);

            data[i] = new Product(id, price, rating);
        }
        return data;
    }

    //ID format is AAA-00 to ZZZ-99, where the letters are incremented first and then the numbers
    private static String toId(int i) {
        int num = i % 100;
        int letters = i / 100;
        char c = (char) ('A' + letters % 26);
        char b = (char) ('A' + letters / 26 % 26);
        char a = (char) ('A' + letters / 26 / 26 % 26);
        return "" + a + b + c + "-" + String.format("%02d", num);
    }
}