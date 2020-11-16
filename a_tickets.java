import java.util.Scanner;

public class a_tickets {
    public static void main(String[] args) {
        // read and store the input numbers as integers
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p1 = sc.nextInt();
        int p2 = sc.nextInt();
        int p3 = sc.nextInt();

        int sum = 0;
        int selected = Math.min(100,n);
        sum+= selected*p1;
        n = n-selected;
        selected = Math.min(100,n);
        sum+= selected*p2;
        n = n-selected;
        sum+= n * p3;

        System.out.println(sum);

    }
}
