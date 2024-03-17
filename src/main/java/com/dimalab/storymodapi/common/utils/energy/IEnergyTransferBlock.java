package com.dimalab.storymodapi.common.utils.energy;

import net.minecraft.util.Direction;

public interface IEnergyTransferBlock extends IEnergyController {

    boolean canConnectEnergy(Direction side);
}