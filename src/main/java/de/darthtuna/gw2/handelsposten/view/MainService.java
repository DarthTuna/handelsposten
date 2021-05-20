package de.darthtuna.gw2.handelsposten.view;

import com.airhacks.afterburner.injection.Injector;

import de.darthtuna.gw2.handelsposten.model.TableEntry;
import de.darthtuna.gw2.handelsposten.util.CSVHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainService
{
    private final ObservableList<TableEntry> entryList = FXCollections.observableArrayList();

    public ObservableList<TableEntry> getEntryList()
    {
        return entryList;
    }

    public void updateData()
    {
        var csvHelper = Injector.instantiateModelOrService(CSVHelper.class);
        entryList.setAll(csvHelper.getFile());
    }
}
