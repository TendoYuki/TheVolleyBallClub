package com.volleyball.club.pages;

import java.awt.GridBagLayout;

public abstract class EditPage extends Page {
    public EditPage() {
        super();
        setLayout(new GridBagLayout());
    }

    /**
     * Clears the edit pages's fields
     */
    public abstract void clear();
}
