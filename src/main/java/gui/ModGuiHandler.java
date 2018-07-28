package gui;

import com.deerangle.tile.entity.TileEntityBottomlessPit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class ModGuiHandler implements IGuiHandler {

    public static final int bottomlesspit_id = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case bottomlesspit_id:
                return new ContainerBottomlessPit(player.inventory, (TileEntityBottomlessPit) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case bottomlesspit_id:
                return new GuiBottomlessPit((ContainerBottomlessPit) getServerGuiElement(ID, player, world, x, y, z));
        }
        return null;
    }
}
