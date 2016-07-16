package cracking.coding;

public class StringCrack {

	/**
	 * @param args
	 */
	public static boolean isUniqueChars2(String str) {

		boolean[] char_set = new boolean[256];

		for (int i = 0; i < str.length(); i++) {
			// int: get ASCII
			int val = str.charAt(i);
			if (char_set[val])
				return false;
			char_set[val] = true;
		}
		return true;

	}
	
		


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(isUniqueChars2("abcaAd")){
			System.out.println("Unique");
		}
		else
		{
			System.out.println("Not Unique");
		}
	}

}
