package com.dimalab.storymodapi.common.notifications;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;

public class NotificationScreen extends Screen {
    private final String message;
    private final String additionalMessage;
    private final long displayTime;
    private long startTime;
    private boolean closing = false;

    private int animationTime = 1000; // Время анимации в миллисекундах
    private int targetX = 10;
    private int startX;

    int notificationWidth = 250;
    int notificationHeight = 60;
    int x;
    int y;

    private final ResourceLocation closeTexture = new ResourceLocation("storymod", "textures/gui/close.png");
    private Button closeButton;


    public NotificationScreen(String message, String additionalMessage, long displayTime) {
        super(new StringTextComponent("Notifications"));
        this.message = message;
        this.additionalMessage = additionalMessage;
        this.displayTime = displayTime;
        this.startTime = System.currentTimeMillis();
        this.startX = -200; // Начальное значение X за пределами экрана слева
    }

    @Override
    public void init() {
        super.init();
        closeButton = new Button(x + notificationWidth - 5,  y + 12,   12, 12, StringTextComponent.EMPTY, button -> onClose()) {
            @Override
            public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                Minecraft.getInstance().getTextureManager().bind(closeTexture);
                int u = 0;
                int v = 0;
                blit(matrices, this.x, this.y, u, v, this.width, this.height, 12, 12); // Используйте размеры вашей текстуры
            }
        };
        addButton(closeButton);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (!closing) {
            drawNotification(matrices, mouseX, mouseY, delta);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void drawNotification(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int width = this.width;
        int height = this.height;

        int x = calculateX();
        int y = 10;

        int additionalMessageWidth = notificationWidth - 20; // Ширина второго текста
        int additionalMessageHeight = 10; // Высота второго текста

        int alpha = calculateAlpha();

        int topGradientColor = 0x6000FFFF;
        int bottomGradientColor = 0x6000FFFF;

        closeButton.visible = !closing;
        closeButton.render(matrices, mouseX, mouseY, delta);

        // Прозрачность окна
        int alphaColor = alpha << 24;

        fillGradient(matrices, x, y, x + notificationWidth, y + notificationHeight, topGradientColor, bottomGradientColor);

        drawBorder(matrices, x, y, notificationWidth, notificationHeight, 2, 0x3500FFFF);

        int shadowColor = 0x80000000 + (alpha << 24); // Цвет тени текста с учётом альфа-канала
        int additionalColor = 0xFFFFFF + (alpha << 24); // Цвет текста с учётом альфа канала

        // Установка стиля текста с тенью
        //Style styleWithShadow = Style.EMPTY.withColor(TextFormatting.WHITE).withColor(Color.fromRgb(shadowColor)).withBold(true);

        Minecraft.getInstance().font.drawShadow(matrices, message, x + 10, y + 10, 0xFFFFFF + (alpha << 24));
        Minecraft.getInstance().font.drawShadow(matrices, additionalMessage, x + 10, y + 30, additionalColor);

        if (x == targetX) {
            if (System.currentTimeMillis() > startTime + displayTime + 1000) {
                closing = true;
            }
        }
    }


    private int calculateX() {
        long elapsed = System.currentTimeMillis() - startTime;

        if (!closing) {
            if (elapsed < animationTime) {
                return (int) ((double) elapsed / animationTime * (targetX - startX) + startX);
            } else {
                return targetX;
            }
        } else {
            // Логика для плавного закрытия уведомления
            int closingSpeed = 3; // Скорость закрытия, настройте по своему усмотрению

            int deltaX = (int) (closingSpeed * Math.sqrt(elapsed));

            return targetX + deltaX;
        }
    }
    private int calculateAlpha() {
        long elapsed = System.currentTimeMillis() - startTime;
        int alpha = 255;

        if (elapsed < displayTime) {
            alpha = 255;
        } else if (elapsed > displayTime + 1000) {
            Minecraft.getInstance().setScreen(null); // Закрываем экран уведомления
        }

        return alpha;
    }

    // Отрисовка градиента
    public void fillGradient(MatrixStack matrices, int x1, int y1, int x2, int y2, int color1, int color2) {
        fillGradient2(matrices, x1, y1, x2, y2, 0, color1, color2);
    }

    // Отрисовка градиента
    private void fillGradient2(MatrixStack matrices, int x1, int y1, int x2, int y2, int z, int color1, int color2) {
        fill(matrices, x1, y1, x2, y2, color1);
        fill(matrices, x1, y1, x2, y1 + 1, color2);
        fill(matrices, x1, y1 + 1, x1 + 1, y2 - 1, color2);
        fill(matrices, x2 - 1, y1 + 1, x2, y2 - 1, color2);
        fill(matrices, x1, y2 - 1, x2, y2, color1);
    }

    // Отрисовка рамки
    private void drawBorder(MatrixStack matrices, int x, int y, int width, int height, int thickness, int color) {
        fill(matrices, x, y, x + width, y + thickness, color);
        fill(matrices, x, y + thickness, x + thickness, y + height - thickness, color);
        fill(matrices, x + width - thickness, y + thickness, x + width, y + height - thickness, color);
        fill(matrices, x, y + height - thickness, x + width, y + height, color);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(null); // Закрываем экран уведомления при закрытии
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

