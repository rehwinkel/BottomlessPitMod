package com.deerangle.gui;

import com.deerangle.main.BottomlessMod;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiBottomlessPit extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BottomlessMod.MODID, "textures/gui/bottomlesspit.png");

    private ContainerBottomlessPit container;
    private List<Integer> rawSlots = new ArrayList<Integer>();

    private final int exposedSlots = 3;
    private final int totalSlots;
    private int scrollPos = 0;
    private double scrollPosPixels = 0;
    private boolean isScrolling = false;

    private final int scrollbarHeight = 52;
    private final int scrollerHeight = 15;
    private final int scrollerOffset = (scrollerHeight / 2) - 1;

    public GuiBottomlessPit(ContainerBottomlessPit container) {
        super(container);
        IItemHandler itemHandler = container.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        totalSlots = itemHandler.getSlots() / 9;
        this.container = container;
        xSize = 176;
        ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
        rawSlots.clear();
        for(int i = 0; i < totalSlots * 9; i++){
            rawSlots.add(inventorySlots.inventorySlots.get(i).yPos);
        }
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

        //System.out.println(scrollPos);
        for(int i = 0; i < totalSlots * 9; i++) {
            inventorySlots.inventorySlots.get(i).yPos = rawSlots.get(i) - 18 * scrollPos;
        }

        for(int i = 0; i < totalSlots; i++) {
            for(int j = 0; j < 9; j++){
                SlotBottomlessPit s = (SlotBottomlessPit) inventorySlots.inventorySlots.get(i * 9 + j);
                s.setEnabled(i >= scrollPos && i < exposedSlots + scrollPos);
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.renderHoveredToolTip(mouseX, mouseY);
    }

    private void handleScrollbar() {
        double pos = MathHelper.clamp(scrollPosPixels - scrollerOffset, 0, scrollbarHeight - scrollerHeight);
        double scrolled = pos / (double) (scrollbarHeight - scrollerHeight);
        scrollPos = (int) Math.round((totalSlots - exposedSlots) * scrolled);
    }

    private void drawScrollbar(boolean enabled) {
        double pos = MathHelper.clamp(scrollPosPixels - scrollerOffset, 0, scrollbarHeight - scrollerHeight);
        if(!enabled) pos = 0;
        drawTexturedModalRect(guiLeft + 174, guiTop + 18 + (int) pos, 232 + (enabled ? 0 : 12), 0, 12, scrollerHeight);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        final int scrollbar_height = 52;

        int x = mouseX - (guiLeft + 174);
        int y = mouseY - (guiTop + 19);
        if (x >= 0 && y >= 0 && y < scrollbar_height && x < 12) {
            scrollPosPixels = y;
            isScrolling = true;
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        isScrolling = false;
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if (isScrolling) {
            scrollPosPixels = mouseY - (guiTop + 19);
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int wheelScroll = Mouse.getEventDWheel();

        if (wheelScroll == 0) return;
        if(wheelScroll > 1) {
            wheelScroll = 1;
        }
        if(wheelScroll < 1) {
            wheelScroll = -1;
        }
        double spp = scrollPosPixels + (-wheelScroll * ((double) (scrollbarHeight - scrollerHeight) / (totalSlots - exposedSlots)));
        scrollPosPixels = MathHelper.clamp(spp, scrollerOffset, scrollbarHeight - scrollerOffset - 3);
    }

}
