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
            var parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withQuote('%'));
            for (var record : parser)
            {
                int offerAvailability = Integer.parseInt(record.get("offer_availability"));
                int saleAvailability = Integer.parseInt(record.get("sale_availability"));
                if (offerAvailability < 10_000 || saleAvailability < 10_000)
                {
                    // Wenn ein Item kaum gehandelt wird, lohnt es sich nicht, diese aufzukaufen, weil kein interesse
                    // daran besteht.
                    continue;
                }
                var entry = new TableEntry();
                entry.setOfferAvailability(offerAvailability);
                entry.setSaleAvailability(saleAvailability);
                entry.setName(record.get("name"));
                entry.setMaxOfferPrice(Integer.parseInt(record.get("max_offer_unit_price")));
                entry.setMinSaleUnitPrice(Integer.parseInt(record.get("min_sale_unit_price")));
                if (entry.getMargin() < 1)
                    continue;
                entryList.add(entry);
            }
        }
        catch (IOException e)
        {
            logger.catching(e);
        }
        logger.info("Insgesamt {} brauchbare items gefunden.", entryList.size());
    }
}
