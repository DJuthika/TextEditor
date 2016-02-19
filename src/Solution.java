import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//This class contains the implementation for the Array Hopper problem
public class Solution {

	//Constant error message to be displayed
	public static final String INPUT_ERROR_MESSAGE = "failure\n";
	
    public static void main(String args[] ) throws Exception {
    
    	int count = -1;
    	ArrayList<Integer> jumps = null;
    	
    	//Retrieve array of integers from user input
    	jumps = readInputHops();
    	
    	//Invalid input format received
    	if(jumps == null) {
    		
    		System.out.println(INPUT_ERROR_MESSAGE);
    		return;
    	}
    
    	//0 is added to accomodate the destination i.e., in situations when the last node is flown over and not included in the path
    	jumps.add(0);
    	
    	//Determine the minimum count and print the shortest path available for the travel
    	count = findMinimumPath(jumps); 	
    	
    	//Failure in determining the shortest path for the travel
    	if(count == -1) {
    		
    		System.out.println(INPUT_ERROR_MESSAGE);
    	}
    }
    
    //Function creates a list of integers obtained from the user input until an invalid or blank character is obtained
    private static ArrayList<Integer> readInputHops() {
    	
    	//InputStream -> Input reader stream to read the input from standard input
    	//Jumps -> Array containing input hop counts
    	
    	BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> jumps = new ArrayList<Integer>();
        String line;
        Integer nextNumber;
        
    	//Continue reading input until blank line or empty space is received
        try {
        	
        	//Input line is available
			while((line = inputStream.readLine()) != null) {
				
				//Empty line obtained to quit reading input
				if(line.isEmpty() || line.trim().isEmpty()) {
					
					return jumps;
				}
			    
			    try {
			    	
			    	//Get next input number from stream and add valid numeric user input to hops list
			    	nextNumber = Integer.parseInt(line);
			    	
			    	//Checking for valid positive integers
			    	if(nextNumber >= 0) {
			    	
			    		jumps.add(nextNumber);
			    	}
			    	
			    	//Negative number obtained
			    	else {
			    		
			    		return null;
			    	}
			    }
			    
			    catch(Exception e) {
			    	
			    	//Invalid input format so return to main function with failure
			    	inputStream.close();
			    	return null;
			    }
			    
			}
		} catch (IOException e) {

			return null;
		}

        try {
			
        	inputStream.close();
		} 
        
        catch (IOException e) {

			return null;
		}
 
        return jumps;
    }
    
    //Function to iterate over input hop count and find minimum path to be travelled
    private static Integer findMinimumPath(ArrayList<Integer> jumps) {
    	
    	//Path -> Array will contain minimum number of hops required to reach that index
    	//Route -> Array will contain the previous node from which this index was reached
    	
    	int size = jumps.size(),
    		path[] = new int[size],
    		route[] = new int[size];
		
    	//If no array was obtained or the system stalls at first input return with error message
		if(size == 0 || jumps.get(0) == 0){
			
			return (-1);
		}
		
		//Count for first node will be always 0
		path[0] = 0;
		route[0] = 0;
		
		//For each node starting from the index 1, find the previous node with least hops and path to reach current index
		for(int i = 1; i < size; i++){
			
			path[i] = Integer.MAX_VALUE;
			
			//Find all previous node from where we can jump to current index
			for(int j = 0; j < i; j++){
				
				//If valid jump allowed i.e., lies within the limits of previous node hop count
				if(i <= j + jumps.get(j) && path[j] != Integer.MAX_VALUE){
					
					//Update path count of current index and store previous node in route
					if(path[i] >= (path[j] + 1)) {
						
						path[i] = path[j] + 1;
						route[i] = j;
					}
					
					else {
						
						route[i] = i;
					}
					
					break;
				}
			}
		}

		//Valid optimal path and mimimum count obtained
		if(path[size-1] != Integer.MAX_VALUE) {
			
			//Print the shortest path to the destination
			printPath(route, path[size - 1]);
		}
		
		//Error in obtaining optimal path the minimum count
		else {
			
			return -1;
		}
		
		return(path[size-1]);
    }
    
    //Function to print the shortest path obtained for the travel
    private static void printPath(int[] route, int hopCount) {
    	
    	//ActualPath -> Array containing path taken from destination node to source node
    	//Path -> Actual string to be printed for path in expected output form
    	
    	int i;
    	ArrayList<Integer> actualPath = new ArrayList<Integer>();
    	StringBuffer path = new StringBuffer();
    	
    	//Create a backward path for the actual path to be taken starting from the destination
    	for(i = route.length - 1; i != 0;) {
    		
    		//Add node to the path
    		actualPath.add(route[i]);
    		i = route[i];
    	}
    	
    	//Reverse the path obtained in previous step to print the actual path
    	for(i = actualPath.size() - 1; i >= 0; i--) {
    		
    		path.append(actualPath.get(i) + ", ");
    	}
    	path.append("out\n");
    	
    	System.out.println(path);
    }
}