import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Vending {
    
    ArrayList<String> directory;
    ArrayList<Queue<Item>> slots;
    ArrayList<Item> types;

    public Vending(ArrayList<String> data) {
        this.directory = data;
        this.slots = new ArrayList<Queue<Item>>();
        loadItems(data);
        this.types = new ArrayList<Item>();
        for (int i = 0; i < data.size(); i++) {
            types.add(slots.get(i).element());
        }
    }

    private Queue<Item> parseData(ArrayList<String> parts) {
        Queue<Item> queue = new LinkedList<Item>();
        if (parts.get(0).equals("Drink")) {
            for (int j = 0; j < Integer.parseInt(parts.get(5)); j++) {
                queue.add(new Drink(parts.get(1), Float.parseFloat(parts.get(2)), parts.get(0), Float.parseFloat(parts.get(3)), parts.get(4)));
            }
        }
        else if (parts.get(0).equals("Snack")) {
            for (int j = 0; j < Integer.parseInt(parts.get(5)); j++) {
                queue.add(new Snack(parts.get(1), Float.parseFloat(parts.get(2)), parts.get(0), Float.parseFloat(parts.get(3)), Boolean.parseBoolean(parts.get(4))));
            }
        }
        return queue;
    }

    public void loadItems(ArrayList<String> data) {
        if (slots.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> parts = new ArrayList<>(Arrays.asList(data.get(i).split(", ")));
                slots.add(parseData(parts));
            }
        }
        else {
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> parts = new ArrayList<>(Arrays.asList(data.get(i).split(", ")));
                ArrayList<Integer> emp = new ArrayList<Integer>();
                for (int j = 0; j < slots.size(); j++) {
                    if (parts.get(1).equals(types.get(j).name) && slots.get(j).isEmpty()) {
                        emp.add(j);
                    }
                }
                if (emp.isEmpty()) {
                    for (int j = 0; j < slots.size(); j++) {
                        if (parts.get(0).equals("Drink") && parts.get(1).equals(types.get(i).name)) {
                            for (int k = 0; k < Integer.parseInt(parts.get(5)); k++) {
                                slots.get(j).add(new Drink(parts.get(1), Float.parseFloat(parts.get(2)), parts.get(0), Float.parseFloat(parts.get(3)), parts.get(4)));
                            }
                            j = slots.size();
                        }
                        else if (parts.get(0).equals("Snack") && parts.get(1).equals(types.get(i).name)) {
                            for (int k = 0; k < Integer.parseInt(parts.get(5)); k++) {
                                slots.get(j).add(new Snack(parts.get(1), Float.parseFloat(parts.get(2)), parts.get(0), Float.parseFloat(parts.get(3)), Boolean.parseBoolean(parts.get(4))));
                            }
                            j = slots.size();
                        }
                    }
                }
                else {
                    int least = 0;
                    if (parts.get(0).equals("Drink")) {
                        for (int k = 0; k < Integer.parseInt(parts.get(5)); k++) {
                            slots.get(emp.get(least)).add(new Drink(parts.get(1), Float.parseFloat(parts.get(2)), parts.get(0), Float.parseFloat(parts.get(3)), parts.get(4)));
                            if (least == emp.size()-1) {
                                least = -1;
                            }
                            least++;
                        }
                    }
                    else if (parts.get(0).equals("Snack")) {
                        for (int k = 0; k < Integer.parseInt(parts.get(5)); k++) {
                            slots.get(emp.get(least)).add(new Snack(parts.get(1), Float.parseFloat(parts.get(2)), parts.get(0), Float.parseFloat(parts.get(3)), Boolean.parseBoolean(parts.get(4))));
                            if (least == emp.size()-1) {
                                least = -1;
                            }
                            least++;
                        }
                    }
                }
            }
        }
    }

    public Item unloadItems(int index) {
        if (slots.get(index).isEmpty() || slots.get(index).size() == 1) {
            int most = -1;
            for (int i = 0; i < slots.size(); i++) {
                if (slots.get(i).isEmpty()) {}
                else if (slots.get(i).element().name.equals(types.get(index).name)) {
                    if (most == -1) {
                        most = i;
                    }
                    else if (slots.get(i).size() > slots.get(most).size()) {
                        most = i;
                    }
                }
            }
            if (most == -1) {
                return null;
            }
            return slots.get(most).remove();
        }
        else {
            return slots.get(index).remove();
        }
    }

    public ArrayList<Item> unloadItems(ArrayList<Integer> index) {
        ArrayList<Item> result = new ArrayList<Item>();
        for (int i = 0; i < index.size(); i++) {
            result.add(unloadItems(index.get(i)));
        }
        return result;
    }

    public ArrayList<Integer> findProduct(String product) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < directory.size(); i++) {
            if (directory.get(i).equals(product)) {
                result.add(i);
            }
        }
        return result;
    }

    public void displayItems() {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isEmpty()) {
                System.out.println(types.get(i).toString()+"0");
            }
            else {
                System.out.println(types.get(i).toString()+slots.get(i).size());
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i).isEmpty()) {
                result = result + (types.get(i).toString()+"0"+'\n');
            }
            else {
                result = result + (types.get(i).toString()+slots.get(i).size()+'\n');
            }
        }
        return result;
    }

    @Override
    public void finalize() {
        displayItems();
    }
}
