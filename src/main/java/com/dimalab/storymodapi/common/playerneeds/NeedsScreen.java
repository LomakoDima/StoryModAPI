package com.dimalab.storymodapi.common.playerneeds;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class NeedsScreen extends Screen {
    private PlayerNeeds playerNeeds;

    public NeedsScreen(PlayerNeeds playerNeeds) {
        super(new StringTextComponent("Player Needs"));
        this.playerNeeds = playerNeeds;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        drawString(matrixStack, font, "Туалет: " + playerNeeds.getToiletLevel(), width / 2 - 50, height / 2 - 20, 0xFFFFFF);
        drawString(matrixStack, font, "Жажда: " + playerNeeds.getThirstLevel(), width / 2 - 50, height / 2, 0xFFFFFF);
        drawString(matrixStack, font, "Выносливость: " + playerNeeds.getStaminaLevel(), width / 2 - 50, height / 2 + 20, 0xFFFFFF);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
