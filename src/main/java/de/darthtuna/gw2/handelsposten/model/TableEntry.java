package de.darthtuna.gw2.handelsposten.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableEntry
{
    private final StringProperty name = new SimpleStringProperty();
    private int offerAvailability;
    private int saleAvailability;
    private final IntegerProperty maxOfferPrice = new SimpleIntegerProperty();
    private final IntegerProperty minSaleUnitPrice = new SimpleIntegerProperty();
    private final IntegerProperty maxBuyPriceToBreakEven = new SimpleIntegerProperty();
    private final IntegerProperty margin = new SimpleIntegerProperty();
    private final ObjectProperty<BigDecimal> marginPercent = new SimpleObjectProperty<>();

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

    public final int getOfferAvailability()
    {
        return offerAvailability;
    }

    public final void setOfferAvailability(final int offerAvailability)
    {
        this.offerAvailability = offerAvailability;
    }

    public final int getSaleAvailability()
    {
        return saleAvailability;
    }

    public final void setSaleAvailability(final int saleAvailability)
    {
        this.saleAvailability = saleAvailability;
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
        updateMarginPercent();
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
        updateMarginPercent();
    }

    public int getMargin()
    {
        return margin.get();
    }

    private void updateMarginPercent()
    {
        if (getMinSaleUnitPrice() != 0)
            setMarginPercent(BigDecimal.valueOf(getMargin())
                    .divide(BigDecimal.valueOf(getMinSaleUnitPrice()), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100)));
    }

    public IntegerProperty marginProperty()
    {
        return margin;
    }

    public final ObjectProperty<BigDecimal> marginPercentProperty()
    {
        return this.marginPercent;
    }

    public final BigDecimal getMarginPercent()
    {
        return this.marginPercentProperty().get();
    }

    public final void setMarginPercent(final BigDecimal marginPercent)
    {
        this.marginPercentProperty().set(marginPercent);
    }

}
