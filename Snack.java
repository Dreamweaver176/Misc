public class Snack extends Item {
    
    protected float weight;
    protected boolean containsNuts;

    public Snack(String name, float calories, String itemType, float weight, boolean containsNuts) {
        super(name, calories, itemType);
        this.weight = weight;
        this.containsNuts = containsNuts;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isContainsNuts() {
        return containsNuts;
    }

    public void setContainsNuts(boolean containsNuts) {
        this.containsNuts = containsNuts;
    }

    @Override
    public String toString() {
        // return "Snack{" +
        //         "weight=" + weight +
        //         ", containsNuts=" + containsNuts +
        //         "} " + super.toString();
        return name + ": (" + itemType + "): ";
    }
}
