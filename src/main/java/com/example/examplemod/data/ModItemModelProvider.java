package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.core.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Register models for the smelted items
        simpleItem(ModItems.ADAMANTITE);
        simpleItem(ModItems.TROPHILITE);
        simpleItem(ModItems.XARIUM);

        // Block Items will be handled by simpleBlockWithItem in BlockStateProvider or here if preferred
        // For now, let BlockStateProvider handle them to keep things grouped.
    }

    private ItemModelBuilder simpleItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ExampleMod.MODID, "item/" + item.getId().getPath()));
    }
}
