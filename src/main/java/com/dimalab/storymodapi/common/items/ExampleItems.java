package com.dimalab.storymodapi.common.items;

import com.dimalab.storymodapi.api.ObjectRegister;
import net.minecraft.item.Item;

public class ExampleItems {
    @ObjectRegister(register = true)
    public static final Item AMETHYST = new Item(new Item.Properties());

    //@ObjectRegister(value = "storymodapi:example_block", register = true)
    //public static final Block EXAMPLE_BLOCK = new Block(Block.Properties.of(Material.STONE));

}
