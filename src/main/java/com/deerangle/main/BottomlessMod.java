package com.deerangle.main;

import com.deerangle.tile.ModBlocks;
import com.deerangle.tile.entity.TileEntityBottomlessPit;
import gui.ModGuiHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = BottomlessMod.MODID, name = BottomlessMod.NAME, version = BottomlessMod.VERSION)
public class BottomlessMod {

    public static final String MODID = "thebottomlesspit";
    public static final String NAME = "The Bottomless Pit";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static BottomlessMod INSTANCE = new BottomlessMod();

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        ModBlocks.onPreInit();

        GameRegistry.registerTileEntity(TileEntityBottomlessPit.class, new ResourceLocation(MODID, "bottomlesspit"));

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
    }

}
