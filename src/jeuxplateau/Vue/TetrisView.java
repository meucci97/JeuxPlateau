package jeuxplateau.Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class TetrisView {

    private Stage primaryStage;
    private Scene plateauxTetris;
    private Group root;

    public TetrisView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new Group();

        Button btn = new Button();
        btn.setText("aaa");

        this.root.getChildren().add(btn);

        this.plateauxTetris = new Scene(root, 800, 600);
        primaryStage.setScene(plateauxTetris);


    }
}
