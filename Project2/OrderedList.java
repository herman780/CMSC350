/*
 * Author Name: Herman Mann
 * Project: # 2 - Involves the writing of a program which will have the ability to
 * examine a file of polynomials and will be able to determine whether the
 * polynomials in that specific file are in ascending order using two different
 * methods of comparison. 
 * Filename: OrderedList.java
 * Date: 02/06/2022
 * Description: This is a Java class a utility class that contains two overloaded
 * implementations of a method named checkSorted, which determines whether a List object,
 * supplied as a parameter, is in strictly ascending order. Both methods should 
 * be class (static) methods. The first of the overloaded methods should
 * accept a list that contains elements that implement Comparable. 
 * The second should instead be supplied an additional parameter that is an object 
 * of a class that implements the Comparator interface. Refer to the signatures of 
 * the two sort methods in the predefined Java Collections class as a model for how these two
 * methods should be defined. Do not duplicate code, but instead ensure that first 
 * overloaded method calls the second. 
 */


//Package name
package cmsc350_hermanmann_project2;

//Import the files
import java.util.List;
import java.util.*;

/*
 * A class that contains two overloaded implementations of a method named checkSorted,
 * which determines whether a List object, supplied as a parameter, is in strictly ascending order.
 */
public class OrderedList  {
	
	/**
     * To check whether a given list is in sorting order or not
     *
     * @param <T> - Generic type
     * @param list - List of Generic type objects
     * @return true if list is in sorted order else false
     */
	 public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
		
		 boolean isListOrdered = true;
	        //Length of list
	        int listLength = list.size();
	        //Loop for each item in the list
	        for (int index = listLength - 1; index > 0; index--) {
	            T item = list.get(index);
	            //Check for sorting order
	            if (!checkSorted(list, item)) {
	                isListOrdered = false;
	            }
	        }
	        return isListOrdered;
	}
	 
	 /**
	  * To check whether a given item from a list is in sort order or not
	  *
	  * @param <T> - Generic type
	  * @param list - List of Generic type objects
	  * @param item - item to compare for the sorting order
	  * @return true if list is in sorted order else false
	  */
	 private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T item) {
	        //Index of an item
	        int itemIndex = list.indexOf(item);
	        //Index of previous item
	        int previousItemIndex = itemIndex - 1;
	        //Data of item
	        T itemData = list.get(itemIndex);
	        //Data of previous item
	        T previousItemData = list.get(previousItemIndex);
	        if (previousItemData != null) {
	            return itemData.compareTo(previousItemData) >= 0;
	        }
	        return true;
	 }
}
