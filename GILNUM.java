import java.util.Scanner;

public class GILNUM  {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String num1 = sc.nextLine().trim();
            int num1Len = num1.length();
            String num2 = sc.nextLine().trim();
            int num2Len = num2.length();

            if(num1.isEmpty() || num2.isEmpty()){
                System.out.println("?");
                continue;
            }

            int maxLength = Math.max(num1.length(),num2.length());
            int[] numTab = new int[maxLength+1];//+1 pour addition overflow.
            boolean badNumber = false;

            for(int i = 0; i<maxLength;i++){
                if(i < num1Len){
                    int value = charToInt(num1.charAt(num1Len-i-1));
                    if(value<0 || value>9){
                        badNumber = true;
                        break;
                    }
                    numTab[i] += value;
                }
                if(i < num2Len){
                    int value = charToInt(num2.charAt(num2Len-i-1));
                    if(value<0 || value>9){
                        badNumber = true;
                        break;
                    }
                    numTab[i] += value;
                }
                if(numTab[i]>=10) {
                    numTab[i] = numTab[i] % 10;
                    numTab[i + 1] += 1;
                }
            }
            if(badNumber){
                System.out.println("?");
            }
            else {
                boolean print = false;
                for (int i = numTab.length-1; i >= 0; i--) {

                    if(print||numTab[i] != 0){
                        print = true;
                        System.out.print(numTab[i]);
                    }
                }
                if (!print){
                    System.out.print("0");
                }
                System.out.println();

            }
        }
    }

    public static int charToInt(char c){
        return (int) c - (int) '0';
    }

}
