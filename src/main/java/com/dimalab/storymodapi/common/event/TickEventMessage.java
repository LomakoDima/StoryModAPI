package com.dimalab.storymodapi.common.event;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
public class TickEventMessage {
    private static int tickgame = 0;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getInstance().player != null) {
            tickgame++;

            if (tickgame == 100) {
                sendMessageToChat("§b[Система уведомлений] §f[Начинаю процесс дешифрации...]");
            } else if (tickgame == 200) {
                sendMessageToChat("§6Для того чтобы прочитать сообщение нажмите 'левый alt'.");
            } else if (tickgame == 450) {
                sendMessageToChat("§b[???] §f[Вы активировали запрос на реконструкцию событий]");
            } else if (tickgame == 650) {
                sendMessageToChat("§b[???] §f[Направляю запрос в блок памяти...]");
            } else if (tickgame == 700) {
                sendMessageToChat("§b[???] §f[Инициализация...]");
            } else if (tickgame == 850) {
                sendMessageToChat("§b[???] §f[Критическая ошибка #17]");
            } else if (tickgame == 900) {
                sendMessageToChat("§b[???] §f[Данные повреждены]");
            } else if (tickgame == 950) {
                sendMessageToChat("§b[???] §f[Отсутствует деталь Гамма]");
            } else if (tickgame == 1050) {
                sendMessageToChat("§b[???] §f[Ошибка чтения]");
            } else if (tickgame == 1150) {
                sendMessageToChat("§b[???] §f[Данные повреждены]");
            } else if (tickgame == 1250) {
                sendMessageToChat("§b[???] §f[Активирован протокол гибернации]");
            }
        }
    }
    private static void sendMessageToChat(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), Minecraft.getInstance().player.getUUID());
    }
}