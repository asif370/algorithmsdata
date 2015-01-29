import java.util.Random;

/** 
	Written by Ryan D’souza and Barnabe
	The main class for the Collision System

	Run Instructions:
	1. Download all .java files
	2. From terminal, run: javac *.java
	3. Run: java CollisionSystem 50
		50 = number of particles

	Output: 
		GUI of Collision System */


public class CollisionSystem { 
  
  private MinPQ<Event> pq = new MinPQ<Event>();
  private double t = 0.0;
  private Particle[] particles;
  private static final double RADIUS = 0.005;

  //Constructor with given set of particles
  public CollisionSystem(Particle[] particles) { 
    this.particles = particles;
  }
  
  //Constructor with known set of particles that makes a bunch of random particles
  public CollisionSystem(final int N) { 
    this.particles = new Particle[N];
    
    //A stationary object ie. the sun
    particles[0] = new Particle(Math.random(), Math.random(), 0.0, 0.0, RADIUS * 4, Math.random());
    
    //The other random particles
    for(int i = 1; i < particles.length; i++) { 
      particles[i] = new Particle(Math.random(), Math.random(), 0.01 * (Math.random() - 0.05), 0.01 * (Math.random() - 0.05), RADIUS, Math.random());
      particles[i].draw();
    }
  }
   
  //Predicts if there will be a collision
  private void predict(Particle a){
    
    if(a == null) return;
    for(int i = 0; i < particles.length; i++){
      
      double dt = a.timeToHit(particles[i]);
      pq.insert(new Event(t + dt, a, particles[i]));
      
    }
    
    pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
    pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    
  }
  
  private void redraw() { 
    StdDraw.clear();
    for(Particle a : particles) { 
      a.draw();
    }
  }
  
  //Does the simulation
  public void simulate(){
    
    pq = new MinPQ<Event>();
    for(int i = 0; i < particles.length; i++){
      
      predict(particles[i]);
      
    }
    
    pq.insert(new Event(0, null, null));
    
    while(!pq.isEmpty()){
      
      Event event = pq.delMin();
      if(!event.isValid()) continue;
      Particle a = event.a;
      Particle b = event.b;
      
      for(int i = 0; i < particles.length; i++){
        
        particles[i].move(event.time - t);
        
      }
      
      t = event.time;
      
      if(a != null && b != null) a.bounceOff(b);
      else if(a != null && b == null) a.bounceOffVerticalWall();
      else if(a == null && b == null) b.bounceOffHorizontalWall();
      else if(a == null && b == null) redraw();
      
      predict(a);
      predict(b);
      
    }
    
    int tempCounter = 0;
    while(true) { 
      tempCounter++;
      for(int i = 0; i < particles.length; i++){
        
        particles[i].move(Math.random());
      }
      
      
      StdDraw.show(10);
      redraw();
    }
    
  }
  
  public static void main(String[] args) { 
    final int N = 50;
    final CollisionSystem cs = new CollisionSystem(N);
    cs.simulate();
  }
  
  
  private class Event implements Comparable<Event> { 
    private double time;
    private Particle a, b;
    private int countA, countB;
    
    public Event(double t, Particle a, Particle b) { 
      this.time = t;
      this.a = a;
      this.b = b;
      this.countA = a == null ? 0 : a.getCount();
      this.countB = b == null ? 0 : b.getCount();
    }
    
    public int compareTo(Event that) { 
      if(this.time < that.time) { 
        return -1;
      }
      if(this.time > that.time) { 
        return 1;
      }
      return 0;
    }
    
    //TO FIGURE OUT
    public boolean isValid() { 
      if(a != null && countA != a.getCount()) { 
        return false;
      }
      if(b != null && countB != b.getCount()) { 
        return false;
      }
      return false;
    }
  }
}