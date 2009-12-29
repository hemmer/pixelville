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
        frame.getContentPane().add(new CityPainter(600, 600));
        frame.setLocationRelativeTo(null);  // Center window.
		frame.pack();
		frame.setVisible(true);		
	}

}
