package ecommerce;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Product not found for ID: " + id);    
    }
}
