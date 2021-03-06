

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainUI extends Application implements EventHandler<ActionEvent>{
    private Button insert;
    private Button search;
    private Button update;
    private Button delete;
    private Button exportJson;
    private Button exit;
    private Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception{

        VBox layout = new VBox(20);
        this.window = primaryStage;
        this.window.setTitle("MyAddressBook");
        Label welcome = new Label("Welcome to the Address Book");

        this.insert = new Button("Insert");
        this.search = new Button("Search");
        this.update = new Button("Update");
        this.delete = new Button("Delete");
        this.exportJson = new Button("Export JSON");
        this.exit = new Button("Exit");

        this.insert.setOnAction(this);
        this.search.setOnAction(this);
        this.update.setOnAction(this);
        this.delete.setOnAction(this);
        this.exportJson.setOnAction(this);
        this.exit.setOnAction(this);


        layout.getChildren().addAll(welcome, this.insert, this.search, this.update, this.delete, this.exportJson, this.exit);
        layout.setAlignment(Pos.CENTER);
        this.window.setScene(new Scene(layout, 280, 380));
        primaryStage.show();


        //Not allowing user to close program from the red cross button.
        //And giving user choice b/w, to close or not.
        this.window.setOnCloseRequest(e -> {
            e.consume();
           closeProgram();
        });


    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == this.exit){
            closeProgram();
        } else if (event.getSource() == this.insert){
            UpdateAndInsertUI updateAndInsertUI = new UpdateAndInsertUI("Insert");
            updateAndInsertUI.updateInsertUI();
        } else if (event.getSource() == this.search) {
            UpdateSearchDeleteUI updateSearchDeleteUI = new UpdateSearchDeleteUI("Search - AddressBook", "Enter ID, first name or phone number to search", "Search");
            updateSearchDeleteUI.generateUI();
        } else if (event.getSource() == this.delete) {
            UpdateSearchDeleteUI updateSearchDeleteUI = new UpdateSearchDeleteUI("Delete - AddressBook", "Enter ID to delete entry", "Delete");
            updateSearchDeleteUI.generateUI();
        } else if (event.getSource() == update) {
            UpdateSearchDeleteUI updateSearchDeleteUI = new UpdateSearchDeleteUI("Update - AddressBook", "Enter ID  to update", "Update");
            updateSearchDeleteUI.generateUI();
        } else if (event.getSource() == exportJson) {
            AddressBook addressBook = new AddressBook();
            addressBook.writeJSON();
        }

    }

    public void closeProgram(){
        AlertBox alertBox = new AlertBox("close - AddressBook", "Are you sure you want to close AddressBook?");
        Boolean answer = alertBox.alert();

        if (answer){
            this.window.close();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
