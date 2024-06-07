package halloween;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int capacity;
    private List<Kid> data;

    public House(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    public void addKid(Kid kid) {
        if (data.size() < capacity) {
            data.add(kid);
        }
    }

    public boolean removeKid(String name) {
        return data.removeIf(k -> k.getName().equals(name));
    }

    public Kid getKid(String street) {
        for (Kid kid : data) {
            if (kid.getStreet().equals(street)) {
                return kid;
            }
        }
        return null;
    }

    public int getAllKids() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Children who visited a house for candy:").append(System.lineSeparator());
        for (Kid kid : data) {
            sb.append(kid.getName() + " from " + kid.getStreet() + " street").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
