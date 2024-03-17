package com.dimalab.storymodapi.common.gui.button;

import com.dimalab.storymodapi.common.dialogue.DialogScreen;
import com.dimalab.storymodapi.common.dialogue.Response;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class ChoiceButton extends Button {
    private final Response response;

    public ChoiceButton(int x, int y, Response response, IPressable onPress) {
        super(x, y, 150, 30, new StringTextComponent(response.getText()), onPress);
        this.response = response;

    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft.getInstance().getTextureManager().bind(DialogScreen.BUTTON_TEXTURE);

        blit(matrixStack, x, y, 0, 0, width, height, 260, 40);

        drawCenteredString(matrixStack, Minecraft.getInstance().font, getMessage(), x + width / 2, y + (height - 8) / 2, 0xFFFFFF);
    }
}
