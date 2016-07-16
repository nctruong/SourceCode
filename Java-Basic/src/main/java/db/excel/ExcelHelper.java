package db.excel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ExcelHelper {
	public static void read() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("ObjectList.csv"));
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
		    // nextLine[] is an array of values from the line
		    //System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
			System.out.println(Arrays.toString(nextLine));
		}
	}
	
	public static void write() throws IOException{
		CSVWriter writer = new CSVWriter(new FileWriter("test.csv"), '\t');
		// feed in your array (or convert your data to an array)
		String[] entries = new String[10];// = {"first","second","third"};//"first#second#third".split("#");
		entries[0] = "1,HAI";
		entries[1] = "TRUONG";
		writer.writeNext(entries);
		writer.close();
	}
	public static void main(String args[]) throws IOException{
		//read();
		write();
	}
}
