import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //load data from file
	    DataFile myData = new DataFile("./Directory/directory1.txt", "./Input/input.txt");
	    //initialize Vending machine with loaded data
	    ArrayList <String> myVending = myData.loadDirectory();
	    ArrayList <Integer> mySelections = myData.loadSampleInput();

	    Vending myMachine = new Vending(myVending);
	    // Test line to show items before removing initially
	     System.out.println("Items originally there:");
	     myMachine.displayItems(); //debug helper function, REALLY NEEDS toString()
	     System.out.println("______________________________");
	    //remove items
	     System.out.println("Items removed final count: ");
	    myMachine.unloadItems(mySelections);

	    //Final output to display after removing
	    //myMachine.displayItems(); //debug helper function, REALLY NEEDS toString()

		/***************************************/
		// Begin Extra Credit Test Cases

		try {
			FileWriter myWriter = new FileWriter("results.txt");
			DataFile dat = new DataFile("./Directory/directory2.txt", "./Input/input2.txt");
			DataFile dat2 = new DataFile("./Directory/directory3.txt", "./Input/input3.txt");
			Vending ven = new Vending(dat.loadDirectory());
			myWriter.write("Testing initial machine loading:\n");
			myWriter.write(ven.toString());
			myWriter.write("______________________________\n");
			ven.loadItems(dat2.loadDirectory());
			myWriter.write("Testing adding more items to existing machine:\n");
			myWriter.write(ven.toString());
			myWriter.write("______________________________\n");
			ven.unloadItems(dat.loadSampleInput());
			myWriter.write("Testing removing enough items to bring slots below 1:\n");
			myWriter.write(ven.toString());
			myWriter.write("______________________________\n");
			ven.unloadItems(dat2.loadSampleInput());
			myWriter.write("Testing emptying slots and trying to extract more:\n");
			myWriter.write(ven.toString());
			myWriter.write("______________________________\n");
			ven.loadItems(dat2.loadDirectory());
			myWriter.write("Testing adding more items to emptied slots:\n");
			myWriter.write(ven.toString());
			myWriter.write("______________________________\n");
			myWriter.close();
		  } catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		  }

		// End Extra Credit Test Cases
		/***************************************/

		//Finalize
		myMachine = null;
		System.gc();

	    /*****************/
	    // Above DisplayItems() call is fine, but the Vending machine's deconstructor 
	    // should call that function since it's the LAST operation. Notice it could be
	    // done with a helper function that USES the toString()
	    // We will NOT call DisplayItems() in testing 
	    /*****************/
	    
	}

}
