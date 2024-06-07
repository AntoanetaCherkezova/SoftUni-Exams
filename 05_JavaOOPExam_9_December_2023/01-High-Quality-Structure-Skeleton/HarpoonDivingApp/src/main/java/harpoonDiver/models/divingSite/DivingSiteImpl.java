package harpoonDiver.models.divingSite;

import harpoonDiver.models.diving.Diving;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static harpoonDiver.common.ExceptionMessages.SITE_NAME_NULL_OR_EMPTY;

public class DivingSiteImpl implements DivingSite {
    private String name;
    private List<String> seaCreatures;

    public DivingSiteImpl(String name) {
        this.name = name;
        this.seaCreatures = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SITE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getSeaCreatures() {
        return this.seaCreatures;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
