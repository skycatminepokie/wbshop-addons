package com.skycat.wbshopaddons.blocks;

import com.skycat.wbshop.WBShop;
import com.skycat.wbshop.econ.Economy;
import com.skycat.wbshopaddons.WBShopAddons;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class DonaterBlockEntity extends BlockEntity {
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



    private final SimpleInventory inventory = new SimpleInventory(1) {
        @Override
        public boolean canInsert(ItemStack stack) {
            return true;
        }

        @Override
        public ItemStack addStack(ItemStack stack) {
            Economy econ = WBShop.getEconomy();
            if (econ != null) {
                points += econ.pointValueOf(stack);
                markDirty();
                return ItemStack.EMPTY;
            }
            return stack;
        }

        @Override
        public void markDirty() {
            DonaterBlockEntity.this.markDirty();
        }
    };

    public final InventoryStorage inventoryWrapper = InventoryStorage.of(inventory, null);
}
