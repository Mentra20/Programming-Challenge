import java.util.Scanner;

public class a_retransmission {
    public static void main(String[] args) {

        // read and store the input numbers as integers
        Scanner sc = new Scanner(System.in);
        int Xtv = sc.nextInt();
        int M = sc.nextInt();
        int Xrad = sc.nextInt();
        int Xi = sc.nextInt();

        int result =Xtv *M + Xrad + Xi;
        // then print the result
        System.out.println(result);

    }
}
