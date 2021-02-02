#include <stdio.h>
#include <math.h>
#include <iostream>

using namespace std;

//Permet de calculer la distance euclidienne entre deux vecteurs.
long double computeDist(int* v1, int* v2, int size) {

    long double dist = 0;

    for (int i = 0; i < size; i++) {
        dist += pow(v1[i] - v2[i], 2);
    }
    return sqrt(dist);
}

//Permet de remettre au debut de la liste L et indexList (liste des index) 
//l'élément à l'index idx.
void moveToStart(int** L, int* indexList, int idx) {
    if (idx == 0)//Pas besoin de bouger si c'est déjà le premier élément
        return;

    int* tmp = L[idx];
    int tmpIdx = indexList[idx];

    while (idx > 0) {//Décalage
        L[idx] = L[idx - 1];
        indexList[idx] = indexList[idx - 1];
        idx -= 1;
    }

    //L'élément à l'index idx deviens le premier.
    L[0] = tmp;
    indexList[0] = tmpIdx;
}

//Donne un dictionnaire qui contient les distances deux à deux entre chaque vecteur d'une liste.
long double** dictionaryDist(int** vectorList, int sizeArray, int sizeVector) {

    long double** M = new long double* [sizeArray];

    for (int i = 0; i < sizeArray; i++) {
        M[i] = new long double[sizeArray];
    }

    for (int i = 0; i < sizeArray; i++) {
        for (int j = i + 1; j < sizeArray; j++) {
            //Calcul des distance entres les vecteurs deux à deux.
            long double dist = computeDist(vectorList[i], vectorList[j], sizeVector);
            M[i][j] = dist; //Dist de a à b = dist de b à a
            M[j][i] = dist;
        }
    }
    return M;
}

//Renvoie une liste qui pour chaque vecteur contient la distance vers le vecteur le plus proche.
long double* minDistArray(long double** M, int size) {

    long double* minDist = new long double[size] {-1};//INIT

    for (int i = 0; i < size;i++) {
        for (int j = i + 1; j < size; j++) {
            long double dist = M[i][j];//Distance de i à j.

            if (minDist[i] == -1 || minDist[i] > dist) {//Si la distance de i à j est la plus petite pour i.
                minDist[i] = dist;
            }
            if (minDist[j] == -1 || minDist[j] > dist) {//Si la distance de i à j est la plus petite pour j.
                minDist[j] = dist;
            }
        }
    }
    return minDist;
}

//Trouve le vecteur le plus proche du vecteur vector.. 
int findClosest(long double** M, int** vectorList, int* listIdx, long double* listMinDist, int* vector, int sizeArray, int sizeVector) {
    //On pose X = vector;

    bool* eliminate = new bool[sizeArray] {false};//True pour chaque vecteur éliminés.

    int* cn = vectorList[0];//Cn = vecteur supposé le plus proche de vector.

    long double distcn = computeDist(cn, vector, sizeVector);//Distance entre Cn et X.

    //Si dist(Cn,X) est plus petit que la moitié de la distance du vecteur le plus proche de Cn :
    //Alors cn est le vecteur le plus proche de X.
    if (distcn * 2 < listMinDist[listIdx[0]]) {
        return 0;
    }
    int cnIndex = 0; //Index du Cn courant.

    for (int i = 1; i < sizeArray; i++) {//On va essayer de trouver Cn le vecteur le plus proche.

        if (eliminate[i]) {//Pas de test des vecteurs éliminés
            continue;
        }

        int* cs = vectorList[i];//Cs vecteur qui sert à l'élimination par inégalité triangulaire.
        long double distcs = computeDist(cs, vector, sizeVector);//Dist(Cs,X)
        int csIndex = i;//Index de Cs courant.

        //Si Cs est plus proche que Cn ou égalité mais index plus petit.
        if (distcs < distcn or (listIdx[csIndex] < listIdx[cnIndex] and distcs == distcn)) {

            // Si dist(Cs, X) est plus petit que la moitié de la distance du vecteur le plus proche de Cs :
            //Alors Cs est le vecteur le plus proche de X.
            if (distcs * 2 < listMinDist[listIdx[csIndex]]) {
                return i;
            }
            //Cn deviens Cs et vis-versa.
            swap(distcs, distcn);
            swap(cs, cn);
            swap(cnIndex, csIndex);
        }

        long double csPluscn = distcs + distcn;//Dist(Cs,X) + Dist(Cn,X)
        long double csMoinscn = distcs - distcn;//Dist(Cs,X) - Dist(Cn,X)

        /* Phase d'élimination */
        for (int j = i + 1; j < sizeArray; j++) {
            if (eliminate[j]) {//Pas besoin d'éliminé un vecteur qui l'est déjà.
                continue;
            }
            //Cp vecteur potientiellement éliminable.
            long double cpToCs = M[listIdx[csIndex]][listIdx[j]];//Dist(Cp,Cs)

            //Inégalité triangulaire (les deux versions)
            if (cpToCs > csPluscn || cpToCs < csMoinscn || M[listIdx[cnIndex]][listIdx[j]] > 2 * distcn) {
                eliminate[j] = true;//Elimination.
            }
        }
    }
    return cnIndex;
}

