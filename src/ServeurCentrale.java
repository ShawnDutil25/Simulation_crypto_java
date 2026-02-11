import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServeurCentrale {

    private static final Random RANDOM = new Random();
    private static final double PROB_MINING = 0.2;

    private static double computeProbMining(double difficulte) {

        return PROB_MINING / Math.pow(2, difficulte);
    }

    private static double blocRecompense(){
        return 1.0 + (4.0 - 1.0) * RANDOM.nextDouble();
    }

    private static double preparerBloc(double blocTrouver){
        double blocBTC= 0.0;
        for (int i = 0; i < (int) blocTrouver; i++) {
            blocBTC += blocRecompense();
        }

        return blocBTC;
    }

    private static int essaiMining(int essais, double difficulter){
        int blocTrouve = 0;
        for (int i = 0; i < essais; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.print("Essai #" + i + " ... ");
            if (RANDOM.nextDouble() < computeProbMining(difficulter)) {
                blocTrouve++;
                System.out.println("- Bloc trouvé !");
            } else {
                System.out.println("- Échec.");
            }
        }
        return blocTrouve;
    }


    public static void mining(Wallet wallet, int nbreEssais, double difficulter){
        if(wallet == null){
            return;
        }

        int blocsTrouves = essaiMining(nbreEssais, difficulter);
        double blocBTC = preparerBloc(blocsTrouves);

        wallet.depositBTC(blocBTC);

        System.out.printf("%d bloc(s) trouvé(s) pour %s, BTC gagné = %.2f%n \n", blocsTrouves, wallet.getOwner(), blocBTC);

        System.out.println("Nouvelle balance BTC : " + wallet.getBalanceBTC());
    }
}
