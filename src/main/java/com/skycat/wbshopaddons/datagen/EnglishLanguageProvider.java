package com.skycat.wbshopaddons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static com.skycat.wbshopaddons.WBShopAddons.*;

public class EnglishLanguageProvider extends FabricLanguageProvider {
    protected EnglishLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder tb) {
        tb.add(DONATER_BLOCK, "Donater");
    }
}
