import java.io.*;
import java.util.*;

public class ReadFile {
		
	public static void main(String[] args){
		
		String s = "a\n"
				+ " b\n"
				+ " c\n"
				+ "  file1.jpg\n"
				+ "  d\n"
				+ "   file.txt\n"
				+ " x\n"
				+ "  file3.txt\n"
				+ "  y\n"
				+ "   file4.jpg\n"
				+ "   file5.jpg\n"
				+ "d\n"
				+ " file2.jpg\n";
		long sum = Solution(s);
		System.out.println(sum);
				
		
	}
	
	public static int Solution(String s){
		
		String[] str = s.split("\n");
		
		Stack<Integer> stack = new Stack<Integer>();
		int maxLen = 0;
		
		int height = 0;
		int sumStack = 0;
		for(int i=0;i<str.length;i++){
			
			height = calculateSpaces(str[i]);
			int index = str[i].indexOf('.');
			if(index == -1){
				while(stack.size() > height){
					int thisLen = stack.pop();
					sumStack = sumStack - thisLen;
				}
				stack.push(str[i].length()+1 - height);
				sumStack = sumStack+ str[i].length()+1 - height;
			}
			else if(str[i].substring(index).equals(".jpg")){
				maxLen = maxLen + sumStack;
			}
			
		}
		
		
		return maxLen;
		
	}
	public static int calculateSpaces(String s){
		int i=0;
		int height=0;
		while(s.charAt(i)==' '){
			height++;
			i++;
		}
		return height;
	}
	}
