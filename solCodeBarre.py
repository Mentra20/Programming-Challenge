
n,m = map(int, input().split())

grid = [list(input()) for _ in range(n)]
total_line = list(map(int, input().split()))
total_col = list(map(int, input().split()))

n_manquant = [total_line[i] - grid[i].count("-") for i in range(n)] #NOMBRE MANQUANT PAR LIGNE
m_manquant = [total_col[i] - [grid[j][i] for j in range(n)].count("-") for i in range(m)] #NOMBRE MANQUANT PAR COLONNE


for line in range(n): #EXCLUSION DES CAS TRIVIAUX POUR LES LIGNES
    if total_line[line] == m: 
        for col in range(m):
            if grid[line][col] == "*":
                grid[line][col] = "-"
                n_manquant[line] -= 1
                m_manquant[col] -= 1


for col in range(m):#EXCLUSION DES CAS TRIVIAUX POUR LES COLONNES
    if total_col[col] == n:
        for line in range(n):
            if grid[line][col] == "*":
                grid[line][col] = "-"
                n_manquant[line] -= 1
                m_manquant[col] -= 1

missing = sum(n_manquant) # == sum(m_manquant)

#LISTE DES POSSIBILITE
possibility = [(i,j) for i in range(n) for j in range(m) if(m_manquant[j]>0 and n_manquant[i] >0 and grid[i][j] != '-')]

''' SON EQUIVALENT : 
possibility = [] #LISTE DES POSSIBILITE
for i in range(n):
    if(n_manquant[i]>0):
        for j in range(m):
            if(m_manquant[j]>0 and grid[i][j]!="-"):
                possibility.append((i,j))
'''

linePossibility = [ 0 for i in range(n)] #POSSIBILITE PAR LIGNE
colPossibility = [0 for i in range(m)] #POSSIBILITE PAR COLONNE

for pos in possibility: #CALCULE DES POSSIBILITE
    linePossibility[pos[0]] += 1 
    colPossibility[pos[1]] += 1 


while(missing>0): #TANT QU'IL RESTE DES POSSIBILITE
    asChange = False
    i =0
    while(i <len(possibility)):

        a = possibility[i][0]
        b = possibility[i][1]

        if(m_manquant[b] == 0 or n_manquant[a]==0):#POSSIBILITE QUI N'EXISTE PLUS
            possibility.pop(i)

        elif(m_manquant[b] == colPossibility[b] or n_manquant[a] == linePossibility[a]): #POSSIBILITE EVIDENTE

            asChange = True

            if(n_manquant[a] == linePossibility[a]):#POSSIBILITE EVIDENTE D'UNE LIGNE
                linePossibility[a]-=1
            if(m_manquant[b] == colPossibility[b]): #POSSIBILITE EVIDENTE D'UNE COLONNE
                colPossibility[b]-=1
            
            grid[a][b] = "-" #ON REMPLACE ET MET A JOUR
            m_manquant[b] -=1
            n_manquant[a] -=1
            missing-=1
            possibility.pop(i) #POSSIBILITE SUPPRIMEE

        else:
            i+=1

    if(not asChange): #PAS DE CHANGEMENT APRES TOUT UN PARCOURS DES POSSIBILITE ALORS PAS D'UNIQUE SOLUTION
        print("NON")
        break


else:
    print('\n'.join(''.join(grid[i]) for i in range(n)))


    