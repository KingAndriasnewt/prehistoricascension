package net.newt.prehistoricascension.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.newt.prehistoricascension.PrehistoricAscension;

public class FossilCleanerScreen extends AbstractContainerScreen<FossilCleanerMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(PrehistoricAscension.MOD_ID, "textures/gui/fossil_cleaner_gui.png");

    private static final int[] BUBBLELENGTHS = new int[]{29, 24, 20, 16, 11, 6, 0};

    public FossilCleanerScreen(FossilCleanerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);

        renderBrewingBubbles(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 101, y + 15, 179, 0, 7, menu.getScaledProgress());
        }
    }

    private void renderBrewingBubbles(GuiGraphics guiGraphics, int x, int y) {
        int brewingTicks = menu.getBrewingTicks();
        if (brewingTicks > 0) {
            int verticalProgress = (int)(28.0F * (1.0F - (float)brewingTicks / (float)menu.getMaxBrewingTicks()));
            if (verticalProgress > 0) {
                guiGraphics.blit(TEXTURE, x + 97, y + 16, 176, 0, 9, verticalProgress);
            }

            int bubbleHeight = BUBBLELENGTHS[brewingTicks / 4 % 7];
            if (bubbleHeight > 0) {
                guiGraphics.blit(TEXTURE, x + 61, y + 14 + 30 - bubbleHeight, 185, 29 - bubbleHeight, 12, bubbleHeight);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}