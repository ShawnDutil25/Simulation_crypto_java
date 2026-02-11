import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Blockchain blockchain = new Blockchain();

        // Création de deux wallets de base
        Wallet wShawn = new Wallet("Shawn", 400, 0);

        blockchain.addWallet(wShawn);

        System.out.println("Bienvenue dans ma simulation de BTC en Blockchain !");
        boolean running = true;

        while (running) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Afficher tous les wallets");
            System.out.println("2. Créer un nouveau wallet");
            System.out.println("3. Faire du mining");
            System.out.println("4. Effectuer une transaction");
            System.out.println("5. Voir toutes les transactions");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    // Affichage des wallets
                    System.out.println("\n--- Wallets ---");
                    for (Wallet w : blockchain.wallets.values()) {
                        System.out.println(w.getOwner() + " (" + w.getPublicKey() + ") : " + w.getBalance() + " BTC");
                    }
                    break;

                case 2:
                    // Créer un wallet
                    System.out.print("Nom du propriétaire : ");
                    String nom = scanner.nextLine();
                    System.out.print("Solde initial : ");
                    double solde = scanner.nextDouble();

                    Wallet newWallet = new Wallet(nom, solde, 0);
                    blockchain.addWallet(newWallet);
                    System.out.println("Wallet créé pour " + nom + " avec " + solde + " $.");
                    break;
                case 3:
                    System.out.println("Mining de BTC");
                    System.out.println("Veuillez choisir la wallet que vous voulez miner du BTC");
                    System.out.println("\n--- Wallets ---");
                    int compteur = 1;
                    for (Wallet w : blockchain.wallets.values()) {
                        System.out.println(compteur + " : " + w.getOwner() + " (" + w.getPublicKey() + ") : " + w.getBalanceBTC() + " BTC");
                        compteur++;
                    }

                    System.out.print("Votre choix : ");
                    int choixWallet = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Veuillez choisir la difficulter");
                    System.out.println("Difficulté 1 : facile (≈ 15% de chance)");
                    System.out.println("Difficulté 3 : moyenne (≈ 3.75% de chance)");
                    System.out.println("Difficulté 5 : difficile (≈ 0.94% de chance)");
                    System.out.println("Difficulté 8 : extrême (≈ 0.12% de chance)");
                    int choixDifficulter = scanner.nextInt();
                    scanner.nextLine();

                    double prob = 0.0;
                    switch (choixDifficulter) {
                        case 1:
                            prob = 0.15;   // Assez souvent
                            System.out.println("Difficulté 1 : facile (≈ 15% de chance)");
                            break;
                        case 3:
                            prob = 0.0375; // Rare
                            System.out.println("Difficulté 3 : moyenne (≈ 3.75% de chance)");
                            break;
                        case 5:
                            prob = 0.0094; // Très rare
                            System.out.println("Difficulté 5 : difficile (≈ 0.94% de chance)");
                            break;
                        case 8:
                            prob = 0.0012; // Presque jamais
                            System.out.println("Difficulté 8 : extrême (≈ 0.12% de chance)");
                            break;
                        default:
                            System.out.println("Difficulté invalide, niveau 3 appliqué par défaut.");
                            prob = 0.0375;
                            break;
                    }

                    Wallet walletChoisie = null;
                    compteur = 1;
                    for (Wallet w : blockchain.wallets.values()) {
                        if(compteur == choixWallet){
                            walletChoisie = w;
                            break;
                        }
                        compteur++;
                    }

                    if(walletChoisie != null){
                        System.out.println("Mining pour " + walletChoisie.getPublicKey());

                        ServeurCentrale.mining(walletChoisie, 20,prob);

                    } else {
                        System.out.println("Wallet invalide !");
                    }

                    break;

                case 4:
                    // Faire une transaction entre compte.
                    System.out.print("Clé publique de l’expéditeur : ");
                    String sender = scanner.nextLine();
                    System.out.print("Clé publique du destinataire : ");
                    String dest = scanner.nextLine();
                    System.out.print("Montant à transférer : ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    blockchain.addTransaction(amount, dest, sender);
                    break;

                case 5:
                    // Voir historique des transactions
                    System.out.println("\n--- Transactions ---");
                    blockchain.printlistTransaction();
                    break;

                case 6:
                    // Fermer la transaction
                    running = false;
                    System.out.println("Fermeture de la simulation...");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        }

        scanner.close();
    }
}
