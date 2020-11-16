import java.util.Scanner;

public class b_nom_de_groupe {

    public static void main(String[] args) {
        // read and store the input numbers as integers
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        int sum = 0;
        if (input.contains("ker")) sum +=5;
        String voyelle = "aeiouy";
        int vnb = 0;
        int lnb = 0;
        for(char c : input.toCharArray()){
            if(voyelle.contains(""+c)) vnb += 1;
            else lnb++;
        }
        int j = input.length()-1;
        boolean isPal = true;
        for(int i =0; i<=j;i++,j--){
            if(input.charAt(i)!=input.charAt(j)){
                isPal = false;
                break;
            }
        }

        sum+=(vnb*2-lnb);
        if(isPal && sum>0) sum *=2;



        System.out.println(sum);

    }
}
