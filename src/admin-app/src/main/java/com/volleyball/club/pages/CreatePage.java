package com.volleyball.club.pages;

import java.awt.GridBagLayout;

/** Abstract create page */
public abstract class CreatePage extends Page {
    /** Creates a new create page */
    public CreatePage() {
        super();
        setLayout(new GridBagLayout());
    }

    /**
     * Clears the create pages's fields
     */
    public abstract void clear();
}
