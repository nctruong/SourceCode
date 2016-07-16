package tutorialspoint.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private static String filter 	= "and name in (";
	private static Character x		= new Character (')');
	public static String replace(String str, int index, char replace){     
	    if(str==null){
	        return str;
	    }else if(index<0 || index>=str.length()){
	        return str;
	    }
	    char[] chars = str.toCharArray();
	    chars[index] = replace;
	    return String.valueOf(chars);       
	}
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("c:\\filter.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				filter += "\'" + str.replaceAll("\\s+","") + "\',";
				//System.out.println(str);		
			}
			
			filter = replace(filter,filter.length() - 1,x);
			System.out.println(filter);
		} catch (IOException e) {
		}
	}

}
