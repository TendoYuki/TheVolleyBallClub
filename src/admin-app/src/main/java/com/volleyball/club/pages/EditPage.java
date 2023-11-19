package com.volleyball.club.pages;

import java.awt.GridBagLayout;

/** Abstract edition page */
public abstract class EditPage extends Page {
    /** Creates a new edition page */
    public EditPage() {
        super();
        setLayout(new GridBagLayout());
    }

    /**
     * Clears the edit pages's fields
     */
    public abstract void clear();
}
