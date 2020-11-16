
import java.util.Arrays;
import java.util.Scanner;

public class c_tout_le_monde_entend {//MARCHE PAS

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nbRang = sc.nextInt();
        int[] rang = new int[nbRang];
        for (int i = 0; i < nbRang; i++) {
            rang[i] = sc.nextInt();
        }

        int nbPersonne = sc.nextInt();


        int[] audition = new int[nbPersonne];
        for (int i = 0; i < nbPersonne; i++) {
            audition[i] = sc.nextInt() - 1;
        }
        Arrays.sort(audition);
        int vide = 0;
        for (int i = 0; i < nbPersonne; i++) {

            for (int j = vide; j <= audition[i] && j < nbRang; j++) {
                if (j == vide && rang[j] == 0) {
                    vide++;
                }
                if (audition[i] == j && rang[audition[i]] == 0) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
                rang[j]--;

            }

        }
        System.out.println("POSSIBLE");
    }
}
