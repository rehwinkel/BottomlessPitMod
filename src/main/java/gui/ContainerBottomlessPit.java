package gui;

import com.deerangle.tile.entity.TileEntityBottomlessPit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBottomlessPit extends Container {

    public ContainerBottomlessPit(InventoryPlayer playerInventory, TileEntityBottomlessPit tileEntity) {
        IItemHandler inv = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        addSlotToContainer(new SlotItemHandler(inv, 0, 10, 10));

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
