public class main {
    public static void main(String[] args) {
        ERPriorityQueue testQueue = new ERPriorityQueue();
        testQueue.add("Jonas");
        System.out.println(testQueue.add("Jonas", 3)); //should output false - can't add the same name twice!!
        testQueue.add("Catherine");
        testQueue.add("Vanessa");
        System.out.println(testQueue.patients);
        System.out.println(testQueue.nameToIndex); //Vanessa=3, Catherine = 2, Jonas=1

        testQueue.removeUrgentPatients(1000);
        //the queue should not change, because by default in add method, all patients have priority of positive infinity.
        System.out.println(testQueue.patients);
        System.out.println(testQueue.nameToIndex);

        testQueue.removeNonUrgentPatients(1000);
        //the queue should be reduced to only the dummy patient at index zero, since all patients are less urgent than 1000.
        System.out.println(testQueue.patients);
        System.out.println(testQueue.nameToIndex); //should be empty

        ERPriorityQueue testQueue2 = new ERPriorityQueue();

        testQueue2.add("Jill",1);
        testQueue2.add("Jack",1);
        testQueue2.add("Jeff",1);
        System.out.println(testQueue2.patients);
        System.out.println(testQueue2.nameToIndex);  //Jeff=3, Jill=1, Jack=2

        testQueue2.remove("Jill");
        //Jeff should be the new root (element at index 1) since Jeff is the last element of the ArrayList and no downHeap is needed
        //since Jeff's priority is <= Jack's
        System.out.println(testQueue2.patients);
        System.out.println(testQueue2.nameToIndex); //Jeff=1, Jack=2

        testQueue2.removeNonUrgentPatients(1000);
        System.out.println(testQueue2.patients);
        System.out.println(testQueue2.nameToIndex); //Jeff=1, Jack=2
        //the queue should not change (just Jeff and Jack), since all patients have priorities more urgent than 1000

        testQueue2.removeUrgentPatients(1000);
        System.out.println(testQueue2.patients);
        System.out.println(testQueue2.nameToIndex); //empty
        //queue should now be empty - just dummy patient

        ERPriorityQueue testQueue3 = new ERPriorityQueue();
        testQueue3.add("Adam",1);
        testQueue3.add("Joy",2);
        testQueue3.add("Katy",2);
        testQueue3.add("Perry", 4);
        System.out.println(testQueue3.patients);
        System.out.println(testQueue3.nameToIndex); //Adam=1, Perry=4, Joy=2, Katy=3

        testQueue3.removeMin();
        //In downHeap operation, Perry gets swapped with the LEFT CHILD as per assignment specifications
        //(if two children have an equal priority that is less than the priority of the element being downheaped, swap the
        //element with the left child)
        //so we should end up with Joy, Perry, Katy in the patients list
        System.out.println(testQueue3.patients);
        System.out.println(testQueue3.nameToIndex); //Perry = 2, Joy=1, Katy=3
    }
}
