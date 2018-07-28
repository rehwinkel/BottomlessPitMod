package com.deerangle.tile;

import com.deerangle.main.BottomlessMod;
import gui.ModGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBottomlessPit extends Block {

    public  BlockBottomlessPit() {
        super(Material.WOOD);
        setCreativeTab(CreativeTabs.REDSTONE);
        setUnlocalizedName("bottomlesspit");
        setRegistryName("bottomlesspit");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) {
            playerIn.openGui(BottomlessMod.INSTANCE, ModGuiHandler.bottomlesspit_id, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        }
        return true;
    }
}
