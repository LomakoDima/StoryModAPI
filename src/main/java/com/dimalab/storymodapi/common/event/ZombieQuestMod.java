package com.dimalab.storymodapi.common.event;

import com.dimalab.storymodapi.common.dialogue.ExampleDialogue;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
public class ZombieQuestMod {


    @SubscribeEvent
    public static void onZombieDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ZombieEntity) {
            if (event.getSource().getEntity() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getSource().getEntity();
                giveGoldReward(player);
            }
        }
    }

    private static void giveGoldReward(ServerPlayerEntity player) {
        ItemStack goldCoins = new ItemStack(Items.BOOK, 5);


        if (!player.inventory.add(goldCoins)) {


            player.drop(goldCoins, false);
        }

        //Minecraft.getInstance().setScreen(new NotificationScreen("Ты выполнил квест!", "Ты убил зомби и получил 5 золотых слитков", 10000));
        ExampleDialogue.initializeDialogs();
    }
}
