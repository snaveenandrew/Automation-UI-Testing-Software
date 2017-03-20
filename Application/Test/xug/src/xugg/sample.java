package xugg;

import xuggle.Player;

public class sample {
	public static void main(String args[]){
		Player p = new Player();
		String s = p.capture(100);
		System.out.println(s);
		Player p1 = new Player();
		String s1 = p.capture(100);
		System.out.println(s1);
	}
}
