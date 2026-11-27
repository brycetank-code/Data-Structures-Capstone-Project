package ecommerce;

public class ProductCatalogArray {
    private static final int MAX_COMBINATIONS = 1_757_600;
    private final Product[] lookupTable = new Product[MAX_COMBINATIONS];
    
    public ProductCatalogArray(Product[] inventory) {
        for (Product product : inventory) {
            if (product != null) {
                int index = encodeId(product.getId());
                if (index != -1) {
                    this.lookupTable[index] = product;
                }
            }
        }
    }
    
    public Product findById(String id) {
        int index = encodeId(id);
        return index != -1 ? lookupTable[index] : null;
    }
    
    private int encodeId (String id) {
        if (id == null || id.length() != 6 || id.charAt(3) != '-') {
            return -1;
        }
        
        char c1 = Character.toUpperCase(id.charAt(0));
        char c2 = Character.toUpperCase(id.charAt(1));
        char c3 = Character.toUpperCase(id.charAt(2));
        char d1 = id.charAt(4);
        char d2 = id.charAt(5);
        
        if (c1 < 'A' || c1 > 'Z' || c2 < 'A' || c2 > 'Z' || c3 < 'A' || c3 > 'Z'
                || d1 < '0' || d1 > '9' || d2 < '0' || d2 > '9') {
            return -1;
        }
        
        int letterValue = ((c1 - 'A') * 676) + ((c2 - 'A') * 26) + (c3 - 'A');
        
        int numberValue = ((d1 - '0') * 10) + (d2 - '0');
        
        return (letterValue * 100) + numberValue;
    }   
}  