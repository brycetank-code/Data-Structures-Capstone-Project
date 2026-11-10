package ecommerce;
import java.math.BigDecimal;
import java.math.BigInteger;

//Gives every product 3 attributes; price and rating are random while id is incremental
class Product{
    BigInteger id;
    BigDecimal price;
    BigDecimal rating;

    Product(BigInteger id, BigDecimal price, BigDecimal rating){
        this.id = id;
        this.price = price;
        this.rating = rating;
    }
}