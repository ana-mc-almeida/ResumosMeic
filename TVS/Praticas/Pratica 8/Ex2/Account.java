package bank;

public class Account {
    
    private Log log;
    private int id;
    private boolean isActive = true;
    private double balance;
    
    public Account(Log log) {
        this(0, 0, log);
    }
    
    public Account(int id, double balance, Log log) {
        this.id = id;
        this.balance = balance;
        log.writeTo(id, OperationType.CREATION, balance, true);
        this.log = log;
    }

    public final boolean isActive() {
        return isActive;
    }
    
    public final int getId() {
        return id;
    }
    
    public final double getBalance() {
        return balance;
    }
  
}
