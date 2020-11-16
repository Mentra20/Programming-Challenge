import java.util.Arrays;
import java.util.Scanner;

public class e_bingo_maudit {//MARCHE PAS
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nbSalle = sc.nextInt();
        int nbCouloir = sc.nextInt();
        int[] couloir = new int[nbSalle];
        for(int i = 0; i<nbCouloir;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            couloir[a] +=1;
            couloir[b] +=1;

        }
        int min = -1;
        for(int i = 0; i<couloir.length; i++){
            if(min ==-1 || min>couloir[i]) min = couloir[i];
        }
        if(min==-1) min = 0;
        System.out.println(min);
    }
}
