package ua.com.alevel.messenger_nure.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.com.alevel.messenger_nure.MessengerApplication;
import ua.com.alevel.messenger_nure.facade.UserFacade;
import ua.com.alevel.messenger_nure.facade.impl.UserFacadeImpl;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;
import ua.com.alevel.messenger_nure.util.Security;

import java.io.IOException;

public class SignInController {

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Label labelWelcome;

    private final UserFacade userFacade;

    public SignInController() {
        this.userFacade = new UserFacadeImpl();
    }

    @FXML
    private void login() {
        buttonSignIn.setOnAction(event -> {
            User user = userFacade.findByLogin(tfUsername.getText());

            if (user != null &&
                    user.getPassword().equals(Security.md5Apache(tfPassword.getText()))) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MessengerApplication.class.getResource("mainPage.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle(user.getLogin());

                    //Transfer data from this class to MainPageController class
                    MainPageController mainPage = loader.getController();
                    mainPage.initialize(user.getLogin());

                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                labelWelcome.setText("ERROR, TRY AGAIN!");
            }
        });
    }

    public TextField getTfUsername() {
        return tfUsername;
    }
}
