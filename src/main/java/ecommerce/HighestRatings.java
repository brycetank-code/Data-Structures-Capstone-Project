package ecommerce;
import java.math.BigDecimal;
import java.math.BigInteger;

//Created a heap to find the top-rated n products; resembles a minimum heap
class HighestRatings{
    Product[] topRated;
    int size = 0;

    HighestRatings(int capacity){
        topRated = new Product[capacity];
    }

    void search(Product p){
        if (size < topRated.length){
            topRated[size] = p;
            fill(size);
            size++;
        }

        else if (p.rating.compareTo(topRated[0].rating) > 0){
            topRated[0] = p;
            replace();
        }
    }


    /*
    When finding the top n rated products, we put them in the first empty slot
    and then check to see if their rating is greater than their parent's rating.
    If so, then everything is in the correct position. If not, switch the positions
    of the products in the array, and then repeat the process until the new product
    is less than its new parent or it becomes the new root
    */
    private void fill(int currentPosition){
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
    Then we compare it among its children to see if it's rating is lower. If the rating is higher,
    we swap the position and continue until the current product's rating is lower than it's parent
    */
    private void replace(){
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

    private void swap(int i, int j){
        Product temp = topRated[i];
        topRated[i] = topRated[j];
        topRated[j] = temp;
    }
}