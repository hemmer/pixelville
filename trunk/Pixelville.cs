using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

public class Pixelville{
    [STAThread]
    static void Main(string[] args) {

        City city = new City(50, 100, 5, true);

        if (args.Length > 0) {
            if (args[0].ToLower().Trim().Substring(0, 2) == "/c") {
                MessageBox.Show("This Screen Saver has no options you can set.", ".NET Screen Saver", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            } else if (args[0].ToLower() == "/s") {
                for (int i = Screen.AllScreens.GetLowerBound(0); i <= Screen.AllScreens.GetUpperBound(0); i++)
                    System.Windows.Forms.Application.Run(new CityPainter(800, 1200, city, i));
            }
        } else {
            //for (int i = Screen.AllScreens.GetLowerBound(0); i <= Screen.AllScreens.GetUpperBound(0); i++)
            int i = 0;
                System.Windows.Forms.Application.Run(new CityPainter(800, 1200, city, i));
        }

    }
}