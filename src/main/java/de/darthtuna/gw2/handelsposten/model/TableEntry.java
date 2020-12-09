package de.darthtuna.gw2.handelsposten.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableEntry
{
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty offerAvailability = new SimpleIntegerProperty();
    private final IntegerProperty saleAvailability = new SimpleIntegerProperty();
    private final IntegerProperty maxOfferPrice = new SimpleIntegerProperty();
    private final IntegerProperty minSaleUnitPrice = new SimpleIntegerProperty();
    private final IntegerProperty maxBuyPriceToBreakEven = new SimpleIntegerProperty();
    private final IntegerProperty margin = new SimpleIntegerProperty();

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

    public final IntegerProperty offerAvailabilityProperty()
    {
        return this.offerAvailability;
    }

    public final int getOfferAvailability()
    {
        return this.offerAvailabilityProperty().get();
    }

    public final void setOfferAvailability(final int offerAvailability)
    {
        this.offerAvailabilityProperty().set(offerAvailability);
    }

    public final IntegerProperty saleAvailabilityProperty()
    {
        return this.saleAvailability;
    }

    public final int getSaleAvailability()
    {
        return this.saleAvailabilityProperty().get();
    }

    public final void setSaleAvailability(final int saleAvailability)
    {
        this.saleAvailabilityProperty().set(saleAvailability);
    }

    public final IntegerProperty maxOfferPriceProperty()
    {
        return this.maxOfferPrice;
    }

    public final int getMaxOfferPrice()
    {
        return this.maxOfferPriceProperty().get();
    }

    public final void setMaxOfferPrice(final int maxOfferPrice)
    {
        this.maxOfferPriceProperty().set(maxOfferPrice);
    }

    public final IntegerProperty minSaleUnitPriceProperty()
    {
        return this.minSaleUnitPrice;
    }

    public final int getMinSaleUnitPrice()
    {
        return this.minSaleUnitPriceProperty().get();
    }

    public final void setMinSaleUnitPrice(final int minSaleUnitPrice)
    {
        this.minSaleUnitPriceProperty().set(minSaleUnitPrice);
        updateMaxBuyPriceToBreakEven();
    }

    private void updateMaxBuyPriceToBreakEven()
    {
        BigDecimal e = BigDecimal.valueOf(getMinSaleUnitPrice()).multiply(BigDecimal.valueOf(0.85d));
        maxBuyPriceToBreakEven.set(e.setScale(0, RoundingMode.UP).intValue());
        updateMargin();
    }

    public int getMaxBuyPriceToBreakEven()
    {
        return maxBuyPriceToBreakEven.get();
    }

    private void updateMargin()
    {
        margin.set(maxBuyPriceToBreakEven.get() - maxOfferPrice.get());
    }

    public int getMargin()
    {
        return margin.get();
    }

    public IntegerProperty marginProperty()
    {
        return margin;
    }
}
