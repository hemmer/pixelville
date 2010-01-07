using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text;

public class Building
    {

        private int height = 0;
        private Color color;

        public Building(int height, Color color)
        {
            this.height = height;
            this.color = color;
        }

        public int getHeight()
        {
            return height;
        }

        public Color getColor()
        {
            return this.color;
        }
}

