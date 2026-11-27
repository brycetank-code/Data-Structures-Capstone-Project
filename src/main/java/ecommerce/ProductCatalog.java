package ecommerce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalog {
    
    private final Map<String,Product> catalog = new HashMap<>();
    
    public ProductCatalog(List<Product> products) {
        for (Product product : products) {
            if (product != null && product.getId() != null) {
                String cleaned = product.getId().trim().toUpperCase();
                if (isValidIdFormat(cleaned)) {
                    catalog.put(cleaned, product);
                }
            }
        }
    }
    
    private boolean isValidIdFormat(String id) {
        if (id == null || id.length() != 6) {
            return false;
        }
    
        for (int i = 0; i < 3; i++) {
            char c = id.charAt(i);
            if (c < 'A' || c > 'Z') {
                return false;
            }
        }
        
        if (id.charAt(3) != '-') {
            return false;
        }
        
        for (int i = 4; i < 6; i++) {
           if (!Character.isDigit(id.charAt(i))) {
               return false;
            }
        }
        return true;
    }
    
    public Product findById(String id) {
        if (id == null) {
            throw new ProductNotFoundException("null");
        }
        
        String cleaned = id.trim().toUpperCase();
        
        if (!isValidIdFormat(cleaned)) {
            throw new ProductNotFoundException(id);
        }
        
        Product product = catalog.get(cleaned);
        
        if (product == null) {
            throw new ProductNotFoundException(cleaned);
        }
        return product;
    }
}

