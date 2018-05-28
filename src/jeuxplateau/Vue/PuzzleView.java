package jeuxplateau.Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jeuxplateau.Controlleur.PuzzleControlleur;
import jeuxplateau.Controlleur.TetrisControlleur;
import jeuxplateau.Modele.Jeu;
import jeuxplateau.Modele.Puzzle;
import jeuxplateau.Modele.Tetris;

public class PuzzleView {
    private PuzzleView vuPuzzle = this;
    private Stage primaryStage;
    private Scene plateauxTetris;
    private Group root;
    private StackPane motherRoot;
    private GridPane grille;
    private MenuView menuView;
    private GridPane lbGrid;

    private Puzzle puzzle;
    private PuzzleControlleur controlleur;

    private final static int HAUTEUR_FENTRE = 525;
    private final static int LARGEUR_FENETRE = 425;

    public PuzzleView(Stage primaryStage, Puzzle puzzle) {
        this.primaryStage = primaryStage;
        this.root = new Group();
        this.motherRoot = new StackPane();
        this.puzzle = puzzle;
        initialisationAll();
        //controlleur = new PuzzleControlleur(puzzle);
    }


    private void initGrille() {
        grille = new GridPane();
        int width= puzzle.getGrille().getWidth();
        int heigth= puzzle.getGrille().getHeight();
        for (int ligne = 0; ligne < heigth; ligne++) {
            for (int colonne = 0; colonne < width; colonne++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(25);
                rec.setHeight(25);
                rec.setFill(Color.web(puzzle.getGrille().getCase(ligne, colonne).getCouleur()));
                rec.setStroke(Color.GRAY);
                rec.setId(""+(puzzle.getGrille().getCase(ligne, colonne).getIntOccupe()));

                grille.add(rec, colonne, ligne);
            }
        }

        grille.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! "+event.getPickResult());
                System.out.println("mouse click detected! "+event.getPickResult().getIntersectedNode().getId());

            }
        });
        this.root.getChildren().add(grille);

    }

    private void updteGrille() {
        int heigth= puzzle.getGrille().getHeight();
        int width= puzzle.getGrille().getWidth();
        for (int ligne = 0; ligne < heigth; ligne++) {
            for (int colonne = 0; colonne < width; colonne++) {
                ((Rectangle) grille.lookup("#Rec" + ((width * ligne) + colonne))).setFill(Color.web(puzzle.getGrille().getCase(ligne, colonne).getCouleur()));
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
        Button btnReset = new Button("Reset");
        btnGrid.add(btnReset, 0, 0);
        btnGrid.add(btnQuitter, 1, 0);

        this.root.getChildren().add(btnGrid);

        btnQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Reset");
                //controlleur.resetGame(LARGEUR_GRILLE,HAUTEUR_GRILLE, vuTetris);
            }
        });
    }

    public void initialisationAll() {

        BackgroundImage myBI= new BackgroundImage(new Image("file:background2.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        motherRoot.setBackground(new Background(myBI));
        // Lancement de la musique de fond

        initGrille();
        initBouttons();


        this.motherRoot.getChildren().add(root);
        this.plateauxTetris = new Scene(motherRoot, LARGEUR_FENETRE, HAUTEUR_FENTRE/*, Color.web("#323232")*/);

        primaryStage.setScene(plateauxTetris);
        primaryStage.setTitle("Tetris");
    }

}
