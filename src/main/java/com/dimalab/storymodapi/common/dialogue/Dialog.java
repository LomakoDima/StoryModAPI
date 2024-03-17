package com.dimalab.storymodapi.common.dialogue;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Dialog {
    private final String id;
    private final String text;
    private int colorText;
    private final List<Response> responses;
    private String characterName;

    private Supplier<LivingEntity> characterEntity;


    //private String nextDialogId;

    public Dialog(String id, String text) {
        this.id = id;
        this.text = text;
        this.responses = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    public void setColorCode(int colorText) {
        this.colorText = colorText;
    }



    public boolean hasResponses() {
        return !responses.isEmpty();
    }

    public String getCharacterName() {
        return characterName != null ? characterName : "";
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /*public String getNextDialogId() {
        return this.nextDialogId;
    }*/

    public Supplier<LivingEntity> getCharacterEntity() {
        return characterEntity;
    }

    public void setCharacterEntity(Supplier<LivingEntity> characterEntity) {
        this.characterEntity = characterEntity;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void addResponse(Response response) {
        responses.add(response);
    }
}
