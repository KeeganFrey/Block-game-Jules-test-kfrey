package com.example.examplemod.client.renderer.entity;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.ExampleMod; // Ensure ExampleMod is imported if used in ResourceLocation
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer; // Reverted superclass
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie; // Ensure Zombie is imported

public class CaveCrawlerRenderer extends ZombieRenderer { // Reverted superclass

    private static final ResourceLocation CAVE_CRAWLER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "textures/entity/cave_crawler.png");

    public CaveCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context); // Reverted constructor
    }

    // @Override // Annotation removed
    public ResourceLocation getTextureLocation(Zombie entity) { // Reverted signature
        return CAVE_CRAWLER_TEXTURE;
    }
}
