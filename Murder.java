import java.util.*;

public class Murder {

    public static int findIndexDicho(List<Integer> list, int value){
        if(list.size()==0) return 0;
        int n = list.size();
        int bas =  0, haut =  n, milieu ;
        do{
            milieu =  (bas + haut) / 2;
            if(milieu == n) return n;
            if ( value == list.get(milieu))  continue;
            else if  ( list.get(milieu) < value )  bas =  milieu + 1 ;
            else haut =  milieu-1 ;
        }
        while ( ( value != list.get(milieu) ) && ( bas <= haut ) );
        if(list.get(milieu)!=value) return milieu+1;
        return milieu;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nbCase = scanner.nextInt();
        for(int i = 0; i< nbCase;i++){
            int nbValue = scanner.nextInt();
            long sum = 0;
            LinkedList<Integer> arr = new LinkedList<>();
            for(int j = 0; j<nbValue;j++){
                int current = scanner.nextInt();
                int index = findIndexDicho(arr,current);

                arr.add(index,current);
                for(int k = 0; k<index; k++){
                    if(arr.get(k) != current)
                        sum+= arr.get(k);
                }
            }
            System.out.println(sum);


//            int nbValue = scanner.nextInt();
//            int[] arrInput = new int[nbValue];
//            int max = 0;
//            for(int j = 0; j<nbValue;j++){
//                arrInput[j] = scanner.nextInt();
//                if(arrInput[j]>max){
//                    max = arrInput[j];
//                }
//            }
//            long sum=0;
//            int[] res = new int[max+1];
//            for(int j = 0; j<nbValue; j++){
//                res[arrInput[j]] +=1;
//                for(int k = 0; k<arrInput[j]; k++){
//                    sum+= res[k]*k;
//                }
//            }
//            System.out.println(sum);
        }
    }
}
