package harpoonDiver.models.diver;

import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.DIVER_NAME_NULL_OR_EMPTY;
import static harpoonDiver.common.ExceptionMessages.DIVER_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseDiver implements Diver{
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    protected BaseDiver(String name, double oxygen) {
        this.name = name;
        this.oxygen = oxygen;
        this.seaCatch = new BaseSeaCatch();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canDive() {
        return this.oxygen > 0;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return this.seaCatch;
    }

    @Override
    public void shoot() {
        this.oxygen -= 30;

        if (this.oxygen < 0) {
            this.oxygen = 0;
        }
    }

    @Override
    public String toString() {
        String seaCreature = String.join(FINAL_DIVER_CATCH_DELIMITER, this.seaCatch.getSeaCreatures());

        if (this.seaCatch.getSeaCreatures().isEmpty()) {
            seaCreature = "None";
        }

        return String.format(FINAL_DIVER_NAME, this.name) + "\n" +
                String.format(FINAL_DIVER_OXYGEN, this.oxygen) + "\n" +
                String.format(FINAL_DIVER_CATCH, seaCreature);
    }

}
