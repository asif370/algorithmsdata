public class Date { 
  private final int month, day, year;
  
  public Date(final int month, final int day, final int year) { 
    this.month = month;
    this.day = day;
    this.year = year;
  }
  
  /** Constructor w/ paramater like "11/29/1996" */
  public Date(final String stringVersion) throws Exception { 
    final String[] sections = stringVersion.split("/");
    
    if(sections.length < 2) { 
      throw new Exception("Invalid constructor ");
    }
    
    this.month = Integer.parseInt(sections[0]);
    this.day = Integer.parseInt(sections[1]);
    this.year = Integer.parseInt(sections[2]);
  }
  
  @Override
  public String toString() { 
    return this.month + "/" + this.day + "/" + this.year;
  }
  
  public int getMonth() { 
    return this.month;
  }
  
  public int getDay() { 
    return this.day;
  }
  
  public int getYear() { 
    return this.year;
  }
}