int main() {
    //on desactive la synchronisation sur le i/o(utile pour le thread safe mais couteux en temps)
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    //Récupération des données
    int lenPicture, lenMatrix, nbMatrix;
    cin >> lenPicture;
    cin >> lenMatrix;
    cin >> nbMatrix;
    int** vectorList = new int* [nbMatrix];
    int* listIdx = new int[nbMatrix];

    int vectorSize = pow(lenMatrix, 2);
    //Récupération du dictionnaire de vecteur.
    for (int i = 0; i < nbMatrix; i++) {
        int* currentVect = new int[vectorSize];
        for (int j = 0; j < vectorSize; j++) {
            cin >> currentVect[j];
        }
        vectorList[i] = currentVect;
        listIdx[i] = i;
    }

    int nbMatrixLine = lenPicture / lenMatrix;//Nb de matrice par ligne
    int numberVector = nbMatrixLine * nbMatrixLine;//Nb de vecteur total de l'image.
    int** imageVector = new int* [numberVector];

    for (int i = 0; i < numberVector; i++) {
        imageVector[i] = new int[vectorSize];
    }

    //Récupération de chaque vecteur de l'image.
    for (int rowIndexMatrix = 0; rowIndexMatrix < nbMatrixLine; rowIndexMatrix++) {

        int decalage = rowIndexMatrix * nbMatrixLine;//Index réél de la ligne

        for (int i = 0; i < lenMatrix; i++) {
            for (int j = 0; j < lenPicture; j++) {
                int value;
                cin >> value;

                //On met chaque valeur dans le bon vecteur de l'image.
                imageVector[decalage + (j / lenMatrix)][j % lenMatrix + i * lenMatrix] = value;
            }
        }
    }

    //debut du traitement
    long double** dic = dictionaryDist(vectorList, nbMatrix, vectorSize);//Dictionnaire des distance deux à deux du dico des vecteurs.
    long double* listMinDist = minDistArray(dic, nbMatrix);//Liste des distance minimale entre les vecteurs du dico.

    for (int currentVectorIndex = 0; currentVectorIndex < numberVector; currentVectorIndex++) {

        if (currentVectorIndex % nbMatrixLine == 0 && currentVectorIndex != 0)//Fin de ligne.
            cout << std::endl;

        //On cherche le vecteur le plus proche.
        int idxResult = findClosest(dic, vectorList, listIdx, listMinDist, imageVector[currentVectorIndex], nbMatrix, vectorSize);
        cout << listIdx[idxResult] << " ";//On affiche l'index du vecteur trouvé.
        moveToStart(vectorList, listIdx, idxResult);//Le dernier vecteur trouvé est placé en premier car souvent contigüe.
    }

    return 0;
}

