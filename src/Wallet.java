import java.util.UUID;

/**
 * Classe de portefeuille de chaque client.
 */
public class Wallet {
    public String publicKey;
    private String privateKey;
    private String owner;
    private double balance;
    private double balanceBTC;
    private final double TAUX_BTC = 91648.09;

    public Wallet (String owner, double balance, double balanceBTC){
        this.owner = owner;
        this.balanceBTC = balanceBTC;
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

    public double getBalanceBTC(){
        return balanceBTC;
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

    public boolean SubstractAmountBalanceBTC(double amount){
        if(amount > 0 && this.balanceBTC >= amount){
            this.balanceBTC -= amount;
            this.balance -= convertirBTCenUSD(amount);
            return true;
        }

        return false;
    }

    public void depositBTC(double amount) {
        if(0 > amount) return;
        this.balanceBTC += amount;
        this.balance += convertirBTCenUSD(amount);
    }

    private double convertirBTCenUSD(double BTC){
        return BTC * TAUX_BTC;
    }
}
