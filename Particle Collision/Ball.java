public class Ball { 
    private double rx, ry;
    private double vx, vy;

    private double radius;

    public Ball(final double rx, final double ry, final double vx, final double vy, final double radius) { 
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void move(double dt) { 

        if((rx + vx * dt < radius) || (rx + vy * dt > 1.0 - radius)){
    
            vx = -vx;

        }

        if((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)){

            vy = -vy;

        }

        rx = rx + vx * dt;
        ry = ry + vy * dt;

    }

    public void draw() { 

        StdDraw.filledCircle(rx, ry, radius);

    }
}
