package com.dimalab.storymodapi.common.dialogue;

import com.dimalab.storymodapi.StoryModAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class ExampleDialogue {
    public static void initializeDialogs() {
        Dialog creeperDialog = new DialogBuilder("creeper_intro")
                //.setSkipButton("Пропустить", "creeper_intro")
                .setCharacterName("Зомби")
                .setText("Я агент 228")
                .setCharacterEntity(() -> new ZombieEntity(Minecraft.getInstance().level))
                .addResponse("bla", "creeper_yes")
                .addResponse("blaaa", "creeper_no")
                .build();


        DialogManager.addDialog("creeper_intro", creeperDialog);

        Dialog creeperYesDialog = new DialogBuilder("creeper_yes")
                .setCharacterName("Player")
                .setText("bla-bla")
                .setCharacterEntity(() -> EntityType.ZOMBIE.create(Minecraft.getInstance().level))
                .build();

        DialogManager.addDialog("creeper_yes", creeperYesDialog);

        Dialog creeperExplanationDialog = new DialogBuilder("creeper_explanation")
                .setCharacterName("John")
                .setText("bla-bla1")
                .setCharacterEntity(() -> EntityType.CHICKEN.create(Minecraft.getInstance().level))
                .addResponse("bla-bla", "creeper_goodbye")
                .addResponse("bla-bla", "creeper_details")
                .build();

        DialogManager.addDialog("creeper_explanation", creeperExplanationDialog);

        Dialog initialDialog = DialogManager.getDialog("creeper_intro");
        if (initialDialog != null) {
            Minecraft.getInstance().setScreen(new DialogScreen(initialDialog));
        }
    }
}
