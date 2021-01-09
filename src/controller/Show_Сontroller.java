package controller;

import bean.Record;
import dao.RecordDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static dao.RecordDAO.SELECT;

public class Show_Сontroller implements Initializable {
    @FXML
    private Label labelErrorDB;
    @FXML
    private TableView<Record> tableRecord;
    @FXML
    private TableColumn<Record, String> col_address;
    @FXML
    private TableColumn<Record, String> col_login;
    @FXML
    private TableColumn<Record, String> col_password;
    @FXML
    private TableColumn<Record, Date> col_time;
    @FXML
    private Button exitButton;
    @FXML
    private Button showButton;

    ObservableList<Record> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exitButtonOnAction2(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void showButtonOnAction(ActionEvent event) {
        try {
            Connection con = RecordDAO.getConnection();
            ResultSet rs = con.createStatement().executeQuery(SELECT);
            while (rs.next()) {
                oblist.add(new Record(rs.getString("address"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getDate("time")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Show_Сontroller.class.getName()).log(Level.SEVERE, null, ex);
            labelErrorDB.setText("Не удается подключиться к базе данных!");
        }
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableRecord.setItems(oblist);
    }
}
