package sl.test.bluebird;

import java.math.BigDecimal;

public class BlueBirdStockInfo {

    private BigDecimal no;

    private String code;
    private String brand;
    private String marketplace;

    private BigDecimal sales;
    private BigDecimal operatingProfit;
    private BigDecimal ordinaryProfit;
    private BigDecimal netIncome;
    private BigDecimal oneShareProfit;
    private BigDecimal oneShareDistribution;

    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal lastPrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code=code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand=brand;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace=marketplace;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales=sales;
    }

    public BigDecimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(BigDecimal operatingProfit) {
        this.operatingProfit=operatingProfit;
    }

    public BigDecimal getOrdinaryProfit() {
        return ordinaryProfit;
    }

    public void setOrdinaryProfit(BigDecimal ordinaryProfit) {
        this.ordinaryProfit=ordinaryProfit;
    }

    public BigDecimal getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(BigDecimal netIncome) {
        this.netIncome=netIncome;
    }

    public BigDecimal getOneShareProfit() {
        return oneShareProfit;
    }

    public void setOneShareProfit(BigDecimal oneShareProfit) {
        this.oneShareProfit=oneShareProfit;
    }

    public BigDecimal getOneShareDistribution() {
        return oneShareDistribution;
    }

    public void setOneShareDistribution(BigDecimal oneShareDistribution) {
        this.oneShareDistribution=oneShareDistribution;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open=open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high=high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low=low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close=close;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice=lastPrice;
    }

    public BigDecimal getNo() {
        return no;
    }

    public void setNo(BigDecimal no) {
        this.no=no;
    }
}
