package jeuxplateau.Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jeuxplateau.Modele.Tetris;

import java.io.File;

public class TetrisView {

    private Stage primaryStage;
    private Scene plateauxTetris;
    private Group root;
    private GridPane grille;

    private static int HAUTEUR_GRILLE = 20;
    private static int LARGEUR_GRILLE = 10;

    private MediaPlayer mediaPlayer;

    private Tetris tetris;

    public TetrisView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new Group();

        Tetris monTetris = new Tetris(10,20);

        // Lancement de la musique de fond
        initialiserMusique();
        lancerMusique();

        // GridPane zone pièce
        GridPane pieceGrid = new GridPane();
        pieceGrid.setTranslateX(300);
        pieceGrid.setTranslateY(50);
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(15);
                rec.setHeight(15);
                rec.setFill(Color.LIGHTGRAY);
                rec.setStroke(Color.GRAY);
                GridPane.setRowIndex(rec, ligne);
                GridPane.setColumnIndex(rec, colonne);
                pieceGrid.getChildren().addAll(rec);
            }
        }

        // GridPane des bouttons Pause et Quitter
        GridPane btnGrid = new GridPane();
        btnGrid.setTranslateX(280);
        btnGrid.setTranslateY(250);
        btnGrid.setHgap(10);
        Button btnQuitter = new Button("Quitter");
        Button btnPause = new Button("Pause");
        btnGrid.add(btnPause, 0, 0);
        btnGrid.add(btnQuitter, 1, 0);

        // GridPane des labels Score, Niveau et Lignes
        GridPane lbGrid = new GridPane();
        lbGrid.setTranslateX(300);
        lbGrid.setTranslateY(175);
        Label lbScore = new Label("Score : ");
        Label lbNiveau = new Label("Niveau : ");
        Label lbLignes = new Label("Lignes : ");
        Label score = new Label(Integer.toString(monTetris.getScore()));
        Label niveau = new Label(Integer.toString(monTetris.getNiveau()));
        Label lignes = new Label(Integer.toString(monTetris.getLignes()));
        lbGrid.add(lbScore, 0, 0);
        lbGrid.add(lbLignes, 0, 1);
        lbGrid.add(lbNiveau, 0, 2);
        lbGrid.add(score, 1, 0);
        lbGrid.add(lignes, 1, 1);
        lbGrid.add(niveau, 1, 2);

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
        this.root.getChildren().add(lbGrid);
        this.root.getChildren().add(pieceGrid);

        this.plateauxTetris = new Scene(root, 425, 525);

        root.requestFocus();
        this.plateauxTetris.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        System.out.println("down");
                        break;
                    case RIGHT:
                        System.out.println("right");
                        break;
                    case LEFT:
                        System.out.println("left");
                        break;
                    case SPACE:
                        System.out.println("space");
                        break;
                }
            }
        });

        btnQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Quitter");
            }
        });

        btnPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pause");
                arreterMusique();
            }
        });

        primaryStage.setScene(plateauxTetris);
        primaryStage.setTitle("Tetris");
    }

    private void initialiserMusique() {
        String musicFile = "tetris.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void lancerMusique() {
        mediaPlayer.play();
    }

    private void arreterMusique() {
        mediaPlayer.pause();
    }
}
