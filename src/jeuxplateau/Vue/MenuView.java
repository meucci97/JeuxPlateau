package jeuxplateau.Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuView   {
    private Stage primaryStage;
    private Scene menuScene;
    private GridPane root;

    public MenuView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root= new GridPane();

        initMenu();
        menuScene = new Scene(root, 300, 250);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Hello World!");
        primaryStage.show();
    }

    private void initMenu(){
        Button btnTetris = new Button();
        Button btnBlocus= new Button();
        Button btnPuzzle= new Button();
        btnTetris.setText("Tetris");
        btnBlocus.setText("Blocus");
        btnPuzzle.setText("Puzzle");

        btnTetris.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Tetris");
            }
        });

        btnBlocus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Blocus");
            }
        });

        btnPuzzle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Puzzle");
            }
        });
        GridPane grid= new GridPane();
        root.setAlignment(Pos.CENTER);
        root.add(btnTetris,0,2);
        root.add(btnBlocus,1,2);
        root.add(btnPuzzle,2,2);



    }


}
