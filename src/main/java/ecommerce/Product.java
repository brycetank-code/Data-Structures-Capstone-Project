package ecommerce;
import java.math.BigDecimal;

//Gives every product 3 attributes; price and rating are random while id is incremental
class Product{
    String id;
    BigDecimal price;
    BigDecimal rating;

    Product(String id, BigDecimal price, BigDecimal rating){
        this.id = id;
        this.price = price;
        this.rating = rating;
    }
}