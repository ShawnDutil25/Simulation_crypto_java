import java.util.UUID;

/**
 * Classe de portefeuille de chaque client.
 */
public class Wallet {
    public String publicKey;
    private String privateKey;
    private String owner;
    private double balance;

    public Wallet (String owner, double balance){
        this.owner = owner;
        this.balance = balance;
        this.publicKey = UUID.randomUUID().toString();
        this.privateKey = UUID.randomUUID().toString();
    }
    public Wallet(String publicKey) {
        this.publicKey = publicKey;
        this.privateKey = "hidden";
    }

    public String getOwner(){
        return owner;
    }

    public String getPublicKey(){
        return publicKey;
    }

    public double getBalance() {
        return balance;
    }

    public boolean SubstractAmountBalance(double amount){
        if(amount > 0 && this.balance >= amount){
            this.balance -= amount;
            return true;
        }

        return false;
    }

    public void deposit(double amount) {
        if(0 > amount) return;
        this.balance += amount;
    }

    public boolean withdraw(double amount){
        if(amount > 0 && balance >= amount){
            this.balance -= amount;
            return true;
        }

        return false;
    }
}
