package de.darthtuna.gw2.handelsposten.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.darthtuna.gw2.handelsposten.model.TableEntry;

public class CSVHelper
{
    private static final Logger logger = LogManager.getLogger(CSVHelper.class);

    private final List<TableEntry> entryList = new ArrayList<>();

    public void getFile()
    {
        entryList.clear();
        try (var inputStream = new URL("http://www.gw2spidy.com/api/v0.9/csv/all-items/all").openStream();
                var reader = new InputStreamReader(inputStream))
        {
            var parser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (var record : parser)
            {
                var entry = new TableEntry();
                entry.setName(record.get("name"));
                entryList.add(entry);
            }
        }
        catch (IOException e)
        {
            logger.catching(e);
        }
    }
}
