import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataFile {
    
    protected String directory, input;

    public DataFile(String directory, String input) {
        this.directory = directory;
        this.input = input;
    }

    public ArrayList<String> loadDirectory() {
        ArrayList<String> list = new ArrayList<String>();
        try {
			BufferedReader reader = new BufferedReader(new FileReader(directory));
			String line = reader.readLine();
			while (line != null) {
                list.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
    }

    public ArrayList<Integer> loadSampleInput() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String line = reader.readLine();
			while (line != null) {
                list.add(Integer.parseInt(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return list;
    }
}
