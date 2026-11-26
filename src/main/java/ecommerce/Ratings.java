package ecommerce;
import java.math.BigDecimal;
import java.math.BigInteger;

//Created a heap to find the top-rated n products; resembles a minimum heap
class Ratings{
    Product[] topRated;
    int size = 0;

    Ratings(int capacity){
        topRated = new Product[capacity];
    }

    void minHeap(Product p){
        if (size < topRated.length){
            topRated[size] = p;
            fillMin(size);
            size++;
        }

        else if (p.rating.compareTo(topRated[0].rating) > 0){
            topRated[0] = p;
            replaceMin();
        }
    }

    /*
    When finding the top n rated products, we put them in the first empty slot
    and then check to see if their rating is greater than their parent's rating.
    If so, then everything is in the correct position. If not, switch the positions
    of the products in the array, and then repeat the process until the new product
    is greater than its new parent or it becomes the new root
    */
    private void fillMin(int currentPosition){
        while (currentPosition > 0){
            int parentPosition = (currentPosition-1) / 2;
            if(topRated[parentPosition].rating.compareTo(topRated[currentPosition].rating) <= 0){
                break;
            }

            swap(currentPosition, parentPosition);
            currentPosition = parentPosition;
        }
    }

    /*
    Once the heap is full, we compare every product with the root (the lowest rated product
    out of the top products). If a product has a higher rating than the root, then we replace the root.
    Then we compare it among its children to see if its rating is higher. If the rating is higher than the smallest child,
    we swap the position and continue until the current product's rating is lower than its children's
    */
    private void replaceMin(){
        int productPosition = 0;
        while (true){
            int leftChild = 2 * productPosition + 1;
            int rightChild = leftChild + 1;
            int smallestPosition = productPosition;
            if (leftChild < size && topRated[leftChild].rating.compareTo(topRated[smallestPosition].rating) < 0){
                smallestPosition = leftChild;
            }

            if (rightChild < size && topRated[rightChild].rating.compareTo(topRated[smallestPosition].rating) < 0){
                smallestPosition = rightChild;
            }

            if (smallestPosition == productPosition){
                break;
            }

            swap(productPosition, smallestPosition);
            productPosition = smallestPosition;
        }
    }

    void maxHeap(Product p){
        if (size < topRated.length){
            topRated[size] = p;
            fillMax(size);
            size++;
        }

        else if (p.rating.compareTo(topRated[0].rating) < 0){
            topRated[0] = p;
            replaceMax();
        }
    }

    /*
    When finding the lowest n rated products, we put them in the first empty slot
    and then check to see if their rating is lower than their parent's rating.
    If so, then everything is in the correct position. If not, switch the positions
    of the products in the array, and then repeat the process until the new product
    is less than its new parent or it becomes the new root
    */
     private void fillMax(int currentPosition){
        while (currentPosition > 0){
            int parentPosition = (currentPosition-1) / 2;
            if(topRated[parentPosition].rating.compareTo(topRated[currentPosition].rating) >= 0){
                break;
            }

            swap(currentPosition, parentPosition);
            currentPosition = parentPosition;
        }
    }

    /*
    Once the heap is full, we compare every product with the root (the highest rated product
    out of the lowest rated products). If a product has a lower rating than the root, then we replace the root.
    Then we compare it among its children to see if its rating is lower. If the rating is lower than the largest child,
    we swap the position and continue until the current product's rating is higher than its children's
    */
    private void replaceMax(){
        int productPosition = 0;
        while (true){
            int leftChild = 2 * productPosition + 1;
            int rightChild = leftChild + 1;
            int largestPosition = productPosition;
            if (leftChild < size && topRated[leftChild].rating.compareTo(topRated[largestPosition].rating) > 0){
                largestPosition = leftChild;
            }

            if (rightChild < size && topRated[rightChild].rating.compareTo(topRated[largestPosition].rating) > 0){
                largestPosition = rightChild;
            }

            if (largestPosition == productPosition){
                break;
            }

            swap(productPosition, largestPosition);
            productPosition = largestPosition;
        }
    }

    private void swap(int i, int j){
        Product temp = topRated[i];
        topRated[i] = topRated[j];
        topRated[j] = temp;
    }

    private void print(){
        for (int i = 0; i < size; i++){
            System.out.println("Product ID: " + topRated[i].id + ", Price: $" + topRated[i].price.toPlainString() + ", Rating: " + topRated[i].rating.toPlainString());
        }
    }

}