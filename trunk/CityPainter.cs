using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

public class CityPainter : System.Windows.Forms.Form
{
    int panelHeight, panelWidth;
    int gridSize = 80;
    City city;
    int xOffset = 500; 
    int yOffset = 100;

    public CityPainter(	int h, int w, City city)
    {
        panelHeight = h;
        panelWidth = w;
        this.city = city;
        BackColor = Color.FromArgb(144, 172, 93);

        //this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
        this.ClientSize = new System.Drawing.Size(panelWidth, panelHeight);
        this.Text = "DataGridExample";
    }

    protected override void OnPaint(PaintEventArgs pea)
    {
        DoPage(pea.Graphics, ClientSize.Width, ClientSize.Height);
    }

    protected void DoPage(Graphics grfx, int cx, int cy)
    {
        int w = city.getW();
        int h = city.getH();

        // draw buildings
        for (int i = 0; i < w; i++)
        {
            for (int j = 0; j < h; j++)
            {
                paintCube(xOffset+(j-i)*gridSize/2, yOffset+(i + j) * gridSize / 4, grfx, city.getBuilding(i, j).getHeight(), gridSize / 2, gridSize / 2, city.getBuilding(i, j).getColor());
            }
        }
    }

    	public void paintCube(int x, int y, Graphics g, int h, int w, int d, Color c){
            Pen pen = new Pen(Color.Black);
            Brush brush = new SolidBrush(c);

            if (h != 0)
            {
                Point[] points1 = { new Point(x, y), new Point(x, y - h), new Point(x - w, y - h - (w / 2)), new Point(x - w, y - (w / 2)) };
                g.FillPolygon(brush, points1);
                g.DrawPolygon(pen, points1);

                Point[] points2 = { new Point(x, y), new Point(x, y - h), new Point(x + d, y - h - (d / 2)), new Point(x + d, y - (d / 2)) };
                brush = new SolidBrush(MiscMethods.darken(c, 0.7));
                g.FillPolygon(brush, points2);
                g.DrawPolygon(pen, points2);
            }
		
        Point[] points3 = {new Point(x,y-h), new Point(x-w,y-(w/2)-h), new Point(x-w+d, y-(w/2)-(d/2)-h), new Point(x+d,  y-(d/2)-h)};
        brush = new SolidBrush(MiscMethods.brighten(c, 0.7));
		g.FillPolygon(brush, points3);
		g.DrawPolygon(pen, points3);
	}


}