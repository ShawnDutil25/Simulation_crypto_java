public class Main {
    public static void main(String[] args) {

        Wallet wAlice = new Wallet("Alice", 100);
        System.out.println(wAlice.getOwner() + " a " + wAlice.getBalance() + " BTC");

        System.out.println("Depot de 50 BTC");
        wAlice.deposit(50);
        System.out.println("Après dépôt: " + wAlice.getBalance());



        Wallet wShawn = new Wallet("Shawn", 400);
        System.out.println(wShawn.getOwner() + " a " + wShawn.getBalance() + " BTC");

        System.out.println("Depot de 50 BTC");
        wShawn.deposit(50);
        System.out.println("Après dépôt: " + wShawn.getBalance());

        if (wShawn.withdraw(80)) {
            System.out.println("Retrait OK, nouveau solde: " + wShawn.getBalance());
        } else {
            System.out.println("Retrait refusé !");
        }

        System.out.println("Transaction");
        Blockchain blockchain = new Blockchain();

        blockchain.addWallet(wShawn);
        blockchain.addWallet(wAlice);

        blockchain.addTransaction(100.0, wShawn.getPublicKey(), wAlice.getPublicKey());

        blockchain.printlistTransaction();


        System.out.println("Balance de Shawn : " + wShawn.getBalance());
        System.out.println("Balance d'Alice : " + wAlice.getBalance());



    }
}