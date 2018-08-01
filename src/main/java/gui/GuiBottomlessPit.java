package gui;

import com.deerangle.main.BottomlessMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.lwjgl.Sys;

import java.io.IOException;

public class GuiBottomlessPit extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BottomlessMod.MODID, "textures/gui/bottomlesspit.png");

    private ContainerBottomlessPit container;
    private final int exposedSlots = 3;
    private final int totalSlots;
    private int scrollOffset = 0;
    private int scrollPos = 0;

    private final int scrollbar_height = 52;
    private final int scroller_height = 15;
    private final int scroller_offset = (scroller_height / 2) - 1;

    public GuiBottomlessPit(ContainerBottomlessPit container) {
        super(container);
        IItemHandler itemHandler = container.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        totalSlots = itemHandler.getSlots() / 9;
        this.container = container;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize + 18, ySize);
        drawScrollbar(totalSlots > exposedSlots);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(I18n.format("container.bottomlesspit"), 8, 6, 4210752);
        this.fontRenderer.drawString(container.inventoryPlayer.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 3, 4210752);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        handleScrollbar();

        for(int i = 0; i < totalSlots; i++) {
            for(int j = 0; j < 9; j++){
                setSlotVisibility(i * 9 + j, i >= scrollOffset && i < exposedSlots + scrollOffset, -scrollOffset * 18);
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
        for(int i = 0; i < totalSlots; i++) {
            for(int j = 0; j < 9; j++){
                setSlotVisibility(i * 9 + j, i >= scrollOffset && i < exposedSlots + scrollOffset, scrollOffset * 18);
            }
        }

        this.renderHoveredToolTip(mouseX, mouseY);
    }

    private void handleScrollbar() {
        int pos = MathHelper.clamp(scrollPos - scroller_offset, 0, scrollbar_height - scroller_height);
        float scrolled = pos / (float) (scrollbar_height - scroller_height);
        scrollOffset = Math.round((totalSlots - exposedSlots) * scrolled);
    }

    private void setSlotVisibility(int index, boolean visible, int offset) {
        SlotBottomlessPit s = (SlotBottomlessPit) inventorySlots.inventorySlots.get(index);
        s.setEnabled(visible);
        s.yPos = s.yPos + offset;
        inventorySlots.inventorySlots.set(index, s);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        final int scrollbar_height = 52;

        int x = mouseX - (guiLeft + 174);
        int y = mouseY - (guiTop + 19);
        if (x >= 0 && y >= 0 && y < scrollbar_height && x < 12) {
            scrollPos = y;
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        int x = mouseX - (guiLeft + 174);
        int y = mouseY - (guiTop + 19);
        if (x >= 0 && y >= 0 && y < scrollbar_height && x < 12) {
            scrollPos = y;
        }
    }

    private void drawScrollbar(boolean enabled) {
        int pos = MathHelper.clamp(scrollPos - scroller_offset, 0, scrollbar_height - scroller_height);
        drawTexturedModalRect(guiLeft + 174, guiTop + 18 + pos, 232 + (enabled ? 0 : 12), 0, 12, scroller_height);
    }

}
