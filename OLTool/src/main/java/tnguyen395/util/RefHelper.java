package tnguyen395.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/*
 * Finding all of references in a COBOL file.
 * 	- Read file
 * 	- Recursion: max code block is 3. Example: while -> if -> if -> Recursion.
 */
public class RefHelper {
	private static String parent 	= "c:\\P0017.CBL";
	private static String root 		= "C:\\SourceCode\\GroupAsiaSource\\";
	private static CBL cblLoc = new CBL("ObjectList_new_format_add_missing_programs.csv");
	private static Set<String> modules = new HashSet();
	private static Set<String> copies  = new HashSet();
	private static Set<String> io	   = new HashSet();
	private List<Set> refs			   = new ArrayList();
	private static ObjTyp MODULE;
	private static ObjTyp COPY;
	
	private boolean getList(String cblDir) {
	
		try {
			
			// read parent node
			BufferedReader in = new BufferedReader(new FileReader(cblDir));
			String str;
			int i=1;
			
			// get all MODULE in parent node
			while ((str = in.readLine()) != null) {
				// get COPY references
				if (str.contains("COPY ") ){
					String str1 = str.replaceAll("\\s+","").replace("COPY", "").replace(".", "");
					Character letter = str1.charAt(0);
					Character comment = "*".charAt(0);
					if (letter.compareTo(comment) != 0){
						StringBuilder str2 = new StringBuilder(str1);
						int startIdx = str2.indexOf("<");
						int endIdx = str2.indexOf(">");
						try{
							str2.replace(startIdx, ++endIdx, "");
						} catch (Exception e){}
						//System.out.println(str2);
						copies.add(str2.toString());	
					}
				}
				
				// get CALL references
				if (str.contains("CALL \'")){
					String[] str2 = str.split("\'");

					// except IO module
					if (str2[1].endsWith("IO")){
						io.add(str2[1].substring(0, str2[1].length() - 2 ));
						continue;
					}
					else 
					{
						// if they're modules, move to Set to avoid duplicated values.					
						modules.add(str2[1]);
						//System.out.println(str2[1]);
						
						// if they're CBL, print and then recursion
						//i++;
						String dir = cblLoc.getDir(str2[1]);
						if (dir == null){					
							continue;
						}
						cblDir = root + dir + "\\" + str2[1] + ".CBL";
						
						// Recursion
						getList(cblDir);
						
					}										
				}
			}
			
			//filter = replace(filter,filter.length() - 1,x);
			//System.out.println(filter);
		} catch (IOException e) {
		}

		return true;
	}
	
	public void printMissingObject(Set<String> ob){
		for (String name : ob){
			System.out.print(name+" ");
		}
	}
	
	public static void main(String args[]) throws IOException{
		    RefHelper help = new RefHelper();
			if(help.getList(parent)){
			
			
	/*		for(String name : modules){
				System.out.println(name);
			}
	*/		
			QteExcelHelper ex = new QteExcelHelper("ObjectList_new_format_add_missing_programs.csv");
			
			System.out.println("==========================================================");
			System.out.println("* IO");
			System.out.println("==========================================================");
			ex.printLPF(io);
			
			System.out.println("==========================================================");
			System.out.println("* COPY");
			System.out.println("==========================================================");
			ex.printRecord(copies, COPY);
		
			System.out.println("==========================================================");
			System.out.println("* MODULE");
			System.out.println("==========================================================");
			ex.printRecord(modules, MODULE);
			System.out.println("==========================================================");
			System.out.println("* NOT FOUND");
			System.out.println("==========================================================");
			System.out.println("*** COPY NOT FOUND");
			help.printMissingObject(ex.getCpNF());
			
			System.out.println("\n***LFs NOT FOUND");
			help.printMissingObject(ex.getLfNF());
			
			System.out.println("\n***PFs NOT FOUND");
			help.printMissingObject(ex.getPfNF());
			
			System.out.println("***MODULEs NOT FOUND");
			help.printMissingObject(ex.getMdNF());
			
		}
	}
}
