package com.deerangle.gui;

import com.deerangle.tile.entity.TileEntityBottomlessPit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerBottomlessPit extends Container {

    InventoryPlayer inventoryPlayer;
    TileEntityBottomlessPit tileEntity;

    public ContainerBottomlessPit(InventoryPlayer playerInventory, TileEntityBottomlessPit tileEntity) {
        this.inventoryPlayer = playerInventory;
        this.tileEntity = tileEntity;

        IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        for(int j = 0; j < inventory.getSlots() / 9; j++){
            for (int i = 0; i < 9; i++) {
                addSlotToContainer(new SlotBottomlessPit(inventory, i + j * 9, 8 + (i * 18), 18 + (j * 18)));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
