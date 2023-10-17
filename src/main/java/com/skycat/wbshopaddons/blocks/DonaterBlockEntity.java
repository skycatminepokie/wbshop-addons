package com.skycat.wbshopaddons.blocks;

import com.skycat.wbshopaddons.WBShopAddons;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class DonaterBlockEntity extends BlockEntity {
    public DonaterBlockEntity(BlockPos pos, BlockState state) {
        super(WBShopAddons.DONATER_BLOCK_ENTITY, pos, state);
    }
}
