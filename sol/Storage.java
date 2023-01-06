package sol;

public class Storage {
    String outcomeValue;
    int counter;

    /**
     * Storage is a class with two fields that acts as our "dictionary class"
     * counter records the amount of instances as an int of the outcome
     * values, the other field outcomeValue is a string which is
     * the value to record.
     * */

    public Storage(String outcome, int counter) {
        this.counter = counter;
        this.outcomeValue = outcome;
    }

    /**
     * Returns this.outcomeValue, the value stored in the storage object.
     * @returns: this.outcomeValue, a string stored in the storage field
     */
    public String getOutcomeVal() {
        return this.outcomeValue;
    }

    /**
     * Increments the counter field in the storage object.
     */
    public void addToCounter() {
        this.counter++;
    }

    /**
     * Returns the counter, the int this.counter is storing in the
     * storage object.
     * @returns: this.counter, a int stored in the counter field
     */
    public int getCount() {
        return this.counter;
    }
}
