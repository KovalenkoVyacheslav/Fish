package VIEW;

import CONTROLLER.GameController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLStartController implements Initializable {

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXButton btnStart;

    @FXML
    private JFXTextField txtName;

    @FXML
    void OnActionExit(ActionEvent event) {

        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnActionStart(ActionEvent event) throws IOException {

        FXMLMainFormController fxmlMainFormController = new FXMLMainFormController();
        String name = txtName.getText().equals("") ? "Player" : txtName.getText();
        fxmlMainFormController.SetName(name);
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLMainForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("FishingGame");
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        OnActionExit(event);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
