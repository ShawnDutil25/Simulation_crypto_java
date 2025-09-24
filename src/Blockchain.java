import java.util.HashMap;

public class Blockchain {
    // Noeud du debut
    TransactionBTC head;

    TransactionBTC tail;

    HashMap<String,Wallet> wallets = new HashMap<>();

    /**
     * Ajouter un wallet déja existant pour faire la transaction entre les comptes.
     * @param wallet
     */
    public void addWallet(Wallet wallet){
        wallets.put(wallet.getPublicKey(),wallet);
    }

    /**
     * Pouvoir allé chercher la wallet avec sa clé public.
     * @param publicKey
     * @return la wallet rechercher.
     */
    public Wallet getWallet(String publicKey){
        return wallets.get(publicKey);
    }

    /**
     * Ajouter un nouveau noeud
     * @param newAmount
     * @param
     */
    public void addTransaction(double newAmount, String destinationKey, String senderKey){

        Wallet walletSender = wallets.get(senderKey);
        Wallet walletDestination = wallets.get(destinationKey);

        if (walletSender == null) {
            System.out.println("Erreur : le wallet de l'expéditeur (" + senderKey + ") n'existe pas !");
            return;
        }

        if (walletDestination == null) {
            System.out.println("Erreur : le wallet du destinataire (" + destinationKey + ") n'existe pas !");
            return;
        }


        if(walletSender.SubstractAmountBalance(newAmount)){
            walletDestination.deposit(newAmount);
            // Cree un nouveau noeud
            TransactionBTC newTransactionBTC = new TransactionBTC(newAmount, destinationKey, senderKey);

            // Verifie si le head existe, sinon le newNode est egale au nouveau node
            if(head == null){
                head = newTransactionBTC;
                tail = newTransactionBTC;
            }
            else{



                // Avec le tail
                // Pas besoin de parcourir, va directement au dernier noeud.
                tail.nextTransactionBTC = newTransactionBTC;
                tail = newTransactionBTC;



                // Si tu tous parcourir les noeuds pour en ajouter un (sans le tail)

                // Tu ajoute le head comme prochaine node temporaire
                //TransactionBTC temp = head;

                // Tant qu'il existe un noeud apres qui n'est pas vide, on avance.
                //while(temp.nextTransactionBTC != null){
                //    temp = temp.nextTransactionBTC;
                //}


                // Le prochain noeud du dernier noeud existant est null donc, on ajoute ici le nouveau noeud.
                //temp.nextTransactionBTC = newTransactionBTC;
            }
        }
        else{
            System.out.println("Erreur : solde insuffisant pour " + senderKey);
        }



        

    }

    public void printlistTransaction(){
        TransactionBTC temp = head;
        while (temp != null)
        {
            System.out.println("De " +temp.senderkey + " à " + temp.destinationkey );
            System.out.println("Le montant de la transaction : " + temp.amount);
            System.out.println("--------------------------------------------------------");
            temp = temp.nextTransactionBTC;
        }
    }
}
