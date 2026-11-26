package ecommerce;
import java.util.Arrays;

public class RatingsArray {
    Product[] allProducts;

    //Initialized allProducts and then sorts it using merge sort
    RatingsArray(Product[] tempProducts){
        this.allProducts = Arrays.copyOf(tempProducts, tempProducts.length);
        Product[] products = new Product[allProducts.length];
        mergeSort(0, allProducts.length - 1, products);
    }

    /*
    Splits the range [left, right] in half, sorts each half recursively,
    and then merges the two sorted halves back together. A range of one
    product is already sorted, so that is where the recursion stops
    */
    private void mergeSort(int left, int right, Product[] products){
        if (left >= right){
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(left, middle, products);
        mergeSort(middle + 1, right, products);
        merge(left, middle, right, products);
    }

    /*
    Merges two sorted halves, [left, middle] and [middle + 1, right], into one sorted range
    We compare the front product of each half and copy the lower rated one into products
    If the ratings are equal, we take from the left half first, which keeps the sort stable
    Once one half runs out, the rest of the other half is copied over, and then the
    merged range is copied from products back into allProducts. I did have to look this algorithm up
    */
    private void merge(int left, int middle, int right, Product[] products){
        int leftPosition = left;
        int rightPosition = middle + 1;
        int productsPosition = left;

        while (leftPosition <= middle && rightPosition <= right){
            if (allProducts[leftPosition].rating.compareTo(allProducts[rightPosition].rating) <= 0){
                products[productsPosition++] = allProducts[leftPosition++];
            }
            else{
                products[productsPosition++] = allProducts[rightPosition++];
            }
        }

        while (leftPosition <= middle){
            products[productsPosition++] = allProducts[leftPosition++];
        }

        while (rightPosition <= right){
            products[productsPosition++] = allProducts[rightPosition++];
        }

        for (int i = left; i <= right; i++){
            allProducts[i] = products[i];
        }
    }

    //Prints the top n rated products, which are at the end of the sorted array
    void printTop(int n){
        n = Math.min(n, allProducts.length);
        for (int i = allProducts.length - 1; i >= allProducts.length - n; i--){
            Product p = allProducts[i];
            System.out.println("ID: " + p.id + ", Price: " + p.price.toPlainString() + ", Rating: " + p.rating.toPlainString());
        }
    }

    //Prints the bottom n rated products, which are at the beginning of the sorted array
    void printBottom(int n){
        n = Math.min(n, allProducts.length);
        for (int i = 0; i < n; i++){
            Product p = allProducts[i];
            System.out.println("ID: " + p.id + ", Price: " + p.price.toPlainString() + ", Rating: " + p.rating.toPlainString());
        }
    }

}