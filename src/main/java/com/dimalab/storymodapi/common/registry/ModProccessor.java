package com.dimalab.storymodapi.common.registry;

import com.dimalab.storymodapi.api.ObjectRegister;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ForgeRegistries;


import java.lang.reflect.Field;
import java.util.Locale;


public class ModProccessor {
    public static void registerItems(Class<?> modItemsClass) {
        for (Field field : modItemsClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ObjectRegister.class)) {
                ObjectRegister objectRegisterAnnotation = field.getAnnotation(ObjectRegister.class);
                boolean shouldRegister = objectRegisterAnnotation.register();

                if (shouldRegister) {
                    if (field.getType() == Item.class) {
                        String itemName = field.getName().toLowerCase(Locale.ROOT);
                        Item item = new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD)).setRegistryName(itemName);
                        ForgeRegistries.ITEMS.registerAll(item);
                    } else if (field.getType() == Block.class) {
                        String blockName = field.getName().toLowerCase(Locale.ROOT);
                        Block block = new Block(Block.Properties.of(Material.STONE)).setRegistryName(blockName);
                        ForgeRegistries.BLOCKS.registerAll(block);

                        ForgeRegistries.ITEMS.registerAll(
                                new BlockItem(block, new Item.Properties().tab(ItemGroup.TAB_FOOD)).setRegistryName(blockName)
                        );
                    }
                }
            }
        }
    }

}

