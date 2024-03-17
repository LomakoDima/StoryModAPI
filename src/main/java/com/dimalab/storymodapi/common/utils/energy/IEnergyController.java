package com.dimalab.storymodapi.common.utils.energy;

public interface IEnergyController {
    boolean canReceiveEnergy(EnergyType type);

    boolean canExtractEnergy(EnergyType type);

    int receiveEnergy(EnergyType type, int maxReceive, boolean simulate);

    int extractEnergy(EnergyType type, int maxExtract, boolean simulate);
}
