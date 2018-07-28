package gui;

import com.deerangle.tile.entity.TileEntityBottomlessPit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerBottomlessPit extends Container {

    public ContainerBottomlessPit(IInventory playerInventory, TileEntityBottomlessPit tileEntity) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }
}
