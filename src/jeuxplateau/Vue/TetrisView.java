package jeuxplateau.Vue;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jeuxplateau.Controlleur.TetrisControlleur;
import jeuxplateau.Modele.Piece;
import jeuxplateau.Modele.Tetris;

import java.io.File;
import java.util.Optional;

import static javafx.scene.paint.Color.BLACK;

public class TetrisView implements Observateur {
    private TetrisView vuTetris = this;
    private Stage primaryStage;
    private Scene plateauxTetris;
    private Group root;
    private StackPane motherRoot;
    private GridPane grille;
    private MenuView menuView;
    private GridPane lbGrid;

    private static int HAUTEUR_GRILLE = 20;
    private static int LARGEUR_GRILLE = 10;

    private static int HAUTEUR_FENTRE = 525;
    private static int LARGEUR_FENETRE = 425;

    private MediaPlayer mediaPlayer;

    private Tetris tetris;
    private TetrisControlleur controlleur;

    PauseTransition wait;

    public TetrisView(Stage primaryStage, Tetris tetris) {
        this.primaryStage = primaryStage;
        this.root = new Group();
        this.motherRoot = new StackPane();
        this.tetris = tetris;
        controlleur = new TetrisControlleur(tetris);
    }

    public void initialisationAll() {
        BackgroundImage myBI= new BackgroundImage(new Image("file:background2.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        motherRoot.setBackground(new Background(myBI));
        // Lancement de la musique de fond
        initialiserMusique();
        lancerMusique();
        initZoneProchainePiece();
        initGrille();

        initBouttons();
        initLabels();
        paintPiece();
        initTimer();
        this.motherRoot.getChildren().add(root);
        this.plateauxTetris = new Scene(motherRoot, LARGEUR_FENETRE, HAUTEUR_FENTRE/*, Color.web("#323232")*/);
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

    private void muteMusique() {
        mediaPlayer.pause();
    }

    private void arreterMusique() {
        mediaPlayer.stop();
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
                    rec.setFill(BLACK);
                } else {
                    rec.setFill(Color.web(piece.getCouleur()));
                }

                rec.setStroke(Color.GRAY);
                pieceGrid.setRowIndex(rec, ligne);
                pieceGrid.setColumnIndex(rec, colonne);
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
                rec.setId("Rec" + (LARGEUR_GRILLE * ligne) + colonne);
                grille.add(rec, colonne, ligne);
            }
        }
        for (int i = 0; i < 4; i++) {
            Rectangle rec = new Rectangle();
            rec.setWidth(25);
            rec.setHeight(25);
            rec.setOpacity(0);
            rec.setStroke(Color.GRAY);
            rec.setId("P" + i);
            grille.add(rec, 0, 0);
        }
        this.root.getChildren().add(grille);
    }

    private void updteGrille() {
        for (int ligne = 0; ligne < HAUTEUR_GRILLE; ligne++) {
            for (int colonne = 0; colonne < LARGEUR_GRILLE; colonne++) {
                ((Rectangle) grille.lookup("#Rec" + (LARGEUR_GRILLE * ligne) + colonne)).setFill(Color.web(tetris.getGrille().getCase(ligne, colonne).getCouleur()));
            }
        }
    }

    private void initBouttons() {
        // GridPane des bouttons Pause et Quitter
        GridPane btnGrid = new GridPane();
        btnGrid.setTranslateX(280);
        btnGrid.setTranslateY(250);
        btnGrid.setHgap(10);
        Button btnQuitter = new Button("Quitter");
        //Button btnPause = new Button("Pause");
        Button btnReset = new Button("Reset");
        //btnGrid.add(btnPause, 0, 0);
        btnGrid.add(btnReset, 0, 0);
        btnGrid.add(btnQuitter, 1, 0);

        this.root.getChildren().add(btnGrid);

        btnQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Quitter");
                menuView = new MenuView(primaryStage);
                stopGame();
                System.out.println("Menu");
            }
        });

