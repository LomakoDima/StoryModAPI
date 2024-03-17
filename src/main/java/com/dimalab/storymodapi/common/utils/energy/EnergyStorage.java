package com.dimalab.storymodapi.common.utils.energy;

public class EnergyStorage {
    private int energy;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    private final EnergyType energyType;

    public EnergyStorage(int capacity, int maxReceive, int maxExtract, EnergyType energyType) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energyType = energyType;
    }

    public int getEnergyStored() {
        return energy;
    }

    public int getMaxEnergyStored() {
        return capacity;
    }

    public int receiveEnergy(int maxReceive, boolean simulate, EnergyType type) {
        if (type != energyType) {
            return 0;
        }

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate) {
            energy += energyReceived;
        }

        return energyReceived;
    }

    public int extractEnergy(int maxExtract, boolean simulate, EnergyType type) {
        if (type != energyType) {
            return 0;
        }

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
        }

        return energyExtracted;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }
}
