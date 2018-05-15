package jeuxplateau.Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class TetrisView {

    private Stage primaryStage;
    private Scene menuScene;
    private Group root;

    public TetrisView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new Group();

        Button btn = new Button();
        btn.setText("aaa");

        this.root.getChildren().add(btn);

        this.menuScene = new Scene(root, 800, 600);
        this.primaryStage.setScene(menuScene);
        this.primaryStage.setTitle("Tetris");
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
