using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

public class Pixelville{
    [STAThread]

    public static void Main(string[] args)
    {
        City city = new City(16, 16, 0);
        Application.Run(new CityPainter(800, 1200, city));
    }
}