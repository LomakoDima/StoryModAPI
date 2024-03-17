package com.dimalab.storymodapi.common.event.custom;

import com.dimalab.storymodapi.StoryModAPI;
import com.dimalab.storymodapi.common.commands.ModCommands;
import com.dimalab.storymodapi.common.playerneeds.NeedsScreen;
import com.dimalab.storymodapi.common.playerneeds.PlayerNeeds;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = StoryModAPI.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MyEventHandler {


    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        player.sendMessage(new StringTextComponent("Welcome to the game, " + player.getDisplayName().getString() + "!").withStyle(TextFormatting.GREEN), player.getUUID());
        StoryModAPI.LOGGER.info("Player joined the game: " + player.getDisplayName().getString());
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
            player.sendMessage(new StringTextComponent("You died!").withStyle(TextFormatting.RED), player.getUUID());
            StoryModAPI.LOGGER.info("Player died: " + player.getDisplayName().getString());
        } else {
            StoryModAPI.LOGGER.info("Entity died: " + event.getEntity().getDisplayName().getString());
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        World world = (World) event.getWorld();
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            StoryModAPI.LOGGER.info("World loaded: " + serverWorld.dimension().location());
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }

}




