package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.volleyball.club.elements.EditorActions;

public abstract class EditorActionController {
    private EditorActions editorActions;

    public EditorActionController(EditorActions editorActions) {
        this.editorActions = editorActions;
        this.editorActions.addOnCancelActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                onCancel();
            }
        });
        this.editorActions.addOnSaveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                onSave();
            }
        });
        this.editorActions.addOnDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                onDelete();
            }
        });
    }

    public abstract void onSave();
    public abstract void onCancel();
    public abstract void onDelete();
}
