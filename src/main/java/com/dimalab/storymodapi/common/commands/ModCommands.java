package com.dimalab.storymodapi.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.player.PlayerEntity;
import com.dimalab.storymodapi.common.dialogue.DialogManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ModCommands {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("StoryModAPI")
                .then(Commands.literal("start_dialogue")
                        .then(Commands.argument("dialogueId", EntityArgument.player())
                                .executes(context -> {
                                    return startDialogue(context);
                                })
                        )
                )
        );
    }

    private static int startDialogue(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgument.getPlayer(context, "dialogueId");
        String dialogueId = context.getArgument("dialogueId", String.class);

        try {
            DialogManager.startDialogue(player, dialogueId);
            return 1;
        } catch (CommandSyntaxException e) {
            throw new SimpleCommandExceptionType(new TranslationTextComponent("commands.dialogue.not_found")).create();
        }
    }
}
