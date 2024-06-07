package harpoonDiver.core;

import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private Diving diving;

    private int divingCount;


    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
        this.diving = new DivingImpl();
        this.divingCount = 0;
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver = null;

        if (kind.equals("OpenWaterDiver")) {
            diver = new OpenWaterDiver(diverName);
        } else if (kind.equals("DeepWaterDiver")) {
            diver = new DeepWaterDiver(diverName);
        } else if (kind.equals("WreckDiver")) {
            diver = new WreckDiver(diverName);
        } else {
            throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }

        diverRepository.add(diver);
        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);

        List<String> items = Arrays.asList(seaCreatures);
        divingSite.getSeaCreatures().addAll(items);

        this.divingSiteRepository.add(divingSite);

        return String.format(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = this.diverRepository.byName(diverName);

        if (diver == null) {
            throw new IllegalArgumentException(String.format(DIVER_DOES_NOT_EXIST, diverName));
        }
        boolean removed = this.diverRepository.remove(diver);
        if (!removed) {
            throw new IllegalArgumentException(String.format(DIVER_DOES_NOT_EXIST, diverName));
        }
        return String.format(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
       /* DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        Collection<Diver> collection = this.diverRepository.getCollection();
        List<Diver> goingToMission = new ArrayList<>();
        List<Diver> excluded = new ArrayList<>();
        for (Diver diver : collection) {
            if (diver.getOxygen() <= 30) {
                excluded.add(diver);
            } else {
                goingToMission.add(diver);
            }
        }
        if (goingToMission.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }
        this.divingCount++;
        this.diving.searching(divingSite, goingToMission);

        return String.format(SITE_DIVING, siteName, excluded.size());*/

        List<Diver> divers = this.diverRepository.getCollection().stream()
                .filter(d -> d.getOxygen() > 30)
                .collect(Collectors.toList());
        if(divers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }
        DivingSite divingSite = this.divingSiteRepository.byName(siteName);
        Diving diving = new DivingImpl();
        this.diving.searching(divingSite, divers);
        long excluded = divers.stream().filter(d -> d.getOxygen() == 0).count();
        this.divingCount++;
        return String.format(SITE_DIVING, siteName, excluded);
    }

    @Override
    public String getStatistics() {
        String divers = this.diverRepository.getCollection()
                .stream()
                .map(Diver::toString)
                .collect(Collectors.joining("\n"));

        return String.format(FINAL_DIVING_SITES, this.divingCount) + "\n" +
                FINAL_DIVERS_STATISTICS + "\n" +
                divers;
    }
}
