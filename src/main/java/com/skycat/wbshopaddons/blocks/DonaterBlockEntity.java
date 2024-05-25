package com.skycat.wbshopaddons.blocks;

import com.skycat.wbshop.WBShop;
import com.skycat.wbshop.econ.Economy;
import com.skycat.wbshopaddons.WBShopAddons;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class DonaterBlockEntity extends DispenserBlockEntity {
    private long points = 0;

    public DonaterBlockEntity(BlockPos pos, BlockState state) {
        super(WBShopAddons.DONATER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        points = nbt.getLong("points");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putLong("points", points);
        super.writeNbt(nbt);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (world instanceof ServerWorld serverWorld) {
            Economy econ = WBShop.getEconomy(serverWorld.getServer());
            points += econ.pointValueOf(stack);
        }
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
        markDirty();
    }

}
