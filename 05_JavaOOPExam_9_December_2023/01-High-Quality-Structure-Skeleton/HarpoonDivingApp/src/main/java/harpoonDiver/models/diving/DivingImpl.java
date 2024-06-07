package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;
import java.util.Iterator;

public class DivingImpl implements Diving{
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
        Iterator<String> iterator = divingSite.getSeaCreatures().iterator();

        for (Diver diver : divers) {

            while (iterator.hasNext()) {
                if (!diver.canDive()) {
                    break;
                }
                diver.shoot();

                String item = iterator.next();
                diver.getSeaCatch().getSeaCreatures().add(item);

                iterator.remove();
            }
        }
    }
}
