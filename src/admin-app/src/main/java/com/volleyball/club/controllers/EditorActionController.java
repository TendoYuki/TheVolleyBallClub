package com.volleyball.club.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.volleyball.club.elements.editor.EditorActions;

/**
 * Controller class managing the interactions of the ActionController
 */
public abstract class EditorActionController {
    private EditorActions editorActions;

    /**
     * Creates an editor action controller linked to an editor action element
     * @param editorActions editor actions element
     */
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

    /**
     * Method called when the save button is pressed
     */
    public abstract void onSave();
    
    /**
     * Method called when the cancel button is pressed
     */
    public abstract void onCancel();

    /**
     * Method called when the delete button is pressed
     */
    public abstract void onDelete();
}
