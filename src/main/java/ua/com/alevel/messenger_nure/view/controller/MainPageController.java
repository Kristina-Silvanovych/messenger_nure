package ua.com.alevel.messenger_nure.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ua.com.alevel.messenger_nure.facade.MessageFacade;
import ua.com.alevel.messenger_nure.facade.UserFacade;
import ua.com.alevel.messenger_nure.facade.impl.MessageFacadeImpl;
import ua.com.alevel.messenger_nure.facade.impl.UserFacadeImpl;
import ua.com.alevel.messenger_nure.persistence.entity.message.Message;

import java.util.List;
import java.util.Objects;

public class MainPageController {

    @FXML
    private ScrollPane scrollPeople;

    @FXML
    private ScrollPane scrollMessage;

    @FXML
    private Button sendMess;

    @FXML
    private TextArea taMessage;

    @FXML
    private Button buttonFind;

    @FXML
    private TextField tfFind;

    @FXML
    private Label who;

    @FXML
    private Button updatePage;

    private String loginAuthorized;

    private String sendTo;

    private final VBox vBoxForPeople;

    private final VBox vBoxForMessage;

    private final UserFacade userFacade;

    private final MessageFacade messageFacade;

    public MainPageController() {
        this.userFacade = new UserFacadeImpl();
        this.messageFacade = new MessageFacadeImpl();
        this.vBoxForPeople = new VBox();
        this.vBoxForMessage = new VBox();
    }

    @FXML
    public void initialize(String loginAuthorized) {
        this.loginAuthorized = loginAuthorized;
        updateUsersPane();
    }

    @FXML
    private void findUser() {
        buttonFind.setOnAction(event -> open(tfFind.getText()));
    }

    @FXML
    private void updateUsers() {
        updatePage.setOnAction(event -> {            vBoxForPeople.getChildren().clear();
            updateUsersPane();
        });
    }

    private AnchorPane createAnchorPaneForPeople(String name) {
        Label nameLabel = new Label(name);
        nameLabel.setFont(new Font(15));
        nameLabel.setId("sendTo");

        AnchorPane anchorPane = new AnchorPane(nameLabel);

        anchorPane.setStyle("-fx-border-color: black");
        AnchorPane.setTopAnchor(nameLabel, 0.0);
        anchorPane.setPrefSize(250, 60);
        anchorPane.setOnMouseClicked(e -> open(name));
        return anchorPane;
    }

    private void updateMessagePane() {
        List<Message> messageList = messageFacade.findMessage(loginAuthorized, sendTo);
        for (Message message : messageList) {
            vBoxForMessage.getChildren().add(createAnchorPaneForMessage(message));
        }
        scrollMessage.setContent(vBoxForMessage);
    }

    private void updateUsersPane() {
        List<String> userList = userFacade.findAllUserCorrespondence(loginAuthorized);
        for (String s : userList) {
            vBoxForPeople.getChildren().add(createAnchorPaneForPeople(s));
        }
        scrollPeople.setContent(vBoxForPeople);
    }

    private AnchorPane createAnchorPaneForMessage(Message message) {
        if (message != null && message.getId() != null) {
            Label whoSender = new Label(message.getUserLogin());
            Label contextMessage = new Label(message.getContext());
            contextMessage.setId(message.getId().toString());
            whoSender.setFont(new Font(15));
            contextMessage.setFont(new Font(18));

            TextField textField = new TextField();
            textField.setVisible(false);

            Button updateMessage = new Button();
            Button deleteMessage = new Button();

            updateMessage.setVisible(false);
            deleteMessage.setVisible(false);
            AnchorPane anchorPane = new AnchorPane(contextMessage, whoSender, deleteMessage, textField, updateMessage);

            AnchorPane.setTopAnchor(whoSender, 0.0);
            AnchorPane.setBottomAnchor(contextMessage, 0.0);
            AnchorPane.setRightAnchor(deleteMessage, 0.0);
            AnchorPane.setRightAnchor(updateMessage, 50.0);
            AnchorPane.setBottomAnchor(textField, 0.0);

            anchorPane.setPrefSize(611, 40);

            if (message.getUserLogin().equals(loginAuthorized)) {
                updateMessage.setVisible(true);
                deleteMessage.setVisible(true);
                whoSender.setText("you:");
                anchorPane.setStyle("-fx-background-color: #77a4bb");

                deleteMessage.setText("delete");
                deleteMessage.setOnAction(event -> {
                    messageFacade.delete(Long.valueOf(contextMessage.getId()));
                    open(sendTo);
                });

                updateMessage.setText("update");
                updateMessage.setOnAction(event -> {
                    contextMessage.setVisible(false);
                    deleteMessage.setVisible(false);
                    updateMessage.setVisible(false);
                    textField.setVisible(true);
                    textField.setText(message.getContext());
                    textField.setOnKeyPressed(keyEvent -> {
                        if (keyEvent.getCode() == KeyCode.ENTER) {
                            message.setContext(textField.getText());
                            messageFacade.update(message);
                            open(sendTo);
                        }
                    });
                });
            } else
                anchorPane.setStyle("-fx-background-color: #ffffff");
            return anchorPane;
        }
        return null;
    }

    @FXML
    private void open(String sendTo) {
        this.sendTo = sendTo;
        who.setText("You correspond with:  " + sendTo);
        vBoxForMessage.getChildren().clear();
        updateMessagePane();
    }


    @FXML
    private void sendMessage() {
        sendMess.setOnAction(event -> {
            if (!Objects.equals(taMessage.getText().trim(), "")) {
                Message message = new Message();
                message.setContext(taMessage.getText());
                message.setRecipient(sendTo);
                message.setUserLogin(loginAuthorized);
                taMessage.setText("");
                messageFacade.create(message);
                open(sendTo);
            }
        });
    }
}
