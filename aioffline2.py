from collections import defaultdict
from itertools import combinations
import operator
import numpy #eida barbar chole jay. chole gele pip install kora lagbe eikhner package e jaya
class Graph:
    def __init__(self,v):
        self.graph=defaultdict(list)
        self.v=v
        self.l=0
        self.result=[]
        self.slot1=[]
        self.slot2=[]
        self.slot3=[]
        self.penaltyrandom=defaultdict(list)
        self.penaltylargest=defaultdict(list)
        self.penaltyenrollment=defaultdict(list)
        self.edge=dict()
        self.maxedge=[]
        self.enrollment=dict()
        self.enrollmentlist=[]
    def search(self,l,v):
        for i in l:
            if(i==v):
                return True
        return False
    def addEdge(self,u,v):
        if (self.search((self.graph[u]),v)==False):
            self.graph[u].append(v)
        if (self.search((self.graph[v]),u)== False):
            self.graph[v].append(u)

       # self.graph[u].append(v)
       # self.graph[v].append(u)
    def edgecount(self):
        #maxedge=[]
        for k in self.graph.keys():
           # print("Course No {} Conflicted with {} courses".format(k,len(self.graph.get(k))))
            self.edge[k]=len(self.graph.get(k))
            #self.maxedge.append(len(self.graph.get(k)))
        sort_edge=sorted(self.edge.items(),key=lambda  x:x[1],reverse=True)
        for i in sort_edge:
            self.maxedge.append(i[0])
        #print(self.maxedge)
        #print(self.maxedge)
            #sum=sum+len(self.graph.get(k))
        #print("Number of edges {}".format(sum/2))
        #print("Course {} has maximum edges ".format(max(maxedge.items(),key=operator.itemgetter((1)))[0]))
    def courses(self):
        for k,v in self.graph.items():
            print(k,v)
    def greedycolouringrandom(self):
        #result=[]
        for i in range(1,self.v+2):
            self.result.append(-1)
        self.result[1]=1
        available=[]
        for i in range(1,self.v+2):
            available.append(True)
        for u in range(2,self.v+1):
            for i in self.graph[u]:
                if (self.result[i] != -1):
                    p=self.result[i]
                    available[p]=False
            for cr in range(1,self.v+2):
                if(available[cr]==True):
                    break
            self.result[u]=cr
            for i in range(1,self.v+1):
                available[i]=True
        #writer1=open("random.txt","w")
        #for u in range(1,self.v+1):
            #print("Course {} ----> Slot {}".format(u,self.result[u]))
            #writer1.write("Course {} ----> Slot {} ".format(u,self.result[u]))
            #writer1.write("\n")
        #print("Total Slots {}".format(max(self.result)))
    def greedycolouringlargest(self):
        for i in range(1,self.v+2):
            self.result.append(-1)
        self.result[self.maxedge[0]]=1
        available=[]
        for i in range(1,self.v+2):
            available.append(True)
        for u in self.maxedge:
            if(u==self.maxedge[0]):
                continue
            for i in self.graph[u]:
                if (self.result[i] != -1):
                    p=self.result[i]
                    available[p]=False
            for cr in range(1,self.v+2):
                if(available[cr]==True):
                    break
            self.result[u]=cr
            for i in range(1,self.v+1):
                available[i]=True
        #writer1=open("largest.txt","w")
        #writer2 = open("largestslot.txt", "w")
        #for u in range(1,self.v+1):
            #print("Course {} ----> Slot {}".format(u,self.result[u]))
            #writer1.write("Course {} ----> Slot {}".format(u,self.result[u]))
            #writer1.write("\n")
        f = open("student_taken_courses.txt")
        #slots = []
        for line in f:
            self.l = self.l + 1
            x = line.split()
            for i in range(len(x)):
                x[i] = int(x[i])
            for i in x:
                self.slot3.append(self.result[i])
            flag = len(set(self.slot3)) == len(self.slot3)
            if (flag == 0):
                print("INVALID")
            #print("Roll {} Slots : {} ".format(l,sorted(self.slot2)))
            for i in sorted(self.slot3):
                self.penaltylargest[self.l].append(i)
            #for k, v in self.penaltylargest.items():
                #print("Roll {} Slot : {}".format(k, v))
            #writer2.write("Roll {} Slots : {} ".format(l,sorted(self.slot2)))
            #writer2.write("\n")

            self.slot3.clear()
        #print("Total slots ", max(self.result))
        return max(self.result)
        #writer1.write("Total slots {}".format(max(self.result)))
    def largeenrollment(self):
        file=open("course_enrollment.txt")
        for line in file:
            x=line.split()
            #print(x)
            self.enrollment[int(x[0])]=int(x[1])
        #for k,v in self.enrollment.items():
            #print("{} {}".format(k,v))
        sort_enrollment = sorted(self.enrollment.items(), key=lambda x: x[1], reverse=True)
        for i in sort_enrollment:
            self.enrollmentlist.append(i[0])
    def greedycolouringlargeenrollment(self):
        for i in range(1,self.v+2):
            self.result.append(-1)
        self.result[self.enrollmentlist[0]]=1
        available=[]
        for i in range(1,self.v+2):
            available.append(True)
        for u in self.enrollmentlist:
            if(u==self.enrollmentlist[0]):
                continue
            for i in self.graph[u]:
                if (self.result[i] != -1):
                    p=self.result[i]
                    available[p]=False
            for cr in range(1,self.v+2):
                if(available[cr]==True):
                    break
            self.result[u]=cr
            for i in range(1,self.v+1):
                available[i]=True
        #writer1=open("largeenroll.txt","w")
        #writer2 = open("largeenrollstslot.txt", "w")
        #for u in range(1,self.v+1):
            #print("Course {} ----> Slot {}".format(u,self.result[u]))
            #writer1.write("Course {} ----> Slot {}".format(u,self.result[u]))
            #writer1.write("\n")
        f = open("student_taken_courses.txt")
        #slots = []
        for line in f:
            self.l = self.l + 1
            x = line.split()
            for i in range(len(x)):
                x[i] = int(x[i])
            for i in x:
                self.slot2.append(self.result[i])
            flag = len(set(self.slot2)) == len(self.slot2)
            if (flag == 0):
                print("INVALID")
            #print("Roll {} Slots : {} ".format(l,sorted(self.slot2)))
            for i in sorted(self.slot2):
                self.penaltyenrollment[self.l].append(i)
            #for k, v in self.penaltylargest.items():
                #print("Roll {} Slot : {}".format(k, v))
            #writer2.write("Roll {} Slots : {} ".format(l,sorted(self.slot2)))
            #writer2.write("\n")

            self.slot2.clear()
        #print("Total slots ", max(self.result))
        return max(self.result)
        #writer1.write("Total slots {}".format(max(self.result)))
    def routine(self):
        f=open("student_taken_courses.txt")
        #slots=[]
        for line in f:
            self.l=self.l+1
            x=line.split()
            for i in range(len(x)):
                x[i]=int(x[i])
            for i in x:
                self.slot1.append(self.result[i])
           #print("Roll {} Slots : {} ".format(l,sorted(slots)))
            #print("Roll {} Slots : {} ".format(l,sorted(self.slot1)))
            flag = len(set(self.slot1)) == len(self.slot1)
            if(flag==0):
                print("INVALID")
            for i in sorted(self.slot1):
                self.penaltyrandom[self.l].append(i)
            #for k,v in self.penaltyrandom.items():
                #print("Roll {} Slot : {}".format(k,v))
            #writer1.write("Roll {} Slots : {} ".format(l,sorted(self.slot1)))
            #writer1.write("\n")

            self.slot1.clear()
        #print("Total slots ",max(self.result))
        return max(self.result)
    def penaltyran(self):
        penrad=[]
        a=0
        b=0
        penalty=0
        for k in self.penaltyrandom.keys():
            penin=0
            penrad.clear()
            for item in self.penaltyrandom.get(k):
                penrad.append(item)
            for i in range(len(penrad)-1):
                a=penrad[i]
                b=penrad[i+1]
                r=abs(a-b)
                if(r==1):
                    penin=penin+16
                elif(r==2):
                    penin=penin+8
                elif(r==3):
                    penin=penin+4
                elif(r==4):
                    penin=penin+2
                elif(r==5):
                    penin=penin+1
                elif(r>=6):
                    penin=penin+0
            penalty=penalty+penin
        penalty=penalty/self.l
        return penalty
        #print("Avg Penalty for random {:.2f}".format(penalty))
    def penaltylarge(self):
        penrad=[]
        a=0
        b=0
        penalty=0
        for k in self.penaltylargest.keys():
            penin=0
            penrad.clear()
            for item in self.penaltylargest.get(k):
                penrad.append(item)
            for i in range(len(penrad)-1):
                a=penrad[i]
                b=penrad[i+1]
                r=abs(a-b)
                if(r==1):
                    penin=penin+16
                elif(r==2):
                    penin=penin+8
                elif(r==3):
                    penin=penin+4
                elif(r==4):
                    penin=penin+2
                elif(r==5):
                    penin=penin+1
                elif(r>=6):
                    penin=penin+0
            penalty=penalty+penin
        penalty=penalty/self.l
        return penalty
        #print("Avg Penalty for largest {:.2f}".format(penalty))
    def penaltylargeenroll(self):
        penrad=[]
        a=0
        b=0
        penalty=0
        for k in self.penaltyenrollment.keys():
            penin=0
            penrad.clear()
            for item in self.penaltyenrollment.get(k):
                penrad.append(item)
            for i in range(len(penrad)-1):
                a=penrad[i]
                b=penrad[i+1]
                r=abs(a-b)
                if(r==1):
                    penin=penin+16
                elif(r==2):
                    penin=penin+8
                elif(r==3):
                    penin=penin+4
                elif(r==4):
                    penin=penin+2
                elif(r==5):
                    penin=penin+1
                elif(r>=6):
                    penin=penin+0
            penalty=penalty+penin
        penalty=penalty/self.l
        return penalty
        #print("Avg Penalty for largest Enrollment {:.2f}".format(penalty))
    def student(self):
        return self.l
    def clear(self):
        self.result.clear()
        self.slot1.clear()
        self.slot2.clear()
        self.penaltyrandom.clear()
        self.penaltylargest.clear()
        self.penaltyenrollment.clear()
        self.edge.clear()
        self.maxedge.clear()
        self.enrollment.clear()
        self.enrollmentlist.clear()
        self.l=0
