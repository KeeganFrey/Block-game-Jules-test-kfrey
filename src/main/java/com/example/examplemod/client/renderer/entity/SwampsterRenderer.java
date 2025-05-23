package com.example.examplemod.client.renderer.entity;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.ExampleMod; // Ensure ExampleMod is imported
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer; // Reverted superclass
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie; // Ensure Zombie is imported

public class SwampsterRenderer extends ZombieRenderer { // Reverted superclass

    private static final ResourceLocation SWAMPSTER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "textures/entity/swampster.png"); // Defined field

    public SwampsterRenderer(EntityRendererProvider.Context context) {
        super(context); // Reverted constructor
    }

    // @Override // Annotation removed
    public ResourceLocation getTextureLocation(Zombie entity) { // Reverted signature
        return SWAMPSTER_TEXTURE;
    }
}
