public class Particle { 

    private double rx, ry;
    private double vx, vy;
    private final double radius, mass;
    private int count;
    
    public double getRX() { 
      return this.rx;
    }
    public double getRY() { 
      return this.ry;
    }

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass) { 
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        this.count = 0;
    }

    public void draw() { 
        StdDraw.filledCircle(rx, ry, radius);
    }

    public int getCount() { 
        return this.count;
    }

    public void move(double dt) { 

        if((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)){
            vx = -vx;
        }

        if((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)){
            vy = -vy;
        }
        
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }
    
    public double timeToHit(Particle that){
      
      if(this == that) {
        return Double.MAX_VALUE;
      }
      
      double dx = that.rx - this.rx;
      double dy = that.ry - this.ry;
      double dvx = that.vx - this.vx;
      double dvy = that.vy - this.vy;
      double dvdr = dx * dvx + dy * dvy;
      
      if(dvdr > 0) {
        return Double.MAX_VALUE;
      }
      
      double dvdv = dvx * dvx + dvy * dvy;
      double drdr = dx * dx + dy * dy;
      double s = this.radius + that.radius;
      double d = (dvdr * dvdr) - dvdv * (drdr - s*s);
      
      if(d < 0) {
        return Double.MAX_VALUE;
      }
      
      double temp = -(dvdr + Math.sqrt(d)) / dvdv;
      System.out.println(temp);
      
      if(temp < 0.05) { 
        System.out.println("IMPACT IMMINENT");
      }
      
      return -(dvdr + Math.sqrt(d)) / dvdv;
    }
    
    public double timeToHitVerticalWall() { 
      return (1 - radius - rx) / vx;
    }
    
    public double timeToHitHorizontalWall() { 
        return (1 - radius - ry) / vy;
    }

    public void bounceOff(Particle that){

        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        
        double dvdr = dx * dvx + dy * dvy;
        double s = this.radius + that.radius;
        
        double J = 2 * this.mass * that.mass * dvdr / (s * (this.mass + that.mass));
        double Jx = J * dx / s;
        double Jy = J * dy / s;
        
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        this.vx -= Jx / that.mass;
        this.vy -= Jy / that.mass;
        
        this.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() { 
      vx = -vx;
      rx = 1 - radius;
      ry = ry + vy * timeToHitVerticalWall();
    }

    public void bounceOffHorizontalWall() { 
      vy = -vy;
      ry = 1 - radius;
      rx = rx + vx * timeToHitHorizontalWall();
    }
}
