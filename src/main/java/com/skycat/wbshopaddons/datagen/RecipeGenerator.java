package com.skycat.wbshopaddons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

import static com.skycat.wbshopaddons.WBShopAddons.DONATER_BLOCK;

public class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, DONATER_BLOCK)
                .pattern("i i")
                .pattern("ili")
                .pattern("iii")
                .input('i', Items.IRON_INGOT)
                .input('l', Items.LAVA_BUCKET)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT), FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.LAVA_BUCKET), FabricRecipeProvider.conditionsFromItem(Items.LAVA_BUCKET))
                .offerTo(exporter);
    }
}
