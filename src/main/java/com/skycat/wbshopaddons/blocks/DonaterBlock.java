package com.skycat.wbshopaddons.blocks;

import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class DonaterBlock extends SimplePolymerBlock implements BlockEntityProvider {
    public DonaterBlock(Settings settings, Block polymerBlock) {
        super(settings, polymerBlock);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DonaterBlockEntity(pos, state);
    }
}
