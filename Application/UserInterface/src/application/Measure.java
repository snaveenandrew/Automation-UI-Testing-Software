package application;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Measure {
	double width,height,panelWidth,panelHeight;
	public Measure() {
		// TODO Auto-generated constructor stub
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth() - 100;
        height = screenSize.getHeight() - 100;
        panelWidth = (width)/2 - 50;
        panelHeight = (9*panelWidth)/16;
	}

}
