package sl.test.bluebird;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import sl.common.util.Strings;

import java.text.DecimalFormat;
import java.text.Format;

public class CellFormatter <S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    private Format format;
    private String alignment;

    public CellFormatter(Format format, String alignment) {
        super();
        this.format = format;
        this.alignment = alignment;
    }

    public static CellFormatter create(Format format, String alignment){
        return new CellFormatter(format,alignment);
    }

    public static CellFormatter numberFormat(String pattern, String alignment){
        return new CellFormatter(new DecimalFormat(pattern),alignment);
    }

    public static CellFormatter numberFormat(String pattern){
        return new CellFormatter(new DecimalFormat(pattern),"top-right");
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {

        TableCell cell = new TableCell<S, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new Label(format.format(item)));
                }
            }
        };

        if(Strings.hasValue(alignment)){
            cell.setStyle("-fx-alignment: "+alignment+";-fx-padding: 0 5 0 0;");
        }

        return cell;
    }

}
