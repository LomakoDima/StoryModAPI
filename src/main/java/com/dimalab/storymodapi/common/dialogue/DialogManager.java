package com.dimalab.storymodapi.common.dialogue;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.HashMap;
import java.util.Map;

public class DialogManager {
    private static final Map<String, Dialog> dialogs = new HashMap<>();

    public static void addDialog(String id, Dialog dialog) {
        dialogs.put(id, dialog);
    }



    public static Dialog getDialog(String id) {
        return dialogs.get(id);
    }

    public static void startDialogue(ServerPlayerEntity player, String dialogueId) throws CommandSyntaxException {
        Dialog dialog = getDialog(dialogueId);
        if (dialog != null) {
            Minecraft.getInstance().setScreen(new DialogScreen(dialog));
        } else {
            throw new SimpleCommandExceptionType(new TranslationTextComponent("commands.dialogue.not_found")).create();
        }
    }
}
