public class Transaction { 
  private final Date date;
  private final String customerName;
  private final double price;
  
  public Transaction(final Date date, final String customerName, final double price) { 
    this.date = date;
    this.customerName = customerName;
    this.price = price;
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