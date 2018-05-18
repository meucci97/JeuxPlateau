package jeuxplateau.Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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
import jeuxplateau.Controlleur.TetrisControlleur;
import jeuxplateau.Modele.Case;
import jeuxplateau.Modele.Piece;
import jeuxplateau.Modele.Tetris;

import java.io.File;
import java.util.List;

public class TetrisView implements Observateur{

    private Stage primaryStage;
    private Scene plateauxTetris;
    private Group root;
    private GridPane grille;

    private static int HAUTEUR_GRILLE = 20;
    private static int LARGEUR_GRILLE = 10;

    private static int HAUTEUR_FENTRE = 525;
    private static int LARGEUR_FENETRE = 425;

    private MediaPlayer mediaPlayer;

    private Tetris tetris;
    private TetrisControlleur controlleur;

    public TetrisView(Stage primaryStage, Tetris tetris) {
        this.primaryStage = primaryStage;
        this.root = new Group();

        this.tetris = tetris;
        controlleur = new TetrisControlleur(tetris);
    }

    public void initialisationAll() {
        // Lancement de la musique de fond
        initialiserMusique();
        lancerMusique();
        initZoneProchainePiece();
        initGrille();

        initBouttons();
        initLabels();

        this.plateauxTetris = new Scene(root, LARGEUR_FENETRE, HAUTEUR_FENTRE);

        initClavier();

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

    private void initZoneProchainePiece() {
        // GridPane zone pièce
        Piece piece = tetris.getMesPieces().lastElement();
        GridPane pieceGrid = new GridPane();
        pieceGrid.setTranslateX(300);
        pieceGrid.setTranslateY(50);
        for (int ligne = 0; ligne < 4; ligne++) {
            for (int colonne = 0; colonne < 4; colonne++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(15);
                rec.setHeight(15);

                if (piece.getMatricePiece()[ligne][colonne] == 0) {
                    rec.setFill(Color.BLACK);
                } else {
                    rec.setFill(Color.web(piece.getCouleur()));
                }

                rec.setStroke(Color.GRAY);
                GridPane.setRowIndex(rec, ligne);
                GridPane.setColumnIndex(rec, colonne);
                pieceGrid.getChildren().addAll(rec);
            }
        }

        this.root.getChildren().add(pieceGrid);
    }

    private void initGrille() {
        grille = new GridPane();
        for (int ligne = 0; ligne < HAUTEUR_GRILLE; ligne++) {
            for (int colonne = 0; colonne < LARGEUR_GRILLE; colonne++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(25);
                rec.setHeight(25);
                rec.setFill(Color.web(tetris.getGrille().getCase(ligne, colonne).getCouleur()));
                rec.setStroke(Color.GRAY);
                GridPane.setRowIndex(rec, ligne);
                GridPane.setColumnIndex(rec, colonne);
                grille.getChildren().addAll(rec);
            }
        }

        this.root.getChildren().add(grille);
    }

    private void initBouttons() {
        // GridPane des bouttons Pause et Quitter
        GridPane btnGrid = new GridPane();
        btnGrid.setTranslateX(280);
        btnGrid.setTranslateY(250);
        btnGrid.setHgap(10);
        Button btnQuitter = new Button("Quitter");
        Button btnPause = new Button("Pause");
        btnGrid.add(btnPause, 0, 0);
        btnGrid.add(btnQuitter, 1, 0);

        this.root.getChildren().add(btnGrid);

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
    }

    private void initLabels() {
        // GridPane des labels Score, Niveau et Lignes
        GridPane lbGrid = new GridPane();
        lbGrid.setTranslateX(300);
        lbGrid.setTranslateY(175);
        Label lbScore = new Label("Score : ");
        Label lbNiveau = new Label("Niveau : ");
        Label lbLignes = new Label("Lignes : ");
        Label score = new Label(Integer.toString(tetris.getScore()));
        Label niveau = new Label(Integer.toString(tetris.getNiveau()));
        Label lignes = new Label(Integer.toString(tetris.getLignes()));
        lbGrid.add(lbScore, 0, 0);
        lbGrid.add(lbLignes, 0, 1);
        lbGrid.add(lbNiveau, 0, 2);
        lbGrid.add(score, 1, 0);
        lbGrid.add(lignes, 1, 1);
        lbGrid.add(niveau, 1, 2);

        this.root.getChildren().add(lbGrid);
    }

    private void initClavier() {
        root.requestFocus();
        this.plateauxTetris.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        System.out.println("down");
                        controlleur.clickDown(tetris.getMesPieces().firstElement());
                        break;
                    case RIGHT:
                        System.out.println("right");
                        controlleur.clickRight(tetris.getMesPieces().firstElement());
                        break;
                    case LEFT:
                        System.out.println("left");
                        controlleur.clickLeft(tetris.getMesPieces().firstElement());
                        break;
                    case SPACE:
                        System.out.println("space");
                        controlleur.borderCheck(tetris.getMesPieces().firstElement());
                        break;
                }
            }
        });
    }

    private void paintPiece() {
        Piece piece = tetris.getMesPieces().firstElement();
        int[][] matrice = piece.getMatricePiece();

        int positionDansGrilleI = 0;
        int positionDansGrilleJ = 0;

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if(matrice[i][j]!=0) {
                    if (i < piece.getPointOrientation()[0]) {
                        positionDansGrilleI = piece.getPositionX() - (i);
                    } else if (i == piece.getPointOrientation()[0]) {
                        positionDansGrilleI = piece.getPositionX();
                    } else {
                        positionDansGrilleI = piece.getPositionX() + (i- piece.getPointOrientation()[0]);
                    }

                    if (j < piece.getPointOrientation()[1]) {
                        positionDansGrilleJ = piece.getPositionY() - (j);
                    } else if (j == piece.getPointOrientation()[1]) {
                        positionDansGrilleJ = piece.getPositionY();
                    } else {
                        positionDansGrilleJ = piece.getPositionY() + (j- piece.getPointOrientation()[1]);
                    }

                    if (positionDansGrilleI >= 0) {
                        Rectangle rec = new Rectangle();
                        rec.setWidth(25);
                        rec.setHeight(25);
                        rec.setFill(Color.web(piece.getCouleur()));
                        rec.setStroke(Color.GRAY);
                        GridPane.setRowIndex(rec, positionDansGrilleI);
                        GridPane.setColumnIndex(rec, positionDansGrilleJ);
                        grille.getChildren().addAll(rec);
                    }
                }
            }
        }
    }

    public void startGame(){

        controlleur.startGame();
    }
    @Override
    public void update() {
        initGrille();
        initZoneProchainePiece();
        paintPiece();
        System.out.println(tetris.getMesPieces().firstElement().getPositionX());
        System.out.println(tetris.getMesPieces().firstElement().getPositionY());
    }
}