course=[]
f=open("student_taken_courses.txt")
file=open("course_enrollment.txt")
write=open("CAR91.txt","w")
for line in file:
    x=line.split()
    course.append(int(x[0]))
g1=Graph(max(course))
for line in f:
    x=line.split()
    for i in range(len(x)):
        x[i]=int(x[i])
    comb=combinations(x,2)
    for i in comb:
        p=i[0]
        q=i[1]
        g1.addEdge(p,q)
g1.greedycolouringrandom()
slot = g1.routine()
g1.edgecount()
penalty = g1.penaltyran()
x=g1.student()
print("Total student {}".format(x))
write.write("Total student {}".format(x))
write.write('\n')
print("Random Heuristic : Total Slots {} and Avg Penalty {:.2f}".format(slot, penalty))
write.write("Random Heuristic : Total Slots {} and Avg Penalty {:.2f}".format(slot, penalty))
write.write('\n')
g1.clear()

g1.edgecount()
slot = g1.greedycolouringlargest()
# g1.routine()
penalty = g1.penaltylarge()
print("Largest Edge : Total Slots {} and Avg Penalty {:.2f}".format(slot, penalty))
write.write("Largest Edge : Total Slots {} and Avg Penalty {:.2f}".format(slot, penalty))
write.write('\n')
g1.clear()


g1.largeenrollment()
slot = g1.greedycolouringlargeenrollment()
penalty = g1.penaltylargeenroll()
print("Largest Enrollment : Total Slots {} and Avg Penalty {:.2f}".format(slot, penalty))
write.write("Largest Enrollment : Total Slots {} and Avg Penalty {:.2f}".format(slot, penalty))
write.write('\n')
g1.clear()



