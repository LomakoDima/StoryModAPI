package com.dimalab.storymodapi.common.dialogue;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DialogBuilder {
    private final String id;
    private String text;

    private int textColor;
    private String characterName;

    private final List<Response> responses;
    private Supplier<LivingEntity> characterEntity;

    public DialogBuilder(String id) {
        this.id = id;
        this.responses = new ArrayList<>();
    }

    public DialogBuilder setCharacterName(String characterName) {
        this.characterName = characterName;
        return this;
    }

    public DialogBuilder setCharacterEntity(Supplier<LivingEntity> characterEntity) {
        this.characterEntity = characterEntity;
        return this;
    }

    public DialogBuilder addResponse(String responseText, String nextDialogId) {
        responses.add(new Response(responseText, nextDialogId));
        return this;
    }

    public DialogBuilder setText(String text) {
        this.text = text;
        return this;
    }


    public DialogBuilder setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public Dialog build() {
        Dialog dialog = new Dialog(id, text);
        dialog.setCharacterName(characterName);
        dialog.setCharacterEntity(characterEntity);
        dialog.setColorCode(textColor);
        for (Response response : responses) {
            dialog.addResponse(response);
        }
        return dialog;
    }
}
