/** @author Herman Mann 
 * CMSC 350 Section 6383 - Week 1 (Discussion 1) - 01/18/2022
 * 
 * Focusing on Big-O Notation and using the concept on functions
 * with the different growth rates and using this as comparison
 * of the values of the specific two functions and then illustrating
 * the major point which is when and where the faster growing function
 * overtakes and gets ahead of the slower growing function. 
 * 
 */
public class bigONotation_week1 {

	public static int function_f(int result) {
		
		int nroot_fFunction = result*result;
		int res2 = 135*nroot_fFunction + 200*result ;
		return res2;
	}
	
	public static int function_g(int g_result) {
		
		int final_gResult = g_result*g_result*g_result;
		int res3 = final_gResult - 430*g_result;
		return res3;
	}
	
	public static void main(String[] args) {
	
		System.out.println("f(n) = 135n^2+200n\t g(n) = n^3-430n");
		System.out.println("\nn\t   f(n)\t          g(n)");
		
		for(int theCount = 5; theCount < 500; theCount++) {
			
			System.out.println(theCount + "    \t   " + function_f(theCount) + " \t " + function_g(theCount));
			if(function_f(theCount) < function_g(theCount)) {
				System.out.println("Once n reaches " + theCount + " g overtakes f");
				break;
		
			}
				
				
		}
	}

}
