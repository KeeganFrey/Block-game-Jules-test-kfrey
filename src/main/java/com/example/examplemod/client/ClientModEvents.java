package com.example.examplemod.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.client.renderer.entity.CaveCrawlerRenderer;
import com.example.examplemod.client.renderer.entity.SwampsterRenderer;
import com.example.examplemod.entity.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ExampleMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CAVE_CRAWLER.get(), CaveCrawlerRenderer::new);
        event.registerEntityRenderer(ModEntities.SWAMPSTER.get(), SwampsterRenderer::new);
    }
}
