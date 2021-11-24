import java.util.ArrayList;
import java.util.HashMap;

public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;

	public ERPriorityQueue(){

		//  use a dummy node so that indexing starts at 1, not 0

		patients = new ArrayList<Patient>();
		patients.add( new Patient("dummy", 0.0) );

		nameToIndex  = new HashMap<String,Integer>();
	}

	private int parent(int i){
		return i/2;
	}

	private int leftChild(int i){
	    return 2*i;
	}

	private int rightChild(int i){
	    return 2*i+1;
	}


   	private void swap(int i, int j) { //swap two elements, helper function
		Patient tmp = patients.get(i);
		patients.set(i, patients.get(j));
		patients.set(j, tmp);
	}

	public void upHeap(int i){
		while ((i > 1) && (patients.get(i).getPriority() < patients.get(i/2).getPriority())) {
			swap(i, i/2);
			i = i/2;
			//TODO: unknow specifications in pdf?

		}
	}

	public void downHeap(int i){
        // TODO: Implement your code here
	}

	public boolean contains(String name){
        // TODO: Implement your code here & remove return statement
        return false;
	}

	public double getPriority(String name){
        // TODO: Implement your code here & remove return statement
        return -1;
	}

	public double getMinPriority(){
        // TODO: Implement your code here & remove return statement
        return -1;
	}

	public String removeMin(){
        // TODO: Implement your code here & remove return statement
        return null;
	}

	public String peekMin(){
        // TODO: Implement your code here & remove return statement
        return null;
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then return false.
	 */

	public boolean  add(String name, double priority){
        // TODO: Implement your code here & remove return statement
        return false;
	}

	public boolean  add(String name){
        // TODO: Implement your code here
		return false;
	}

	public boolean remove(String name){
        // TODO: Implement your code here
        return false;
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */

	public boolean changePriority(String name, double priority){
        // TODO: Implement your code here & remove return statement
        return false;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
        return null;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
        return null;
	}



	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof  ERPriorityQueue.Patient)) return false;
			Patient otherPatient = (Patient) obj;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}

	}
}
