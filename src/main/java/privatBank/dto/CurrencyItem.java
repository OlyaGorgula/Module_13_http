package privatBank.dto;

public class CurrencyItem {
/*
    {"ccy":"EUR",
    "base_ccy":"UAH",
    "buy":"44.20000",
    "sale":"45.20000"}
 */
    private CCY ccy;
    public CCY base_ccy;
    private float buy;
    private float sale;

    public CCY getCcy() {
        return ccy;
    }

    public void setCcy(CCY ccy) {
        this.ccy = ccy;
    }

    public CCY getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(CCY base_ccy) {
        this.base_ccy = base_ccy;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CurrencyItem{" +
                "ccy=" + ccy +
                ", base_ccy=" + base_ccy +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }

    public enum CCY{
        USD,
        EUR,
        UAH
    }
}
