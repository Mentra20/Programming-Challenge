import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;

        while ((line = sc.nextLine())!=null){
            if(line.contains(".")) break;
            String[] elt = line.split(" ");
            int nb = Integer.parseInt(elt[1]);
            StringBuilder buffer = new StringBuilder();

            for(int i = 0; i<nb; i++){
                buffer.append(elt[0]);
            }
            int size = elt[0].length();
            for(int k = 0; k<size;k++) {
                System.out.println(buffer.substring(k,buffer.length())+buffer.substring(0,k));
            }

//            String word = buffer.toString();// + buffer.toString();
//            int size = elt[0].length();
//            for(int k = 0; k<size;k++) {
//                for (int j = k; j < size*nb+k; j++) {
//                    System.out.print(word.charAt(j));
//                }
//                System.out.println();
//            }
        }
    }
}
