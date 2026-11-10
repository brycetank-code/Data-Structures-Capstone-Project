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





