package jeuxplateau.Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TetrisView {

    private Stage primaryStage;
    private Scene plateauxTetris;
    private Group root;
    private GridPane grille;
    private GridPane piece;

    private static int HAUTEUR_GRILLE = 20;
    private static int LARGEUR_GRILLE = 10;

    public TetrisView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new Group();

        // GridPane des bouttons Pause et Quitter
        GridPane btnGrid = new GridPane();
        btnGrid.setTranslateX(275);
        btnGrid.setTranslateY(100);
        btnGrid.setHgap(10);
        Button btnQuitter = new Button("Quitter");
        Button btnPause = new Button("Pause");
        btnGrid.add(btnPause, 0, 0);
        btnGrid.add(btnQuitter, 1, 0);

        grille = new GridPane();
        for (int ligne = 0; ligne < HAUTEUR_GRILLE; ligne++) {
            for (int colonne = 0; colonne < LARGEUR_GRILLE; colonne++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(25);
                rec.setHeight(25);
                rec.setFill(Color.LIGHTGRAY);
                rec.setStroke(Color.GRAY);
                GridPane.setRowIndex(rec, ligne);
                GridPane.setColumnIndex(rec, colonne);
                grille.getChildren().addAll(rec);
            }
        }

        this.root.getChildren().add(grille);
        this.root.getChildren().add(btnGrid);

        this.plateauxTetris = new Scene(root, 425, 525);

        primaryStage.setScene(plateauxTetris);
        primaryStage.setTitle("Tetris");
    }
}
