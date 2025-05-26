package com.example.test_block;

import com.example.test_block.init.ModBlocks;
import com.example.test_block.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TestBlockMod.MOD_ID)
public class TestBlockMod {
    public static final String MOD_ID = "test_block";

    public TestBlockMod(IEventBus modEventBus) {
        // Register deferred registers
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        // You can add other event listeners here
    }
}
