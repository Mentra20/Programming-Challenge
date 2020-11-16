import java.util.Arrays;
import java.util.Scanner;

public class MakingBook {

    public static void printSoluce(int caseNb, int[] soluce){
        System.out.print("Case "+caseNb+": ");
        for(int i=0 ; i<soluce.length; i++){
            System.out.print(i+":"+soluce[i] + " ");
        }
        System.out.println();

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNb =0;
        int A =0;
        int B =0;
        int nb = -1;
        do{
            nb = scanner.nextInt();
            caseNb++;

            if(nb == 0) continue;
            A = nb;
            B = scanner.nextInt();
            if(A>B){
                int temp = B;
                B = A;
                A = temp;
            }
            int[] res = new int[10];
            for(int i = A; i<=B; i++) {
                int tmp = i;
                while (tmp != 0) {
                    res[tmp % 10] +=1;
                    tmp = tmp/10;
                }
            }
            printSoluce(caseNb,res);
        }
        while(nb != 0);


//        do{
//            nb = scanner.nextInt();
//            caseNb++;
//
//            if(nb == 0) continue;
//
//            A = nb;
//            B = scanner.nextInt();
//
//            if(A > B ){
//                int temp = B;
//                B = A;
//                A = temp;
//            }
//
//
//            int diff = B - A +1;
//            int[] res = new int[10];
//            for(int i =0; i<10; i++){
//                res[i] = diff/10;
//            }
//
//
//            if(diff % 10 != 0){
//                for(int i =0; i<diff%10; i++) {
//                    res[(A+i)%10] += 1;
//                }
//            }
//            A = A/10;
//            B = B/10;
//
//            for(int i =A%10; i<=B%10; i++) {
//                res[i] += diff;
//            }
//
//            System.out.println(Arrays.toString(res));
//
//        }
//        while(nb != 0);

    }
}
