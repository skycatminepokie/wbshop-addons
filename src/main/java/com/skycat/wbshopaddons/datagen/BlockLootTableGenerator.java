package com.skycat.wbshopaddons.datagen;

import com.skycat.wbshopaddons.WBShopAddons;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    public BlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(WBShopAddons.DONATER_BLOCK, drops(WBShopAddons.DONATER_ITEM));
    }
}
