public class Evaluate {
  public static void main(String[] args) { 
    
    Stack<String> ops  = new Stack<String>();
    Stack<Integer> vals = new Stack<Integer>();
    
    String s1 = args[0].replace(" ", "");
    
    for(int i = 0; i < s1.length(); i++) { 
      String s = String.valueOf(s1.charAt(i));
      if      (s.equals("(") || s.equals(")"))               ;
      else if (s.equals("+"))    ops.push(s);
      else if (s.equals("-"))    ops.push(s);
      else if (s.equals("*"))    ops.push(s);
      else if (s.equals("/"))    ops.push(s);
      else if (s.equals("sqrt")) ops.push(s);
      else { vals.push(Integer.parseInt(s)); }
      
      if(vals.size() >=2 && ops.size() >= 1) { 
        String op = ops.pop();
        int v = vals.pop();
        if      (op.equals("+"))    v = vals.pop() + v;
        else if (op.equals("-"))    v = vals.pop() - v;
        else if (op.equals("*"))    v = vals.pop() * v;
        else if (op.equals("/"))    v = vals.pop() / v;
        else if (op.equals("sqrt")) v = (int) Math.sqrt(v);
        vals.push(v);
      }
    }
    StdOut.println(vals.pop());
  }
}
