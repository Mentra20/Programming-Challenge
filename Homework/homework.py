from math import sqrt
from math import inf


compteur = 0
def computeDist(v1,v2):
    global compteur
    dist = 0
    for i in range(len(v1)):
        dist+=pow(v1[i]-v2[i],2)
    compteur+=1
    return sqrt(dist)

def dictionaryDist(vectorList):
    M = [[0 for j in range(len(vectorList))] for i in range(len(vectorList))]
    listMinDist = [inf for i in range(len(vectorList))]
    for i in range(len(vectorList)):
        for j in range(i+1,len(vectorList)):
            dist = computeDist(vectorList[i],vectorList[j])
            M[i][j] = dist
            M[j][i] = dist

            if(listMinDist[i]>dist):
                listMinDist[i] = dist
            if(listMinDist[j]>dist):
                listMinDist[j] = dist
    return M,listMinDist

def moveToStart(L,vectorListIdx,idx):
    if(idx==0):
        return
    tmp = L[idx]
    tmpIdx = vectorListIdx[idx]


    while(idx>0):
        L[idx] = L[idx-1]
        vectorListIdx[idx] = vectorListIdx[idx-1]
        idx-=1

    L[0] = tmp
    vectorListIdx[0] = tmpIdx


def findClosest(M,vectorList,vectorListIdx,listMinDist,vector):
    eliminate=[False for i in range(len(vectorList))]
    cn = vectorList[0]
    distcn = computeDist(cn,vector)
    if (distcn < listMinDist[vectorListIdx[0]]/2):
        return 0
    cnIndex = 0
    for i in range(1,len(vectorList)):
        if(eliminate[i]):
            continue
        cs = vectorList[i]
        distcs = computeDist(cs,vector)
        csIndex = i
        if(distcs<distcn or (vectorListIdx[csIndex]<vectorListIdx[cnIndex] and distcs==distcn)):
            if (distcs < listMinDist[vectorListIdx[csIndex]]/2):
                return i
            
            tmp=cs
            cs = cn
            cn = tmp

            tmpdist=distcs
            distcs=distcn
            distcn = tmpdist

            csIndex = cnIndex
            cnIndex=i
        
        cspluscn = distcs+distcn
        csmoinscn= distcs-distcn
        for j in range(i+1,len(vectorList)):
            if(eliminate[j]):
                continue
            cptocs = M[vectorListIdx[csIndex]][vectorListIdx[j]]
            if(cptocs>cspluscn or cptocs<csmoinscn or M[vectorListIdx[cnIndex]][vectorListIdx[j]]>2*distcn):
                eliminate[j] = True

    return cnIndex

'''
M =
0   d1  d2
d1  0   d3
d2  d3  0
'''
lenPicture,lenMatrix,nbMatrix = list(map(int, input().split()))

vectorList=[]
vectorListIdx=[]
for i in range(nbMatrix):
    currentVect=[]
    for j in range(lenMatrix):
        lineInt = list(map(int, input().split()))
        for elt in lineInt:
            currentVect.append(elt)

    vectorList.append(currentVect)
    vectorListIdx.append(i)

dic,listMinDist = dictionaryDist(vectorList)


nbMatrixLine = lenPicture//lenMatrix

for linePicture in range(nbMatrixLine):
    vectorPictureRead=[[] for i in range(nbMatrixLine)]

    for lineForOneMatrixPicture in range(lenMatrix):
        lineInt = list(map(int, input().split()))
        #repartition pour les vecteur
        for k in range(lenPicture):
            vectorPictureRead[k//lenMatrix].append(lineInt[k])

    # on a une liste de vecteur on fait les operation
    for currentVect in vectorPictureRead:
        result = findClosest(dic,vectorList,vectorListIdx,listMinDist,currentVect)
    
        print(vectorListIdx[result],end=" ")
        moveToStart(vectorList,vectorListIdx,result)

    print()

print()
print(compteur)