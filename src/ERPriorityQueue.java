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
		nameToIndex.remove(patients.get(i).getName());
		nameToIndex.remove(patients.get(j).getName());
		nameToIndex.put(patients.get(j).getName(), i);
		nameToIndex.put(patients.get(i).getName(), j);
		patients.set(i, patients.get(j));
		patients.set(j, tmp);
	}

	private boolean isEmpty() {
		if (patients.size() == 0) {
			return true;
		}
		else if (patients.size() == 1 && patients.get(0).getPriority() == 0 ){
			return true;
		}
		else return false;
	}

	private boolean isLeaf(int i) {
		if (leftChild(i) >= patients.size() && rightChild(i) >= patients.size()) {
			return true;
		}
		return false;
	}

	public void upHeap(int i){
		while ((i > 1) && (patients.get(i).getPriority() < patients.get(parent(i)).getPriority())) {
			swap(i, parent(i));
			i = parent(i);
			//TODO: unknow specifications in pdf?

		}
	}

	public void downHeap(int i){
		int size = patients.size()-1;
		int tmp = 0;
        while (leftChild(i) <= size) {
			tmp = leftChild(i);
			if (tmp < size) { //don't understand this if statement
				if (patients.get(rightChild(i)).getPriority() < patients.get(tmp).getPriority()) {
					tmp ++;
				}
			}
			if ( patients.get(tmp).getPriority() < patients.get(i).getPriority()) {
				swap(i, tmp);
				i = tmp;
			}
			else return;
		}
	}

	public boolean contains(String name){
		if (nameToIndex.containsKey(name)) {
			return true;
		} return false;
	}

	public double getPriority(String name){
		if (isEmpty()) {
			return -1;
		}
		else {
			for (Patient e : patients) {
				if (e.getName().equals(name)) {
					return e.getPriority();
				}
			}
			return -1;
		}
	}

	public double getMinPriority(){
		if (isEmpty()) {
			return -1;
		}
		else {
			return patients.get(1).getPriority();
		}
	}

	public String removeMin(){
		if (isEmpty()) {
			return null;
		}
//		else if (patients.size()==2) {
//			Patient tmp = patients.get(1);
//			nameToIndex.remove(patients.get(1).getName());
//			patients.remove(patients.get(1));
//			return tmp.getName();
//		}
//		else {
			Patient tmp = patients.get(1);
			removeByIndex(1);

			return tmp.getName();
//		}
	}

	public String peekMin(){
		if (isEmpty()) {
			return null;
		}
		else {
			return patients.get(1).getName();
		}
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then return false.
	 */

	public boolean add(String name, double priority){
		Patient newPatient = new Patient(name, priority);
		if (contains(name)) {
			return false;
		} else {
			nameToIndex.put(name, patients.size());
			patients.add(newPatient);
			upHeap(patients.size() - 1);
		}
		return true;
	}

	public boolean add(String name){
		Patient newPatient = new Patient(name, Double.POSITIVE_INFINITY);
		if (contains(name)) {
			return false;
		} else {
			nameToIndex.put(name, patients.size());
			patients.add(newPatient);
			upHeap(patients.size() - 1);

		}
		return true;
	}

	public boolean remove(String name){
		if (isEmpty()) {
			return false;
		} else {
			int tmp = 0;
			if (contains(name)) {
				tmp = nameToIndex.get(name);
				removeByIndex(tmp);
				return true;
			}
		}
        return false;
	}
	public void removeByIndex(int i) {
		if (isEmpty()) {
			return;
		}
//		if (patients.size() == 2) {
//
//			Patient tmp = patients.get(1);
//
//			nameToIndex.remove(patients.get(1).getName());
//			patients.remove(patients.get(1));
//
//		} else
			if (i == patients.size()-1) {

			nameToIndex.remove(patients.get(patients.size()-1).getName());
			patients.remove(patients.get(patients.size()-1));

		}else {
			Patient tmp2 = patients.get(patients.size() - 1);

			patients.remove(patients.get(patients.size() - 1));
			nameToIndex.remove(patients.get(i).getName());

			nameToIndex.put(tmp2.getName(), i);
			patients.set(i, tmp2);

			downHeap(i);
		}
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */

	public boolean changePriority(String name, double priority){
        if (contains(name)) {
			int tmp = nameToIndex.get(name);
			patients.get(tmp).setPriority(priority);
			upHeap(tmp);
			downHeap(tmp);
			return true;
		}
        return false;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
        ArrayList<Patient> urgentPatients = new ArrayList<Patient>();
		while (getMinPriority() <= threshold) {
			urgentPatients.add(patients.get(1));
			removeMin();
		}
        return urgentPatients;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
		ArrayList<Patient> nonUrgentPatients = new ArrayList<Patient>();
		for (int i = 1; i < patients.size(); i++) {
			if (patients.get(i).getPriority() >= threshold) {
				nonUrgentPatients.add(patients.get(i));
				removeByIndex(i);
				i --;
			}
		}
		return nonUrgentPatients;
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
