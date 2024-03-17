package com.dimalab.storymodapi.common.dialogue;


import com.dimalab.storymodapi.StoryModAPI;
import com.dimalab.storymodapi.common.gui.button.ChoiceButton;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class DialogScreen extends Screen {
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(StoryModAPI.MOD_ID, "textures/gui/button/choice_button_.png");
    private  Dialog dialog;
    private String printedText;
    private int charactersToShow;
    private int maxCharacters;





    public DialogScreen(Dialog dialog) {
        super(new StringTextComponent("Dialog Screen"));
        this.dialog = dialog;
        this.printedText = "";
        this.charactersToShow = 0;
        this.maxCharacters = dialog.getText().length();
    }

    @Override
    public void tick() {
        super.tick();


        if (charactersToShow < maxCharacters) {
            printedText = dialog.getText().substring(0, charactersToShow + 1);
            charactersToShow++;
        }
    }



    @Override
    protected void init() {
        super.init();

        int buttonIndex = 0;
        int yOffset = 10;

        for (Response response : dialog.getResponses()) {
            addButton(new ChoiceButton(width / 2 - 75, height / 2 + yOffset + buttonIndex * 40, response,
                    (button) -> handleButtonClick(response)));
            buttonIndex++;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        onRenderEntity(mouseX, mouseY);

        int nameBackgroundWidth = font.width(dialog.getCharacterName()) + 20;
        int nameBackgroundHeight = 20;

        fill(matrixStack, 10, 85, 10 + nameBackgroundWidth, 85 + nameBackgroundHeight, 0xAA404040);

        fill(matrixStack, 0, 0, width, 80, 0xAA404040);





        int nameX = 20;
        int nameY = height - 450;
        font.draw(matrixStack, dialog.getCharacterName(), nameX, nameY, 0xFFFFFF);

        String fullText = printedText;
        int textX = (width - font.width(fullText)) / 2 - 390;
        int textY = 10;

        float scale = 2.0f;
        matrixStack.pushPose();
        matrixStack.scale(scale, scale, 1.0f);
        font.draw(matrixStack, fullText, textX, textY, 0xFFFFFF);
        matrixStack.popPose();


        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void handleButtonClick(Response response) {
        String nextDialogId = response.getNextDialogId();
        if (nextDialogId != null && !nextDialogId.isEmpty()) {
            Dialog nextDialog = DialogManager.getDialog(nextDialogId);
            if (nextDialog != null) {
                Minecraft.getInstance().setScreen(new DialogScreen(nextDialog));
            }
        } else {
            Minecraft.getInstance().setScreen(null);
        }
    }

    public void onRenderEntity(int mouseX, int mouseY){
        if (dialog.getCharacterEntity() != null) {
            LivingEntity entity = dialog.getCharacterEntity().get();

            if (entity != null) {
                float scaleFactor = entity.getBbHeight() / 1.8F;
                scaleFactor = Math.max(scaleFactor, 0.5F);
                float adaptiveScale = (int) (150.0F / scaleFactor);

                int xDistance = this.width / 2 + 250;
                int yDistance = this.height / 2 + 150;



                InventoryScreen.renderEntityInInventory(xDistance, yDistance, (int) adaptiveScale, (float) xDistance - mouseX, (float) yDistance - mouseY, entity);
            }
        }
    }


    /*private void drawDialogBackground(MatrixStack matrixStack, int x, int y, int width, int height) {
        fill(matrixStack, x - 2, y - 2, x + width + 2, y + height + 2, 0xFF00008B);
        fill(matrixStack, x, y, x + width, y + height, 0x8000008B);

        drawBorder(matrixStack, x, y, width, height, 1, 0xFF00008B);
    }

    private void drawBorder(MatrixStack matrixStack, int x, int y, int width, int height, int thickness, int color) {
        fill(matrixStack, x, y, x + width, y + thickness, color);
        fill(matrixStack, x, y + height - thickness, x + width, y + height, color);
        fill(matrixStack, x, y, x + thickness, y + height, color);
        fill(matrixStack, x + width - thickness, y, x + width, y + height, color);
    }*/

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 1) {
            handleRightClick();
        }


        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void handleRightClick() {
        Dialog currentDialog = dialog;

        if (currentDialog == null || !currentDialog.hasResponses()) {
            return;
        }

        Response nextResponse = currentDialog.getResponses().isEmpty() ? null : currentDialog.getResponses().get(0);

        if (nextResponse != null && !nextResponse.getNextDialogId().isEmpty()) {
            Dialog nextDialog = DialogManager.getDialog(nextResponse.getNextDialogId());

            if (nextDialog != null) {
                Minecraft.getInstance().setScreen(new DialogScreen(nextDialog));
            }
        }
    }
}
