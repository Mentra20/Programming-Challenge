import java.util.ArrayList;
import java.util.Scanner;

public class IsItATree {

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        if(N != M+1){//NE PEUT PAS ETRE UN ARBRE
            System.out.println("NO");
            return;
        }

        if(N == 1){//UN SOMMET EST UN ARBRE
            System.out.println("YES");
            return;
        }

        boolean[] tab = new boolean[N];
        ArrayList<int[]> file = new ArrayList<>();

        int a1 = sc.nextInt()-1;
        int a2 = sc.nextInt()-1;

        tab[a1] = true;
        tab[a2] = true;

        for(int i=0;i<M-1;i++){
            a1 = sc.nextInt()-1;
            a2 = sc.nextInt()-1;

            if(tab[a1] && tab[a2]){//CYCLE
                System.out.println("NO");
                return;
            }
            if(tab[a1] || tab[a2]){//NOUVELLE ARRETE
                tab[a1] = true;
                tab[a2] = true;
            }
            else{//ON NE SAIS PAS ENCORE (PAS D'ARRETE CONNECTEE A NOTRE ARBRE ACTUEL)
                int[] el = new int[]{a1,a2};
                file.add(el);
            }
        }

        boolean change = false;

        while(file.size() != 0){//TANT QUE L'ON A PAS TOUT TRAITER
            for(int i = 0; i<file.size();i++){
                int el1 = file.get(i)[0];
                int el2 = file.get(i)[1];

                if(tab[el1] && tab[el2]){//CYCLE
                    System.out.println("NO");
                    return;
                }
                if(tab[el1] || tab[el2]){//NOUVELLE ARRETE
                    tab[el1] = true;
                    tab[el2] = true;
                    file.remove(i);
                    change = true;
                    break;
                }
            }

            if(!change){//SI PAS DE CHANGEMENT = NON CONNEXE
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");//REPRESENTE UN ARBRE BINAIRE
        return;
    }
}
