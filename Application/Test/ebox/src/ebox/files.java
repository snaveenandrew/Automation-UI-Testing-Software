package ebox;

import java.io.UnsupportedEncodingException;

public class files {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int i = 0;
		StringBuffer s = new StringBuffer("aaaassaaarbbbbbbbbbccccccc");
		String r;
		char b[] = new char[s.length()];
		while(i<s.length()){
			if(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u'){
				char a= (char) (s.charAt(i)+1);
				//System.out.print(a);
				b[i] = a;
			}
			else
				b[i] = s.charAt(i);	
			i++;
		}
		r = String.valueOf(b);	
		System.out.println( r+"\n"+s);

	}

}
