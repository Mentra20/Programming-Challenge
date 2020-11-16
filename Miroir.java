

import java.util.Scanner;

public class Miroir {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String[] input = sc.nextLine().split(" ");
            long value = Long.decode(input[0]);
            int bitMirror = Integer.parseInt(input[1]);
            if(bitMirror<2 || bitMirror>32){
                System.out.println(input[0]);
                continue;
            }
            long result = value;
            result>>=bitMirror;
            for(;bitMirror!=0;bitMirror--){
                result<<=1;
                result|= 1&value;
                value>>=1;
            }
            System.out.println("0x"+Long.toHexString(result).toUpperCase());
        }
    }
}
