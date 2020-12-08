package de.darthtuna.gw2.handelsposten.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableEntry
{
    private final StringProperty name = new SimpleStringProperty();

    public final StringProperty nameProperty()
    {
        return this.name;
    }

    public final String getName()
    {
        return this.nameProperty().get();
    }

    public final void setName(final String name)
    {
        this.nameProperty().set(name);
    }

}
