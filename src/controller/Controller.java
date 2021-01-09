package controller;

import bean.Record;
import dao.RecordDAO;
import iostream.Serialize_DeserializeRecord;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import random.RandomService;
import static dao.RecordDAO.arrPR;

public class Controller implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button listButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label messageLabel;
    @FXML
    private Label messageLabel2;
    @FXML
    private TextField addressField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private PasswordField passwordField2;

    private double xOffset;
    private double yOffset;

    public void listButtonOnAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../controller/showcontroller.fxml"));
        primaryStage.setTitle("Данные");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void resetButtonOnAction(ActionEvent event) {
        addressField.clear();
        loginField.clear();
        passwordField1.clear();
        passwordField2.clear();
        messageLabel.setText("");
        messageLabel2.setText("");
    }

    public void saveButtonOnAction(ActionEvent event) {
        if (addressField.getText().isEmpty() &&
                loginField.getText().isEmpty() &&
                passwordField1.getText().isEmpty() ||
                passwordField2.getText().isEmpty()) {
            messageLabel.setText("Некорректный ввод!");
        }
        String address = addressField.getText();
        String login = loginField.getText();
        String password;
        if (!passwordField1.getText().isEmpty()) {
            password = passwordField1.getText();
        } else {
            int tmp = Integer.parseInt(passwordField2.getText());
            StringBuffer sb = new StringBuffer(tmp);
            for (int i = 0; i < tmp; i++) {
                sb.append(RandomService.getRandomSymbol());
            }
            password = String.valueOf(sb);
        }
        Record r = new Record(address, login, password, new Date());
        RecordDAO.add(r);
        arrPR.add(r);
        Serialize_DeserializeRecord.writeRecord(arrPR);
        messageLabel.setText("Данные сохранены");
    }

    public void passwordField2KeyTyped(KeyEvent event) {
        passwordField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                passwordField2.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        messageLabel2.setText("Вводите только цифры!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}