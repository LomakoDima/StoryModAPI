package com.dimalab.storymodapi;

//import com.dimalab.storymod.event.TickEventMessage;
import com.dimalab.storymodapi.common.event.TickEventMessage;
import com.dimalab.storymodapi.common.event.custom.MyEventHandler;
import com.dimalab.storymodapi.common.items.ExampleItems;
import com.dimalab.storymodapi.common.event.ZombieQuestMod;
import com.dimalab.storymodapi.common.registry.ModProccessor;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(StoryModAPI.MOD_ID)
public class StoryModAPI
{

    //MOD_ID
    public static final String MOD_ID = "storymodapi";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public StoryModAPI() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModProccessor.registerItems(ExampleItems.class);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new TickEventMessage());

        MinecraftForge.EVENT_BUS.register(MyEventHandler.class);
        MinecraftForge.EVENT_BUS.register(ZombieQuestMod.class);
    }
    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }









    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
        
    }
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

    }
}
