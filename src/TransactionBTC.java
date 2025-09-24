/**
 * Classe de transaction de BTC
 */
public class TransactionBTC {
    String senderkey;
    String destinationkey;
    double amount;
    TransactionBTC nextTransactionBTC;

    public TransactionBTC(double amount, String destinationkey, String senderkey){
        this.amount = amount;
        this.senderkey = senderkey;
        this.destinationkey = destinationkey;
        this.nextTransactionBTC = null;
    }
}
