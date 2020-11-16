import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class b_urgence_a_meudon {




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int Xr = sc.nextInt();
        int Xm = sc.nextInt();
        int nbV = sc.nextInt();
        int busPlace = 0;
        int voitureM = 0;
        int voitureR = 0;
        String line;

        for(int i =0; i<nbV; i++){
            line = sc.nextLine();
            if(line.isEmpty()) {
                i--;
                continue;
            }
            String[] splitLine = line.split(" ");
            if(splitLine[0].equals("b")){
                busPlace += Integer.parseInt(splitLine[1]);
            }
            else{
                if(splitLine[1].equals("m")){
                    voitureM += Integer.parseInt(splitLine[2]);
                }
                else {
                    voitureR += Integer.parseInt(splitLine[2]);
                }
            }
        }

        int voiturePlaceUse = 0;
        int busPlaceUse = 0;


        int tmp = Xm - voitureM;

        voiturePlaceUse +=(tmp < 0)? Xm : voitureM;
        Xm = (tmp<0)? 0 : tmp;

        tmp = Xr - voitureR;
        voiturePlaceUse +=(tmp < 0)? Xr : voitureR;
        Xr = (tmp<0)? 0 : tmp;

        tmp = (Xm + Xr) - busPlace;
        busPlaceUse = (tmp<0)? (Xm+Xr) : busPlace;
        int remaining = (tmp<0)? 0: tmp;

        System.out.println(remaining +" "+ voiturePlaceUse + " " + busPlaceUse);
    }
}
