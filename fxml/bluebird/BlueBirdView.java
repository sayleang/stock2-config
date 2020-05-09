package sl.test.bluebird;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.*;
import jodd.bean.BeanUtil;
import sl.common.util.Strings;
import sl.stock.config.BlueBirdSetting;
import sl.stock.model.ColumnInfo;
import sl.stock.model.ControlInfo;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BlueBirdView implements Initializable {
    @FXML
    private Label statusLeft;

    @FXML
    private Label statusRight;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdateStock;

    @FXML
    private TableView<BlueBirdStockInfo> tableView;

    private BlueBirdViewModel viewModel;

    private Map<String, String[]> map=new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize...");
        String[] list=new String[5];
        list[0]="東証一部";
        list[1]="東証二部";
        list[2]="JASDAQ";
        list[3]="東証マザーズ";
        list[4]="その他上場";
        map.put("marketCombo", list);

        viewModel=new BlueBirdViewModel();

        statusLeft.textProperty().bind(viewModel.statusLeftProperty());
        statusRight.textProperty().bind(viewModel.statusRightProperty());

        addColumns();

        tableView.setItems(viewModel.getStocks());
    }

    private TableColumn createTableColumn(ColumnInfo c) {
        TableColumn col=new TableColumn(c.getText());

        if (Strings.hasValue(c.getMinWidth()) && Integer.valueOf(c.getMinWidth()) > 0) {
            col.setMinWidth(Double.valueOf(c.getMinWidth()));
        }

        if (Strings.hasValue(c.getMaxWidth()) && Integer.valueOf(c.getMaxWidth()) > 0) {
            col.setMaxWidth(Double.valueOf(c.getMaxWidth()));
        }

        if (Strings.hasValue(c.getPrefWidth()) && Integer.valueOf(c.getPrefWidth()) > 0) {
            col.setPrefWidth(Double.valueOf(c.getPrefWidth()));
        }

        if ("false".equalsIgnoreCase(c.getVisible())) {
            col.setVisible(false);
        }

        if ("true".equalsIgnoreCase(c.getEditable())) {
            col.setEditable(true);
        }

        if ("false".equalsIgnoreCase(c.getResizable())) {
            col.setResizable(false);
        }

        if ("true".equalsIgnoreCase(c.getSortable())) {
            if ("desc".equalsIgnoreCase(c.getSortType())) {
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }

        col.setCellValueFactory(new PropertyValueFactory<>(c.getProperty()));

        ControlInfo ctr=c.getControl();
        if (ctr != null) {

            if ("ComboBox".equalsIgnoreCase(ctr.getType())) {
                col.setEditable(true);
                col.setCellFactory(ComboBoxTableCell.forTableColumn(map.get(ctr.getData())));
            } else if ("CheckBox".equalsIgnoreCase(ctr.getType())) {
                col.setEditable(true);
                col.setCellFactory(CheckBoxTableCell.forTableColumn(col));
            } else if ("ChoiceBox".equalsIgnoreCase(ctr.getType())) {
                col.setEditable(true);
                col.setCellFactory(ChoiceBoxTableCell.forTableColumn(map.get(ctr.getData())));
            } else if ("ProgressBar".equalsIgnoreCase(ctr.getType())) {

            } else {

            }

        } else {

            if ("string".equalsIgnoreCase(c.getType())) {
                col.setCellFactory(TextFieldTableCell.forTableColumn());

            } else if ("number".equalsIgnoreCase(c.getType())) {

                String alignment=Strings.hasValue(c.getAlignment()) ? c.getAlignment() : "top-right";
                String format=Strings.hasValue(c.getFormat()) ? c.getFormat() : "#,##0";

                col.setCellFactory(CellFormatter.numberFormat(format, alignment));

            } else {

            }

        }


        if (col.isEditable()) {
            col.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<BlueBirdStockInfo, String>>) event -> {
                BeanUtil.pojo.setProperty(event.getTableView().getItems().get(event.getTablePosition().getRow()), c.getProperty(), event.getNewValue());
            });
        }

        return col;
    }

    private void addColumns() {
        tableView.getColumns().clear();
        tableView.setEditable(true);

        try {
            BlueBirdSetting settings=BlueBirdSetting.load("C:\\saywork\\My Documents\\株・証券\\dev\\bluebird.json.txt");

            for (ColumnInfo c : settings.getColumns()) {
//                TableColumn col=createTableColumn(c);

//                TableColumn col=new TableColumn(c.getText());
//
//                if(Strings.hasValue(c.getMinWidth()) && Integer.valueOf(c.getMinWidth())>0){
//                    col.setMinWidth(Double.valueOf(c.getMinWidth()));
//                }
//
//                col.setCellValueFactory(new PropertyValueFactory<>(c.getProperty()));
//                if("number".equalsIgnoreCase(c.getType())){
//                    col.setCellValueFactory(new PropertyValueFactory<>("no"));
//                }
//                if("true".equalsIgnoreCase(c.getEditable())){
//                    col.setEditable(true);
//                    col.setCellFactory(TextFieldTableCell.forTableColumn());
//                    col.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<BlueBirdStockInfo, String>>) event -> {
//                        BeanUtil.pojo.setProperty(event.getTableView().getItems().get(event.getTablePosition().getRow()), c.getProperty(), event.getNewValue());
//                    });
//                }

                TableColumn col=null;
                if (c.getNested() != null && !c.getNested().isEmpty()) {

                    col=new TableColumn(c.getText());

                    for (ColumnInfo children : c.getNested()) {
                        col.getColumns().add(createTableColumn(children));
                    }

                } else {
                    col=createTableColumn(c);
                }

                tableView.getColumns().add(col);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        TableColumn col0=new TableColumn("No");
        col0.setMinWidth(60);
        col0.setCellValueFactory(new PropertyValueFactory<>("no"));
        col0.setCellValueFactory(new PropertyValueFactory<>("no"));

        TableColumn col1=new TableColumn("コード");
        col1.setMinWidth(60);
        col1.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn col2=new TableColumn("銘柄名");
        col2.setMinWidth(100);
        col2.setEditable(true);
        col2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        col2.setCellFactory(TextFieldTableCell.forTableColumn());

        col2.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<BlueBirdStockInfo, String>>) event -> {
            BeanUtil.pojo.setProperty(event.getTableView().getItems().get(event.getTablePosition().getRow()), "brand", event.getNewValue());
        });

        TableColumn col3=new TableColumn("市場");
        col3.setMinWidth(80);
        col3.setCellFactory(ComboBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));
        col3.setCellValueFactory(new PropertyValueFactory<>("marketplace"));

        TableColumn col4=new TableColumn("株価");
        col4.setMinWidth(80);
        col4.setCellValueFactory(new PropertyValueFactory<>("lastPrice"));
        col4.setCellFactory(CellFormatter.numberFormat("#,##0", "top-right"));

        TableColumn emailCol=new TableColumn("Email");

        TableColumn firstEmailCol=new TableColumn("Primary");
        firstEmailCol.setCellValueFactory(new PropertyValueFactory<>("low"));
        firstEmailCol.setCellFactory(CellFormatter.create(new DecimalFormat("\\ #,##0"), "top-right"));


        TableColumn secondEmailCol=new TableColumn("Secondary");
        secondEmailCol.setCellValueFactory(new PropertyValueFactory<>("high"));
        secondEmailCol.setCellFactory(CellFormatter.create(new DecimalFormat("\\ #,##0"), "top-right"));

        emailCol.getColumns().addAll(firstEmailCol, secondEmailCol);

        TableColumn col5=new TableColumn("Choice");
        col5.setCellFactory(ChoiceBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));

        TableColumn col6=new TableColumn("Check");
        col6.setCellFactory(CheckBoxTableCell.forTableColumn(col6));

        tableView.getColumns().addAll(col0, col1, col2, col3, col4, emailCol, col5, col6);
        */

        TableColumn c1=new TableColumn("CheckBox");
        c1.setCellFactory(CheckBoxTableCell.forTableColumn(c1));
        tableView.getColumns().add(c1);

        TableColumn c2=new TableColumn("ChoiceBox");
        c2.setCellFactory(ChoiceBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));
        tableView.getColumns().add(c2);

        TableColumn c3=new TableColumn("ComboBox");
        c3.setCellFactory(ComboBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));
        tableView.getColumns().add(c3);

        TableColumn c4=new TableColumn("ProgressBar");
        c4.setCellFactory(ProgressBarTableCell.forTableColumn());
        tableView.getColumns().add(c4);

        TableColumn c5=new TableColumn("市場");
        c5.setMinWidth(100);
        c5.setCellValueFactory(new PropertyValueFactory<>("marketplace"));
        c5.setCellFactory(ComboBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));

        tableView.getColumns().add(c5);
    }

    public void addAction(ActionEvent actionEvent) {
        viewModel.add();
    }


    public void saveAction(ActionEvent actionEvent) {
        viewModel.save();
    }

    public void updateStockAction(ActionEvent actionEvent) {
        viewModel.updateStock();
    }
}