/*        btnPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pause");
                muteMusique();
            }
        });
        */
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Reset");
                tetris = new Tetris(LARGEUR_GRILLE, HAUTEUR_GRILLE);
                tetris.addObservateur(vuTetris);
                controlleur.setMonTetris(tetris);
                controlleur.startGame();
                updteGrille();
                initTimer();
                initZoneProchainePiece();
                paintPiece();
                updateLabels();
                System.out.println(tetris.getMesPieces().size());
                initClavier();
            }
        });
    }

    private void initLabels() {
        // GridPane des labels Score, Niveau et Lignes
        lbGrid = new GridPane();
        lbGrid.setTranslateX(300);
        lbGrid.setTranslateY(175);
        Label lbScore = new Label("Score : ");
        lbScore.setTextFill(Color.web("#cd853f"));
        Label lbNiveau = new Label("Niveau : ");
        lbNiveau.setTextFill(Color.web("#cd853f"));
        Label lbLignes = new Label("Lignes : ");
        lbLignes.setTextFill(Color.web("#cd853f"));

        Label score = new Label(Integer.toString(tetris.getScore()));
        score.setTextFill(Color.web("#cd853f"));
        Label niveau = new Label(Integer.toString(tetris.getNiveau()));
        niveau.setTextFill(Color.web("#cd853f"));
        Label lignes = new Label(Integer.toString(tetris.getLignes()));
        lignes.setTextFill(Color.web("#cd853f"));
        lbGrid.add(lbScore, 0, 0);
        lbGrid.add(lbLignes, 0, 1);
        lbGrid.add(lbNiveau, 0, 2);
        lbGrid.add(score, 1, 0);
        lbGrid.add(lignes, 1, 1);
        lbGrid.add(niveau, 1, 2);
        this.root.getChildren().add(lbGrid);
    }

    private void updateLabels() {
        lbGrid.getChildren().clear();
        Label lbScore = new Label("Score : ");
        Label lbNiveau = new Label("Niveau : ");
        Label lbLignes = new Label("Lignes : ");
        Label score = new Label(Integer.toString(tetris.getScore()));
        Label niveau = new Label(Integer.toString(tetris.getNiveau()));
        Label lignes = new Label(Integer.toString(tetris.getLignes()));

        lbScore.setTextFill(Color.web("#cd853f"));
        lbNiveau.setTextFill(Color.web("#cd853f"));
        lbLignes.setTextFill(Color.web("#cd853f"));
        score.setTextFill(Color.web("#cd853f"));
        niveau.setTextFill(Color.web("#cd853f"));
        lignes.setTextFill(Color.web("#cd853f"));

        lbGrid.add(lbScore, 0, 0);
        lbGrid.add(lbLignes, 0, 1);
        lbGrid.add(lbNiveau, 0, 2);
        lbGrid.add(score, 1, 0);
        lbGrid.add(lignes, 1, 1);
        lbGrid.add(niveau, 1, 2);

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
                    case UP:
                        System.out.println("up");
                        controlleur.clickEspace(tetris.getMesPieces().firstElement());
                        break;
                }
            }
        });
    }

    public void initTimer() {
        wait = new PauseTransition(Duration.millis(1000/(tetris.getNiveau() + 1)));
        wait.setOnFinished((e) -> {
            controlleur.clickDown(tetris.getMesPieces().firstElement());
            wait.playFromStart();
        });
        wait.play();
    }

    public void updateTimer() {
        wait.setDuration( new Duration(1000/(tetris.getNiveau() + 1)));
    }

    private void paintPiece() {
        for (int i = 0; i < 4; i++) {
            this.grille.getChildren().remove(grille.lookup("#P" + i));
        }

        int idCasePiece = 0;
        Piece piece = tetris.getMesPieces().firstElement();
        int[][] matrice = piece.getMatricePiece();
        int positionDansGrilleI = 0;
        int positionDansGrilleJ = 0;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] != 0) {
                    positionDansGrilleI = controlleur.getPositionDansGrilleI(piece, i);
                    positionDansGrilleJ = controlleur.getPositionDansGrilleJ(piece, j);

                    if (positionDansGrilleI >= 0 && positionDansGrilleJ >= 0) {
                        Rectangle rec = new Rectangle();
                        rec.setWidth(25);
                        rec.setHeight(25);
                        rec.setFill(Color.web(piece.getCouleur()));
                        rec.setStroke(Color.GRAY);
                        rec.setId("P" + idCasePiece);
                        idCasePiece++;
                        grille.add(rec, positionDansGrilleJ, positionDansGrilleI);
                    }
                }
            }
        }

    }

    public void startGame() {
        controlleur.startGame();
    }

    public void stopGame() {
        wait.stop();
        arreterMusique();
    }

    @Override
    public void update() {
        if (tetris.getGameOver()) {
            wait.stop();
            System.out.println("Game over");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GAME OVER ");
            alert.setHeaderText("Voulez vous recommencer?");
            alert.setContentText("Votre score est de: " + tetris.getScore());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.tetris = new Tetris(LARGEUR_GRILLE, HAUTEUR_GRILLE);
                this.tetris.addObservateur(this);
                this.controlleur.setMonTetris(tetris);
                controlleur.startGame();
                updteGrille();
                initTimer();
                initZoneProchainePiece();
                paintPiece();
                updateLabels();
                System.out.println(tetris.getMesPieces().size());
            } else {
                System.out.println("Quitter");
                menuView = new MenuView(primaryStage);
                stopGame();
                System.out.println("Menu");
            }


        } else {
            updteGrille();
            updateTimer();
            initZoneProchainePiece();
            paintPiece();
            updateLabels();
        }

    }
}
