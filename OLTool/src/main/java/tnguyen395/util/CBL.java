package tnguyen395.util;
/*
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;

public class CBL {

	/**
	 * @param args
	 */
	//private static String txt;
	private static String csv;
	
	CBL(String csv){
		//this.txt = txt;
		this.csv = csv;
	}
		
	// get Dir by cobol name
	public String getDir(String name) throws IOException{
		String dir = null;
		
		CSVReader reader = new CSVReader(new FileReader(csv));
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			if (name.equals(nextLine[1]) && "CBL".equals(nextLine[5])){
				dir  = nextLine[4] + "\\" + nextLine[5]; 
				break;
			}
		}
		//System.out.println(dir);
		return dir;
	}
	/*public static HashMap<String,String> getDir() throws IOException{
		HashMap<String, String> CblDir = new HashMap();
		BufferedReader in = new BufferedReader(new FileReader(txt));
		String str,name;
		while ((str = in.readLine()) != null) {
			name	= str.replaceAll("\\s+","");
			CSVReader reader = new CSVReader(new FileReader(csv));
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
			    // nextLine[] is an array of values from the line
			    //System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
				if ((name.equals(nextLine[1])) && ("CBL".equals(nextLine[5]))){
					String str2  = nextLine[4] + "\\" + nextLine[5]; 
					CblDir.put(nextLine[1], str2);
					System.out.println(Arrays.toString(nextLine) + str2);
				}
			}
		}
		return CblDir;
	}*/
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//CBL od = new CBL("c:\\filter.txt","ObjectList_new_format_add_missing_programs.csv");
		
	}

}
