using System;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

public class CityPainter : System.Windows.Forms.Form
{
    private System.ComponentModel.IContainer components = null;
    private System.Windows.Forms.Timer timer1 = new System.Windows.Forms.Timer(new System.ComponentModel.Container());
    Bitmap scene;
    int panelHeight, panelWidth;
    int gridSize = 40;
    City city;
    int xOffset, yOffset;
    private Point MouseXY;

    Random random = new Random();

    int ScreenNumber;
    int xPos = 0;
    int yPos = 0;
    int bmpWidth, bmpHeight;

    public CityPainter(int panelHeight, int panelWidth, City city, int screenNo) {
        //this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);

        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;
        this.city = city;

        bmpWidth = gridSize / 2 * (city.getW() + city.getH())+2;
        bmpHeight = gridSize / 4 * (city.getW() + city.getH())+2;
        xOffset = gridSize / 2 * (city.getW());
        yOffset = gridSize / 2;

        scene = generateScene(city.getW(), city.getH());

        xPos = random.Next(-xOffset, 0);
        yPos = random.Next(-yOffset, 0);

        this.ScreenNumber = screenNo;
        BackColor = Color.FromArgb(144, 172, 93);
        timer1.Interval = 500;
        
        this.ClientSize = new System.Drawing.Size(Screen.AllScreens[screenNo].Bounds.Width, Screen.AllScreens[screenNo].Bounds.Height);
        this.Text = "Pixelville";
        this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
        this.MouseDown += new System.Windows.Forms.MouseEventHandler(this.OnMouseEvent);
        this.Paint += new System.Windows.Forms.PaintEventHandler(this.CityPainter_Paint);
        this.timer1.Enabled = true;
        this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
        //this.MouseMove += new System.Windows.Forms.MouseEventHandler(this.OnMouseEvent);
    }

    public Bitmap generateScene(int w, int h) {

        Bitmap bmp = new Bitmap(bmpWidth, bmpHeight, PixelFormat.Format32bppArgb);
        Graphics gBmp = Graphics.FromImage(bmp);

        // draw buildings
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                paintCube(xOffset + (j - i) * gridSize / 2, yOffset + (i + j) * gridSize / 4, gBmp, city.getBuilding(i, j).getHeight(), gridSize / 2, gridSize / 2, city.getBuilding(i, j).getColor());
            }
        }

        Pen p = new Pen(Color.Black);
        gBmp.DrawRectangle(p, new Rectangle(new Point(xOffset,yOffset), new Size(5,5)));

        return bmp;
    }

    private void CityPainter_Load(object sender, System.EventArgs e) {
        this.Bounds = Screen.AllScreens[ScreenNumber].Bounds;
        Cursor.Hide();
        TopMost = true;
    }

    private void CityPainter_Paint(object sender, PaintEventArgs pea) {
    }
    protected override void OnPaint(PaintEventArgs pea) {
   
        Graphics gForm = pea.Graphics;
        gForm.DrawImage(scene, xPos, yPos, scene.Width, scene.Height);


    }

    private void timer1_Tick(object sender, System.EventArgs e) {
        xPos+=25;
        yPos+=25;
        this.Invalidate();
     }

    public void paintCube(int x, int y, Graphics g, int h, int w, int d, Color c){
        Pen pen = new Pen(Color.Black);
        Brush brush = new SolidBrush(c);

        // only plot if height is non-zero
        if (h != 0){
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

    private void OnMouseEvent(object sender, System.Windows.Forms.MouseEventArgs e) {

        MouseXY = new Point(e.X, e.Y);
        ////if (!MouseXY.IsEmpty) {
        //    if (MouseXY != new Point(e.X, e.Y))
        //        Close();
            if (e.Clicks > 0 && e.Button == System.Windows.Forms.MouseButtons.Left)
                Close();
        //}
            if (e.Button == System.Windows.Forms.MouseButtons.Middle) {
                xPos = random.Next(-100, 100);
                yPos = random.Next(-100, 100);
                this.Invalidate();
            }
            if (e.Button == System.Windows.Forms.MouseButtons.Right) {
                scene.Save(@"c:\someImage.gif", System.Drawing.Imaging.ImageFormat.Png);
            }
    }

    protected override void Dispose(bool disposing) {
        if (disposing) {
            if (components != null) {
                components.Dispose();
            }
        }
        base.Dispose(disposing);
    }
}