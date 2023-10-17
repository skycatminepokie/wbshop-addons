package com.skycat.wbshopaddons;

import com.skycat.wbshopaddons.blocks.DonaterBlock;
import com.skycat.wbshopaddons.blocks.DonaterBlockEntity;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class WBShopAddons implements ModInitializer {
    public static final String MOD_ID = "wbshop-addons";
    public static final DonaterBlock DONATER_BLOCK = new DonaterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK), Blocks.IRON_BLOCK);
    public static final PolymerBlockItem DONATER_ITEM = new PolymerBlockItem(DONATER_BLOCK, new FabricItemSettings(), Items.IRON_BLOCK);
    public static final BlockEntityType<DonaterBlockEntity> DONATER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(MOD_ID, "donater"),
            FabricBlockEntityTypeBuilder.create(DonaterBlockEntity::new, DONATER_BLOCK).build());

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "donater"), DONATER_BLOCK);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "donater"), DONATER_ITEM);
        PolymerBlockUtils.registerBlockEntity(DONATER_BLOCK_ENTITY);
    }
}
