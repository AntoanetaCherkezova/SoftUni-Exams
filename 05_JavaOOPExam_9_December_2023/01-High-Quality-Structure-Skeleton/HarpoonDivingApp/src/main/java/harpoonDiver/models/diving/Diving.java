package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;

public interface Diving {
    abstract

    void searching(DivingSite divingSite, Collection<Diver> divers);
}
