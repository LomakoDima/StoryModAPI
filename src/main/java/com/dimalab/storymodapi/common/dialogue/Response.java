package com.dimalab.storymodapi.common.dialogue;

public class Response {
    private final String text;
    private final String nextDialogId;

    public Response(String text, String nextDialogId) {
        this.text = text;
        this.nextDialogId = nextDialogId;
    }

    public String getText() {
        return text;
    }

    public String getNextDialogId() {
        return nextDialogId;
    }
}
