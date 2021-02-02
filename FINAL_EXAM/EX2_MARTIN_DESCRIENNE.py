import sys

def main():
    msg = sys.stdin.read()

    if(msg==""): #empty answer
        print("",end="")
        return

    if(len(msg) == 1): #trivial
        print(msg)
        return

    elif(len(msg) == 2): 
        if(msg[-1] != msg[-2]): #must be the same
            print(msg[:-1]) #or cut
            return

        print(msg)#no cut
        return

    elif(len(msg) > 2):
        msg_split = list(msg)

        for i in range(len(msg_split)-2):
            sub = msg_split[i:i+3]# pack of 3 bytes

            #Noise to correct
            if("".join(sub) == "010"):
                msg_split[i+1] = '0'
            elif("".join(sub) == "101"):
                msg_split[i+1] = '1'
            
        if(msg_split[-1] != msg_split[-2] and msg_split[-1] != msg_split[-3]): #Must cut the last
            msg_split = msg_split[:-1]

        for el in msg_split:
            print(el,end='')
        print()

if __name__=="__main__":
    main()