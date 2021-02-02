nb_test, mode, nb_col = input().split()

if(mode == 'C'):
    for test in range(int(nb_test)):
        sentence = input() #get the sentence

        List = []
        nb_col = int(nb_col)#build the nb_col dimension matrix
        for i in range(nb_col):
            List.append([])

        i = 0
        for c in sentence : #place the letter at the right place
            List[i].append(c)
            i = (i+1) % nb_col

        for i in range(nb_col): #build the crypted message
            for el in List[i]:
                print(el,end="")
                
        print()

elif(mode == 'D'):
    for test in range(int(nb_test)):
        sentence = input() #get the sentence

        size = []
        nb_col = int(nb_col) #Compute the size, first the quotient
        for i in range(nb_col): 
            size.append(len(sentence)//nb_col)

        rest = len(sentence)%nb_col #not forget to add the rest
        for i in range(rest):
            size[i] += 1

        List = []
        n = 0
        for i in range(nb_col): #Add each crypted sentence fragment
            List.append(sentence[n:n+size[i]])
            n += size[i]

        for i in range(max(size)): #Print letter in the right order
            for col in range(nb_col):
                if(i < size[col]):#if there is remaining letter
                    print(List[col][i],end='') #line by line

        print()


else:
    print("ERROR")

