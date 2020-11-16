#include <iostream>
#include <string>
#include <sstream> 

using namespace std;

int n, m;
char** grid;
char** copy_grid;
int* n_manquant;
int* m_manquant;

int countLine(string s) {
    int nbTiret = 0;
    for (int i = 0;i < s.size();i++) {
        if (s.at(i) == '-') {
            nbTiret++;
        }
    }
    return nbTiret;
}

int countCol(char* s[],int numCol, int n) {
    int nbTiret = 0;

    for (int i = 0;i < n;i++) {
        if (s[i][numCol] == '-') {
            nbTiret++;
        }
    }
    return nbTiret;
}

void copyGrid(char* s[]) {
    copy_grid = new char* [n];

    for (int i = 0;i < n;i++) {
        copy_grid[i] = new char[m];
        for (int j = 0;j < m;j++) {
            copy_grid[i][j] = grid[i][j];
        }
    }
}


int rec(int missing, int startL, int startC) {
    if (missing == 0) {
        copyGrid(grid);
        return 1;
    }

    int nb_completed = 0;
    for (int line = startL; line < n; line++) {
        if (n_manquant[line] > 0) {
            for (int col = 0; col < m; col++) {
                if (line == startL && col < startC) {
                    continue;
                }
      
                if (m_manquant[col] > 0 && grid[line][col] != '-') {
                    grid[line][col] = '-';
                    n_manquant[line]--;
                    m_manquant[col]--;

                    nb_completed += rec(missing - 1, line, col);

                    if (nb_completed >= 2) {
                        return 2;
                    }

                    grid[line][col] = '*';
                    n_manquant[line]++;
                    m_manquant[col]++;
                }
            }
            if (n_manquant[line] != 0) { //SI ON A LAISSER UNE LIGNE AVEC UN TIRET MANQUANT INUTILE DE CONTINUER.
                return nb_completed;
            }
        }
    }
    return nb_completed;
}


int main2() {
  
    
    cin >> n >> m;

    //SKIP \n
    std::cin.ignore();

    grid = new char*[n];

    for (int i = 0;i < n;i++) {
        grid[i] = new char[m];
        for (int j = 0;j < m;j++) {
            cin >> grid[i][j];
        }
    }

    //------------------------------
    int* total_line = new int[n];
    int* total_col = new int[m];

    for (int i = 0;i < n;i++) {
        cin >> total_line[i];
    }

    for (int i = 0;i < m;i++) {
        cin >> total_col[i];
    }

    //------------------------------
    n_manquant = new int[n];
    m_manquant = new int[m];
    int missing = 0;

    for (int i = 0;i < n;i++) {
        n_manquant[i] = total_line[i] - countLine(grid[i]);
        //Calculé dynamiquement
        missing += n_manquant[i];
    }

    for (int i = 0;i <m;i++) {
        m_manquant[i] = total_col[i] - countCol(grid,i,n);
    }

    

    //------------------------
    for (int line=0; line < n; line++) {
        if (total_line[line] == m) {
            for (int col = 0; col < m; col++) {
                if (grid[line][col] == '*') {
                    grid[line][col] = '-';
                    n_manquant[line]--;
                    m_manquant[col]--;

                    //Calculé dynamiquement
                    missing--;
                }
            }
        }
    }

    for (int col = 0; col < m; col++) {
        if (total_col[col] == n) {
            for (int line = 0; line < n; line++) {
                if (grid[line][col] == '*') {
                    grid[line][col] = '-';
                    n_manquant[line]--;
                    m_manquant[col]--;

                    //Calculé dynamiquement
                    missing--;
                }
            }
        }
    }

    if (rec(missing, 0, 0) == 1) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cout << copy_grid[i][j];
            }
            cout << std::endl;
        }
    }
    else {
        cout << "NON";
    }
    return 0;
}