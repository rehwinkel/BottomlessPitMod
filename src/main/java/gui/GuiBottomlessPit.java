package gui;

import com.deerangle.main.BottomlessMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiBottomlessPit extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BottomlessMod.MODID, "textures/gui/bottomlesspit.png");

    public GuiBottomlessPit(ContainerBottomlessPit container) {
        super(container);
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize + 18, ySize);
    }

}
