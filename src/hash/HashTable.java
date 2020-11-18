package hash;

public class HashTable<T> {

    private int size;
    private TableEntry[] table;

    public HashTable(int size) {
        this.size = size;
        table = new TableEntry[size];
    }

    public boolean put(int key, T value) {
        int idx = findEntryIndex(key);
        if (idx == -1) { return false; }
        table[idx] = new TableEntry(key, value);
        return true;
    }

    public T get(int key) {
        int idx = findEntryIndex(key);
        if (idx == -1 || table[idx] == null) { return null; }
        return (T) table[idx].getValue();
    }

    private int findEntryIndex(int key) {
        int hash = key % size;
        while (!(table[hash] == null || table[hash].getKey() == key)) {
            hash = (hash + 1) % size;
            if (hash == key % size) { return -1; }
        }
        return hash;
    }
}
