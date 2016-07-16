package tnguyen395.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;

public class QteExcelHelper {
	private List<String> dir;
	private String csv;
	private List<String> objNF;// NOT FOUND
	private Set<String> lfNF = new HashSet();
	private Set<String> pfNF = new HashSet();
	private Set<String> cpNF = new HashSet(); // Copy not found
	private Set<String> mdNF = new HashSet();
	
	private static ObjTyp MODULE;
	private static ObjTyp COPY;
	
	QteExcelHelper(String csv){
		this.csv = csv;
	}
	
	public void printRecord(Set<String> refs, ObjTyp typ) throws IOException{
		for(String name : refs){
			name	= name.replaceAll("\\s+","");
			CSVReader reader = new CSVReader(new FileReader(csv));
			String [] nextLine;
			boolean flag = false;
			while ((nextLine = reader.readNext()) != null) {
			    // nextLine[] is an array of values from the line
			    //System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
				if (name.equals(nextLine[1])){
					System.out.println(Arrays.toString(nextLine).replaceAll("\\s+",""));
					flag = true;
				}
				
			}
			
			if (!flag)
			{
				if (typ == MODULE){
					mdNF.add(name);
				}
				if (typ == COPY)
				{
					cpNF.add(name);
				}
				//System.out.println("=====================================================================NOT FOUND: " + name);
			}
		}
	}
	
	public void printLPF(Set<String> io) throws IOException{
		int count;
		boolean flagLFFound;
		boolean flagPFFound;
		boolean flagUniqueLFPF;
		for(String name : io){
			name	= name.replaceAll("\\s+","");
			CSVReader reader = new CSVReader(new FileReader(csv));
			String [] nextLine;
			count = 0;
			flagLFFound = false;
			flagPFFound = false;
			flagUniqueLFPF = false;
			while ((nextLine = reader.readNext()) != null) {
			    // nextLine[] is an array of values from the line
			    //System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
				if (nextLine[1].startsWith(name) 
						&& (nextLine[2].startsWith("LF") )
						&& !flagLFFound){
					//System.out.println(Arrays.toString(nextLine).replaceAll("\\s+",""));
					count += 1;
					flagLFFound = true;
				}
				if (nextLine[1].startsWith(name) 
						&& nextLine[2].startsWith("PF")
						&& !flagPFFound){
					//System.out.println(Arrays.toString(nextLine).replaceAll("\\s+",""));
					count += 1;
					flagPFFound = true;
				}
				
				if (nextLine[1].startsWith(name + "SKM"))
				{
					//System.out.println(Arrays.toString(nextLine).replaceAll("\\s+",""));
				}
				
				if (nextLine[1].startsWith(name + "KEY"))
				{
					System.out.println(Arrays.toString(nextLine).replaceAll("\\s+",""));
				}
				
				if (count > 1){
					break;
				}
				else
				{
					count = 0;
				}
				
			}
			if (!flagLFFound)
			{
				lfNF.add(name);
				//System.out.println("===================================================================LF NOT FOUND: " + name);
			}
			
			if (!flagPFFound)
			{
				pfNF.add(name);
				//System.out.println("===================================================================PF NOT FOUND: " + name);
			}
		}
	}
	
	public static void readExcel(String txt, String csv) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(txt));
		String str,name;
		while ((str = in.readLine()) != null) {
			name	= str.replaceAll("\\s+","");
			CSVReader reader = new CSVReader(new FileReader(csv));
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
			    // nextLine[] is an array of values from the line
			    //System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
				if (name.equals(nextLine[1])){
					System.out.println(Arrays.toString(nextLine));
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException{
		readExcel("c:\\filter.txt","ObjectList_new_format_add_missing_programs.csv");
		//write();
	}

	public List<String> getDir() {
		return dir;
	}

	public void setDir(List<String> dir) {
		this.dir = dir;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	public List<String> getObjNF() {
		return objNF;
	}

	public void setObjNF(List<String> objNF) {
		this.objNF = objNF;
	}

	public Set<String> getLfNF() {
		return lfNF;
	}

	public void setLfNF(Set<String> lfNF) {
		this.lfNF = lfNF;
	}

	public Set<String> getPfNF() {
		return pfNF;
	}

	public void setPfNF(Set<String> pfNF) {
		this.pfNF = pfNF;
	}

	public Set<String> getCpNF() {
		return cpNF;
	}

	public void setCpNF(Set<String> cpNF) {
		this.cpNF = cpNF;
	}

	public Set<String> getMdNF() {
		return mdNF;
	}

	public void setMdNF(Set<String> mdNF) {
		this.mdNF = mdNF;
	}
	
	
}
