package sl.test.bluebird;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class BlueBirdViewModel {

    private StringProperty error=new SimpleStringProperty();

    private StringProperty statusLeft=new SimpleStringProperty();

    private StringProperty statusRight=new SimpleStringProperty();

    private ObservableList<BlueBirdStockInfo> stocks=FXCollections.observableArrayList();

    public BlueBirdViewModel() {
        createData();
    }

    private void createData() {
        //stocks = FXCollections.observableArrayList();

        BlueBirdStockInfo osakaKoki=new BlueBirdStockInfo();
        osakaKoki.setNo(new BigDecimal(1));
        osakaKoki.setCode("3173");
        osakaKoki.setBrand("大阪工機");
        osakaKoki.setMarketplace("東１");

        osakaKoki.setLastPrice(new BigDecimal(1200));

        osakaKoki.setLow(new BigDecimal(1000));
        osakaKoki.setHigh(new BigDecimal(1500));


        BlueBirdStockInfo papyles=new BlueBirdStockInfo();
        papyles.setNo(new BigDecimal(2));
        papyles.setCode("3641");
        papyles.setBrand("パピレス");
        papyles.setMarketplace("ＪＳ");
        papyles.setLastPrice(new BigDecimal(2000));

        papyles.setLow(new BigDecimal(900));
        papyles.setHigh(new BigDecimal(2100));

        BlueBirdStockInfo macromill=new BlueBirdStockInfo();
        macromill.setNo(new BigDecimal(3));
        macromill.setCode("3978");
        macromill.setBrand("マクロミル");
        macromill.setMarketplace("東１");
        macromill.setLastPrice(new BigDecimal(1600));
        macromill.setLow(new BigDecimal(500));
        macromill.setHigh(new BigDecimal(2000));

        stocks.add(osakaKoki);
        stocks.add(papyles);
        stocks.add(macromill);

        statusLeft.set("Hello");

    }

    public StringProperty getError() {
        return error;
    }

    public StringProperty statusLeftProperty() {
        return statusLeft;
    }

    public StringProperty statusRightProperty() {
        return statusRight;
    }

    public ObservableList<BlueBirdStockInfo> getStocks() {
        return stocks;
    }

    public void save(){
        for (BlueBirdStockInfo stock:stocks) {
            System.out.println(stock.getCode()+"="+stock.getBrand()+" "+stock.getMarketplace());
        }
    }

    public void add(){
        BlueBirdStockInfo info=new BlueBirdStockInfo();
        info.setNo(new BigDecimal(stocks.size()+1));
        stocks.add(info);
    }

    public void updateStock() {
    }
}
