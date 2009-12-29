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
	
	public Pixelville(){
    	setTitle("Pixel City");
    	setResizable(false);
    	
//    	canvas = new CityPainter(500, 600);
//
//        setLocationRelativeTo(null);  // Center window.
//        pack();
    	
	}
	
	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new Pixelville();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		City city = new City(10,10);
        //frame.getContentPane().add(new CityPainter(500, 900, city));
        frame.getContentPane().add(new MapPainter(city));
        frame.setLocationRelativeTo(null);  // Center window.
		frame.pack();
		frame.setVisible(true);		
	}

}
