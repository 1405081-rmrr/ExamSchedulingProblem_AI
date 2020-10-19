import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.util.*;

import static java.util.Collections.*;

 class Graph {
    public int v;
    public int l;
    public LinkedList<Integer> graph[];
    public int[] result;
    ArrayList<Integer> slot1;
    ArrayList<Integer> slot2;
    ArrayList<Integer> slot3;
    LinkedList<Integer> penaltyran[];
    LinkedList<Integer> penaltylargest[];
    LinkedList<Integer> penaltyenroll[];
    LinkedList<Integer> edge[];
    ArrayList<Integer> maxedge;
    LinkedList<Integer> enrollment[];
    ArrayList<Integer> enrollmentlist;
    HashMap<Integer,Integer>edgecount;
    ArrayList<Integer>edges;
    int []index;
     HashMap<Integer,Integer> course;


    public Graph() {
        this.v = v;
        this.l=l;
        this.slot1=new ArrayList<>();
        //this.result=new int[this.v+1];

    }

    public void edge()
    {
        this.edgecount=new HashMap<>();
        for(int i=1;i<=this.v;i++)
        {
           // System.out.print(graph[i].size()+" ");
            edgecount.put(i,graph[i].size());
        }
        for(int i=1;i<=edgecount.size();i++)
        {
            //System.out.print(edgecount.get(i)+" ");
        }
        System.out.println();
        index=new int[this.v];
        Map<Integer, Integer> hm1 = sortHashMapByValues(edgecount);
        int i=0;
        for (Map.Entry<Integer, Integer> en : hm1.entrySet()) {
            //System.out.println("Key = " + en.getKey() +
                    //", Value = " + en.getValue());
            index[i]=en.getKey();
            i++;

        }


    }
     public LinkedHashMap<Integer, Integer> sortHashMapByValues(
             HashMap<Integer, Integer> passedMap) {
         List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
         List<Integer> mapValues = new ArrayList<>(passedMap.values());
         Collections.sort(mapValues);
         Collections.sort(mapKeys);

         LinkedHashMap<Integer, Integer> sortedMap =
                 new LinkedHashMap<>();

         Iterator<Integer> valueIt = mapValues.iterator();
         while (valueIt.hasNext()) {
             Integer val = valueIt.next();
             Iterator<Integer> keyIt = mapKeys.iterator();

             while (keyIt.hasNext()) {
                 Integer key = keyIt.next();
                 Integer comp1 = passedMap.get(key);
                 Integer comp2 = val;

                 if (comp1.equals(comp2)) {
                     keyIt.remove();
                     sortedMap.put(key, val);
                     break;
                 }
             }
         }
         return sortedMap;
     }
    public void initialize(int v)
    {
        this.v=v;
        graph=new LinkedList[this.v+1];
        for(int i=1;i<=this.v;i++)
        {
            graph[i]=new LinkedList<>();
        }
    }
    public void init(int l)
    {
        this.l=l;
        penaltyran=new LinkedList[this.l+1];
        for(int i=1;i<=this.l;i++)
        {
            penaltyran[i]=new LinkedList<>();
        }

    }



    public void addedge(int u, int v) {


            int f=1;
            for(int i=1;i<=graph[u].size();i++)
            {
             if(i==graph[u].size())
             {
                 f=1;
                 break;
             }
              if(graph[u].get(i)==v)
              {
                    f=0;
                    break;
              }
            }
            if(f==1) graph[u].add(v);
            int f1=1;
        for(int i=1;i<=graph[v].size();i++)
        {
            if(i==graph[v].size())
            {
                f1=1;
                break;
            }
            if(graph[v].get(i)==u)
            {
                f1=0;
                break;
            }
        }
        if(f1==1)graph[v].add(u);

    }

    public void greedycolouringrandom() {
        boolean[] available = new boolean[v+1];
        int p;
        int cr;
        this.result=new int[this.v+1];
        for (int i = 1; i <= this.v ; i++) {
            this.result[i] = -1;
            available[i] = true;
        }
        result[1]=1;

        for (int u = 2; u < v + 1; u++) {

            for (int i : graph[u]) {
                if (result[i] != -1) {
                    p = result[i];
                    available[p] = false;
                }
            }
            for (cr = 1; cr < v + 2; cr++) {
                if (available[cr] == true)
                    break;
            }
            result[u]=cr;
            for(int i=1;i<=this.v;i++)
            {
                available[i]=true;
            }
        }
        System.out.println("Total slots random heuristic"+" "+Arrays.stream(result).max().getAsInt());





    }
     public void greedylargest() {
         boolean[] available = new boolean[v+1];
         int p;
         int cr;
         this.result=new int[this.v+1];
         for (int i = 1; i <= this.v ; i++) {
             this.result[i] = -1;
             available[i] = true;
         }
         result[index.length]=1;

         for (int u =index.length-1; u >= 0; u--) {

             for (int i : graph[index[u]]) {
                 if (result[i] != -1) {
                     p = result[i];
                     available[p] = false;
                 }
             }
             for (cr = 1; cr < v + 2; cr++) {
                 if (available[cr] == true)
                     break;
             }
             result[index[u]]=cr;
             for(int i=1;i<=this.v;i++)
             {
                 available[i]=true;
             }
         }
         System.out.println("Total slots Largestedge Heuristic "+" "+Arrays.stream(result).max().getAsInt());


     }
     public void enroll() throws IOException, FileNotFoundException, NumberFormatException, NullPointerException
     {
         int p,courses,q;
          course = new HashMap<>();
         FileReader file = new FileReader("/home/roktim/Downloads/src/course_enrollment.txt");
         BufferedReader br = new BufferedReader(file);
         String s;
         String s1;
         int stu=0;
         while ((s = br.readLine()) != null) {
             //s.split(",");


             String[] bal = s.split(" ");
             List<Integer> x = new ArrayList<>();
             for (String number : bal) {
                 x.add(Integer.parseInt(number.trim()));
             }
             p = x.get(0);
             q=x.get(1);
             course.put(p,q);

         }
         index=new int[this.v];
         Map<Integer, Integer> hm1 = sortHashMapByValuesenroll(course);
         int i=0;
         for (Map.Entry<Integer, Integer> en : hm1.entrySet()) {
             //System.out.println("Key = " + en.getKey() +
             //", Value = " + en.getValue());
             index[i]=en.getKey();
             i++;

         }
         boolean[] available = new boolean[this.v+1];
         int z;
         int cr;
         this.result=new int[this.v+1];
         for (int k = 1; k <= this.v ; k++) {
             this.result[k] = -1;
             available[k] = true;
         }
         result[index.length]=1;

         for (int u =index.length-1; u >= 0; u--) {

             for (int k : graph[index[u]]) {
                 if (result[k] != -1) {
                     z = result[k];
                     available[z] = false;
                 }
             }
             for (cr = 1; cr < v + 2; cr++) {
                 if (available[cr] == true)
                     break;
             }
             result[index[u]]=cr;
             for(int k=1;k<=this.v;k++)
             {
                 available[k]=true;
             }
         }
         System.out.println("Total slots LargestEnroll Heuristic "+" "+Arrays.stream(result).max().getAsInt());


     }
     public LinkedHashMap<Integer, Integer> sortHashMapByValuesenroll(
             HashMap<Integer, Integer> passedMap) {
         List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
         List<Integer> mapValues = new ArrayList<>(passedMap.values());
         Collections.sort(mapValues);
         Collections.sort(mapKeys);

         LinkedHashMap<Integer, Integer> sortedMap =
                 new LinkedHashMap<>();

         Iterator<Integer> valueIt = mapValues.iterator();
         while (valueIt.hasNext()) {
             Integer val = valueIt.next();
             Iterator<Integer> keyIt = mapKeys.iterator();

             while (keyIt.hasNext()) {
                 Integer key = keyIt.next();
                 Integer comp1 = passedMap.get(key);
                 Integer comp2 = val;

                 if (comp1.equals(comp2)) {
                     keyIt.remove();
                     sortedMap.put(key, val);
                     break;
                 }
             }
         }
         return sortedMap;
     }

    public void routine() throws IOException {
        FileReader f = new FileReader("/home/roktim/Downloads/src/student_taken_courses.txt");
        BufferedReader br = new BufferedReader(f);
        int lines = 0;
        int l=0;
        String s;
        while ((s = br.readLine()) != null) {
            lines++;
            String[] p = s.split(" ");

            List<Integer> x = new ArrayList<>();

            for (String number : p) {
                x.add(Integer.parseInt(number.trim()));
            }
            ListIterator<Integer> lItr = x.listIterator();
            /*while (lItr.hasNext())
            {
                System.out.print(lItr.next());
            }*/


            for (int i : x) {
                slot1.add(result[i]);

            }

            Collections.sort(slot1);
            for(Integer p1:slot1)
            {
                //System.out.print(p1+" ");
                penaltyran[lines].add(p1);

            }



            slot1.clear();
            x.clear();


        }


        this.l=lines;

    }
     public void routineenroll() throws IOException {
         FileReader f = new FileReader("/home/roktim/Downloads/src/student_taken_courses.txt");
         BufferedReader br = new BufferedReader(f);
         int lines = 0;
         int l=0;
         String s;
         while ((s = br.readLine()) != null) {
             lines++;
             String[] p = s.split(" ");

             List<Integer> x = new ArrayList<>();

             for (String number : p) {
                 x.add(Integer.parseInt(number.trim()));
             }
             ListIterator<Integer> lItr = x.listIterator();
            /*while (lItr.hasNext())
            {
                System.out.print(lItr.next());
            }*/


             for (int i : x) {
                 slot1.add(result[i]);

             }

             Collections.sort(slot1);
             for(Integer p1:slot1)
             {
                 //System.out.print(p1+" ");
                 penaltyran[lines].add(p1);

             }



             slot1.clear();
             x.clear();


         }

        /*for(int k=1;k<=lines;k++)
        {
            for(Integer p1:penaltyran[k])
            {
                System.out.print(p1+" ");
            }
            System.out.println();
        }*/
         this.l=lines;
     }
     public void routinelarge() throws IOException {
         FileReader f = new FileReader("/home/roktim/Downloads/src/student_taken_courses.txt");
         BufferedReader br = new BufferedReader(f);
         int lines = 0;
         int l=0;
         String s;
         while ((s = br.readLine()) != null) {
             lines++;
             String[] p = s.split(" ");

             List<Integer> x = new ArrayList<>();

             for (String number : p) {
                 x.add(Integer.parseInt(number.trim()));
             }
             ListIterator<Integer> lItr = x.listIterator();
            /*while (lItr.hasNext())
            {
                System.out.print(lItr.next());
            }*/


             for (int i : x) {
                 slot1.add(result[i]);

             }

             Collections.sort(slot1);
             for(Integer p1:slot1)
             {
                 //System.out.print(p1+" ");
                 penaltyran[lines].add(p1);

             }



             slot1.clear();
             x.clear();


         }

        /*for(int k=1;k<=lines;k++)
        {
            for(Integer p1:penaltyran[k])
            {
                System.out.print(p1+" ");
            }
            System.out.println();
        }*/
         this.l=lines;
     }
     public double penaltyenroll() {
        /*int []penrad=new int[15];
        for(int i=0;i<15;i++)
        {
            penrad[i]=-100;
        }*/

         double a=0.0 ;
         double b=0.0 ;
         double penalty =0.0;
         double r ;
         for (int k = 1; k <= this.l; k++) {
             double penin = 0.0;
             ArrayList<Integer>penrad=new ArrayList<>();
             Collections.fill(penrad,0);
             for (int i : penaltyran[k]) {
                 penrad.add(i);
             }



             for (int i = 0; i < penrad.size() - 1; i++) {


                 a = penrad.get(i);
                 b = penrad.get(i+1);
                 r = Math.abs(a - b);
                 if (r == 1)
                     penin = penin + 16.0;
                 else if (r == 2)
                     penin = penin + 8.0;
                 else if (r == 3)
                     penin = penin + 4.0;
                 else if (r == 4)
                     penin = penin + 2.0;
                 else if (r == 5)
                     penin = penin + 1.0;
                 else if (r >= 6)
                     penin = penin + 0.0;

             }
             penalty = penalty + penin;

         }

         penalty = penalty / this.l;
         return penalty;

     }


     public double penaltyran() {
        /*int []penrad=new int[15];
        for(int i=0;i<15;i++)
        {
            penrad[i]=-100;
        }*/

        double a=0.0 ;
        double b=0.0 ;
        double penalty =0.0;
        double r ;
        for (int k = 1; k <= this.l; k++) {
            double penin = 0.0;
            ArrayList<Integer>penrad=new ArrayList<>();
            Collections.fill(penrad,0);
            for (int i : penaltyran[k]) {
                penrad.add(i);
            }



            for (int i = 0; i < penrad.size() - 1; i++) {


                a = penrad.get(i);
                b = penrad.get(i+1);
                r = Math.abs(a - b);
                if (r == 1)
                    penin = penin + 16.0;
                else if (r == 2)
                    penin = penin + 8.0;
                else if (r == 3)
                    penin = penin + 4.0;
                else if (r == 4)
                    penin = penin + 2.0;
                else if (r == 5)
                    penin = penin + 1.0;
                else if (r >= 6)
                    penin = penin + 0.0;

            }
            penalty = penalty + penin;

        }

        penalty = penalty / this.l;
        return penalty;

    }
     public double penaltylarge() {
        /*int []penrad=new int[15];
        for(int i=0;i<15;i++)
        {
            penrad[i]=-100;
        }*/

         double a=0.0 ;
         double b=0.0 ;
         double penalty =0.0;
         double r=0.0 ;
         for (int k = 1; k <= this.l; k++) {
             double penin = 0.0;
             ArrayList<Integer>penrad=new ArrayList<>();
             Collections.fill(penrad,0);
             for (int i : penaltyran[k]) {
                 penrad.add(i);
             }



             for (int i = 0; i < penrad.size() - 1; i++) {


                 a = penrad.get(i);
                 b = penrad.get(i+1);
                 r = Math.abs(a - b);
                 if (r == 1)
                     penin = penin + 16.0;
                 else if (r == 2)
                     penin = penin + 8.0;
                 else if (r == 3)
                     penin = penin + 4.0;
                 else if (r == 4)
                     penin = penin + 2.0;
                 else if (r == 5)
                     penin = penin + 1.0;
                 else if (r >= 6)
                     penin = penin + 0.0;

             }
             penalty = penalty + penin;

         }

         penalty = penalty / this.l;
         return penalty;

     }

    public int student() {
        return this.l;
    }

    public int course() {
        return v;
    }

    public int[] courseslot() {
        return result;
    }



}
public class Schedule {
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException, NullPointerException {

        ArrayList<Integer> course = new ArrayList<>();
        int p, courses;
        FileReader f = new FileReader("/home/roktim/Downloads/src/student_taken_courses.txt");
        FileReader file = new FileReader("/home/roktim/Downloads/src/course_enrollment.txt");
        BufferedReader br = new BufferedReader(file);
        BufferedReader bs = new BufferedReader(f);
        String s;
        String s1;
        int stu=0;
        while ((s = br.readLine()) != null) {
            //s.split(",");


            String[] bal = s.split(" ");
            List<Integer> x = new ArrayList<>();
            for (String number : bal) {
                x.add(Integer.parseInt(number.trim()));
            }
            p = x.get(0);
            course.add(p);

        }
        courses = Collections.max(course);
        Graph g1 = new Graph();
        g1.initialize(courses);
        while ((s1 = bs.readLine()) != null) {
            //s.split(",");
            stu++;

            String[] bal = s1.split(" ");
            List<Integer> x = new ArrayList<>();
            for (String number : bal) {
                x.add(Integer.parseInt(number.trim()));
            }

            for (int i : x) {
                for (int j : x) {
                    if (i == j) continue;
                    g1.addedge(i, j);
                    //System.out.println(i+" "+j);

                }

            }

        }
        //System.out.println(stu);
        g1.edge();
       g1.greedycolouringrandom();
        g1.init(stu);
        g1.routine();
       double penalty = g1.penaltyran();
        g1.init(stu);
        g1.greedylargest();
        g1.routinelarge();
        double penalty1=g1.penaltylarge();
        g1.init(stu);
        g1.enroll();
        g1.routineenroll();
        double pen=g1.penaltyenroll();
        int x = g1.student();
        int c = g1.course();
        System.out.println("Total Student " + x + " and total courses " + c);
        System.out.println("Penalty Largest Edge " + String.format("%.2f",penalty1));
        System.out.println("Penalty Random " + String.format("%.2f",penalty));
        System.out.println("Penalty Largest Enroll " + String.format("%.2f",pen));


    }
}

