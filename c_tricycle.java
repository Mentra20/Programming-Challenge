import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class c_tricycle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int n= sc.nextInt();
        int[] list = new int[n];

        for(int i = 0; i<n;i++){
            String line = sc.nextLine();
            if(line.isEmpty()) {
                i--;
                continue;
            }
            list[i] = Integer.parseInt(line)+1;
        }

        int poids = W+1;
        //probleme du sac avec prix = poids
        int[][] matrix = new int[n+1][poids+1];

        for(int i = 1; i<=n ; i++){
            for(int j = 0; j<= poids; j++){
                if(list[i-1]>j){
                    matrix[i][j] = matrix[i-1][j];
                }
                else{
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][j - list[i-1]] + list[i-1]);
                }
            }

        }
        System.out.println(((matrix[n][poids])==poids)? "OUI" : "NON");

    }
}
