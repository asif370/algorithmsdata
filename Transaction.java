public class Transaction { 
  
  /** Written by Ryan D'souza
    * Algorithms and Data Structures 
    * Represents a Transaction */
  
  private final Date date;
  private final String customerName;
  private final double price;
  
  public Transaction(final String customerName, final Date date, final double price) { 
    this.customerName = customerName;
    this.date = date;
    this.price = price;
  }
  
  /** Constructor with String in this format: "Dsouza 11/29/1996 12.99 */
  public Transaction(final String allParams) throws Exception { 
    final String[] allItems = allParams.split(" ");
    
    if(allItems.length < 2) { 
      throw new Exception("Invalid Constructor ");
    }
    
    this.customerName = allItems[0];
    this.date = new Date(allItems[1]);
    this.price = Double.parseDouble(allItems[2]);
  }
  
  @Override
  public String toString() { 
    return this.customerName + " " + this.date.toString() + " " + this.price;
  }
  
  public Date getDate() { 
    return this.date;
  }
  
  public String getName() { 
    return this.customerName;
  }
  
  public double price() { 
    return this.price;
  }
}