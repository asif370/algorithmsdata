/** Written by Ryan D'souza
  * Represents a KD-Tree
  * 
  * Algorithms and Data Structures */

public class KdTree {
  
  //Size of the tree
  private int size = 0;
  
  //Direction (only two options) 
  private enum Orientation {
    LeftRight, AboveBelow;
    
    /** Returns the opposite direction */
    public Orientation next() {
      if (this.equals(Orientation.AboveBelow)) {
        return Orientation.LeftRight;
      }
      
      return Orientation.AboveBelow;
    }
  }
  
  /** Represents a Node */
  private static class Node {
    
    private Point2D p;
    
    //Rectangle aligning to this Node
    private RectHV rect;
    
    //Left/bottom subtree
    private Node lb;
    
    //Right and top subtree
    private Node rt;
    
    /** Default constructor */
    public Node(Point2D p) {
      this.p = p;
    }
  }
  
  private Node root;
  
  /** Empty constructor */
  public KdTree() {
  }
  
  /* Is the set empty? */
  public boolean isEmpty() {
    return root == null;
  }
  
  /* Number of points in the set */
  public int size() {
    return size;
  }
  
  /* Adds the point if it doesn't already exist  */
  public void insert(Point2D p) {
    
    //If this point should be the root
    if (isEmpty()) {
      root = new Node(p);
      root.rect = new RectHV(0, 0, 1, 1);
      size++;
      return;
    }
    
    //Recursively add the Point if it is not the root
    root = put(root, p, Orientation.LeftRight);
  }
  
  /** Recursively adds the node/point */
  private Node put(Node x, Point2D p, Orientation orientation) {
    
    //We reached the end of the subtree/bottom of the tree
    if (x == null) {
      size++;
      return new Node(p);
    }
    
    //Node with the same Point already exists
    if (x.p.equals(p)) {
      return x;
    }
    
    //Compares to tell which direction we should go to next
    int cmp = compare(p, x.p, orientation);
    Orientation nextOrientation = orientation.next();
    
    //If it's smaller/less than, recursively add it to the left subtree
    if (cmp < 0) {
      x.lb = put(x.lb, p, nextOrientation);
      if (x.lb.rect == null) {
        if (orientation == Orientation.LeftRight) {
          x.lb.rect = new RectHV(x.rect.xmin(), x.rect.ymin(),
                                 x.p.x(), x.rect.ymax());
        } else {
          x.lb.rect = new RectHV(x.rect.xmin(), x.rect.ymin(),
                                 x.rect.xmax(), x.p.y());
        }
      }
    }
    
    //Recursively add it to the right subtree
    else {
      x.rt = put(x.rt, p, nextOrientation);
      if (x.rt.rect == null) {
        if (orientation == Orientation.LeftRight) {
          x.rt.rect = new RectHV(x.p.x(), x.rect.ymin(),
                                 x.rect.xmax(), x.rect.ymax());
        } else {
          x.rt.rect = new RectHV(x.rect.xmin(), x.p.y(),
                                 x.rect.xmax(), x.rect.ymax());
        }
      }
    }
    return x;
  }
  
  /** Compares two points */
  private int compare(Point2D p, Point2D q, Orientation orientation) {
    if (orientation == Orientation.LeftRight) {
      return Double.compare(p.x(), q.x());
    } else {
      return Double.compare(p.y(), q.y());
    }
  }
  
  /* Checks to see if the set contains a point by calling the recursive function*/
  public boolean contains(Point2D p) {
    return contains(root, p, Orientation.LeftRight);
  }
  
  /** Recursively checks to see if the set contains the point */
  private boolean contains(Node x, Point2D p, Orientation orientation) {
    
    //Bottom of the tree (no more nodes)
    if (x == null) {
      return false;
    }
    
    //Node with value already exists
    if (x.p.equals(p)) {
      return true;
    }
    
    //Compare to see whether to check the left or right subtree
    int cmp = compare(p, x.p, orientation);
    Orientation nextOrientation = orientation.next();
    if (cmp < 0) {
      return contains(x.lb, p, nextOrientation);
    } else {
      return contains(x.rt, p, nextOrientation);
    }
  }
  
  /** Draws the structure using StdDraw class*/
  public void draw() {
    draw(root, Orientation.LeftRight);
  }
  
  /** Recursively draws everything */
  private void draw(Node x, Orientation orientation) {
    
    //No more nodes
    if (x == null) {
      return;
    }
    
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.01);
    x.p.draw();
    
    if (orientation == Orientation.LeftRight) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.setPenRadius(0.001);
      StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
    }
    Orientation next = orientation.next();
    draw(x.lb, next);
    draw(x.rt, next);
  }
  
  /** Returns all points inside a rectangle */
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> queue = new Queue<Point2D>();
    
    if (!isEmpty()) {
      findPoints(queue, rect, root);
    }
    return queue;
  }
  
  /** Recusrively finds the points inside the rectangle */
  private void findPoints(Queue<Point2D> queue, RectHV rect, Node x) {
    if (!rect.intersects(x.rect)) {
      return;
    }
    if (rect.contains(x.p)) {
      queue.enqueue(x.p);
    }
    if (x.lb != null) {
      findPoints(queue, rect, x.lb);
    }
    if (x.rt != null) {
      findPoints(queue, rect, x.rt);
    }
  }
  
  /** Nearest neighbor check*/
  public Point2D nearest(Point2D p) {
    if (isEmpty()) {
      return null;
    }
    return findNearest(root, p, root.p, Double.MAX_VALUE,
                       Orientation.LeftRight);
  }
  
  /** Recursively finds the nearest neighbor */
  private Point2D findNearest(Node x, Point2D p, Point2D nearest,
                              double nearestDistance, Orientation orientation) {
    
    //No more neighbors
    if (x == null) {
      return nearest;
    }
    
    Point2D closest = nearest;
    double closestDistance = nearestDistance;
    double distance = x.p.distanceSquaredTo(p);
    if (distance < nearestDistance) {
      closest = x.p;
      closestDistance = distance;
    }
    Node first, second;
    if (orientation == Orientation.LeftRight) {
      if (p.x() < x.p.x()) {
        first = x.lb;
        second = x.rt;
      } else {
        first = x.rt;
        second = x.lb;
      }
    } else {
      if (p.y() < x.p.y()) {
        first = x.lb;
        second = x.rt;
      } else {
        first = x.rt;
        second = x.lb;
      }
    }
    Orientation nextOrientation = orientation.next();
    if (first != null && first.rect.distanceSquaredTo(p) < closestDistance) {
      closest = findNearest(first, p, closest, closestDistance,
                            nextOrientation);
      closestDistance = closest.distanceSquaredTo(p);
    }
    if (second != null
          && second.rect.distanceSquaredTo(p) < closestDistance) {
      closest = findNearest(second, p, closest, closestDistance,
                            nextOrientation);
    }
    
    return closest;
  }
}