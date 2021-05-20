package de.darthtuna.gw2.handelsposten.view;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import de.darthtuna.gw2.handelsposten.model.TableEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainPresenter implements Initializable
{
    @FXML
    private Button btAktualisieren;
    @FXML
    private TableView<TableEntry> tvEntries;
    @FXML
    private TableColumn<String, TableEntry> colName;
    @FXML
    private TableColumn<String, Integer> colVerkaufspreis;
    @FXML
    private TableColumn<String, Integer> colMarge;
    @FXML
    private TableColumn<String, BigDecimal> colMargeProzent;

    @Inject
    private MainService mainService;

    @Override
    public void initialize(final URL location, final ResourceBundle resources)
    {
        tvEntries.setItems(mainService.getEntryList());

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVerkaufspreis.setCellValueFactory(new PropertyValueFactory<>("minSaleUnitPrice"));
        colMarge.setCellValueFactory(new PropertyValueFactory<>("margin"));
        colMargeProzent.setCellValueFactory(new PropertyValueFactory<>("marginPercent"));

        btAktualisieren.setOnAction(e -> mainService.updateData());
    }

}
