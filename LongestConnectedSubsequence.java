import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LongestConnectedSubsequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nbCase = sc.nextInt();
        for(int i = 0; i<nbCase;i++) {
            int nbByte = sc.nextInt();
            int nb1Ignore = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine();
            ArrayList<Integer> input = new ArrayList<>();
            input.add(-1);
            for(int j = 0; j<line.length();j++){
                if(line.charAt(j) == '1'){
                    input.add(j);
                }
            }
            input.add(line.length());

            for(int a = 0; a+nb1Ignore<input.size();a++){
                
            }
        }
    }
}
