package com.deerangle.tile;

import com.deerangle.main.BottomlessMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModBlocks {

    public static ModBlocks instance = new ModBlocks();

    public static Block bottomlesspit;

    public static void onPreInit() {
        bottomlesspit = new BlockBottomlessPit();

        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(bottomlesspit);
    }

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(getItemBlock(bottomlesspit));
    }

    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        registerModel(Item.getItemFromBlock(bottomlesspit), 0, "bottomlesspit");
    }

    private void registerModel(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(BottomlessMod.MODID + ":" + name, "inventory"));
    }

    private Item getItemBlock(Block block) {
        ItemBlock ib = new ItemBlock(block);
        ib.setRegistryName(block.getRegistryName());
        return ib;
    }

}
