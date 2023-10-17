package com.skycat.wbshopaddons.blocks;

import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public class DonaterBlock extends SimplePolymerBlock {
    public DonaterBlock(Settings settings, Block polymerBlock) {
        super(settings, polymerBlock);
    }
}
