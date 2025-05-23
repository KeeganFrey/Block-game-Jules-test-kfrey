package com.example.examplemod.data;

import com.example.examplemod.core.ModBlocks;
import com.example.examplemod.core.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(pOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        // Adamantite
        oreSmelting(pRecipeOutput, List.of(ModBlocks.ADAMANTITE_ORE.get(), ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get()), RecipeCategory.MISC, ModItems.ADAMANTITE.get(), 0.7f, 200, "adamantite");
        oreBlasting(pRecipeOutput, List.of(ModBlocks.ADAMANTITE_ORE.get(), ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get()), RecipeCategory.MISC, ModItems.ADAMANTITE.get(), 0.7f, 100, "adamantite");

        // Trophilite
        oreSmelting(pRecipeOutput, List.of(ModBlocks.TROPHILITE_ORE.get(), ModBlocks.DEEPSLATE_TROPHILITE_ORE.get()), RecipeCategory.MISC, ModItems.TROPHILITE.get(), 0.7f, 200, "trophilite");
        oreBlasting(pRecipeOutput, List.of(ModBlocks.TROPHILITE_ORE.get(), ModBlocks.DEEPSLATE_TROPHILITE_ORE.get()), RecipeCategory.MISC, ModItems.TROPHILITE.get(), 0.7f, 100, "trophilite");

        // Xarium
        oreSmelting(pRecipeOutput, List.of(ModBlocks.XARIUM_ORE.get(), ModBlocks.DEEPSLATE_XARIUM_ORE.get()), RecipeCategory.MISC, ModItems.XARIUM.get(), 0.7f, 200, "xarium");
        oreBlasting(pRecipeOutput, List.of(ModBlocks.XARIUM_ORE.get(), ModBlocks.DEEPSLATE_XARIUM_ORE.get()), RecipeCategory.MISC, ModItems.XARIUM.get(), 0.7f, 100, "xarium");
    }

    // Helper methods from `RecipeProvider` like `oreSmelting` and `oreBlasting` are protected, so we call them directly.
    // protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
    //    SimpleCookingRecipeBuilder.smelting(Ingredient.of(pIngredients.toArray(new ItemLike[0])), pCategory, pResult, pExperience, pCookingTime).group(pGroup).unlockedBy(getHasName(pIngredients.get(0)), has(pIngredients.get(0))).save(pRecipeOutput, getItemName(pResult) + "_from_smelting_" + getItemName(pIngredients.get(0)));
    // }

    // protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
    //    SimpleCookingRecipeBuilder.blasting(Ingredient.of(pIngredients.toArray(new ItemLike[0])), pCategory, pResult, pExperience, pCookingTime).group(pGroup).unlockedBy(getHasName(pIngredients.get(0)), has(pIngredients.get(0))).save(pRecipeOutput, getItemName(pResult) + "_from_blasting_" + getItemName(pIngredients.get(0)));
    // }
}
