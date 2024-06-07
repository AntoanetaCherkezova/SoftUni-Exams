package harpoonDiver.repositories;

import harpoonDiver.models.diver.Diver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DiverRepository implements Repository<Diver>{

    private List<Diver> divers;

    public DiverRepository() {
        this.divers = new ArrayList<>();
    }

    @Override
    public Collection<Diver> getCollection() {
       return Collections.unmodifiableCollection(this.divers);
    }

    @Override
    public void add(Diver entity) {
        this.divers.add(entity);
    }

    @Override
    public boolean remove(Diver entity) {
        return this.divers.remove(entity);
    }

    @Override
    public Diver byName(String name) {
        return this.divers.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
