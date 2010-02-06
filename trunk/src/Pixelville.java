//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.*;
//import java.io.*;
//import javax.imageio.*;
import java.awt.Canvas;

import javax.swing.*;

@SuppressWarnings("serial")
public class Pixelville extends JFrame{

	Canvas canvas;
	public static int zoomLevels = 4; 
	
	public Pixelville(){
    	setTitle("Pixelville");
    	setResizable(false);
    	
//    	canvas = new CityPainter(500, 600);
//
//        setLocationRelativeTo(null);  // Center window.
//        pack();
    	
	}
	
	public static void main(String[] args) throws InterruptedException {

		JFrame frameIso = new Pixelville();
		JFrame frameMap = new JFrame();
		frameIso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		City city = new City(19,19,4);
//		City city = new City(8,8,8);
		frameIso.getContentPane().add(new CityPainter(1050, 1680, city, 2));
        frameMap.getContentPane().add(new MapPainter(city));
        //frame.setLocationRelativeTo(null);  // Center window.
        frameIso.setUndecorated(true);
		frameIso.pack(); 
		frameIso.setVisible(true);
//		frameMap.pack(); 
//		frameMap.setVisible(true);		
	}

}
