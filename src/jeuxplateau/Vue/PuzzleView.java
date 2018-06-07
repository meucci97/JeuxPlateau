package jeuxplateau.Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jeuxplateau.Controlleur.PuzzleControlleur;
import jeuxplateau.Modele.Puzzle;

import java.util.Optional;

public class PuzzleView implements Observateur {
    private PuzzleView vuePuzzle = this;
    private Stage primaryStage;
    private Scene plateauxPuzzle;
    private Group root;
    private StackPane motherRoot;
    private GridPane grille;
    private MenuView menuView;
    private GridPane lbGrid;
    private GridPane pieceGrid;
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
        controlleur = new PuzzleControlleur(puzzle);
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
                //System.out.println("mouse click detected! "+event.getPickResult());
                //System.out.println("mouse click detected! "+event.getPickResult().getIntersectedNode().getId());
                controlleur.clickSelectionPiece(Integer.parseInt(event.getPickResult().getIntersectedNode().getId()));
            }
        });
        this.root.getChildren().add(grille);

    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    private void initBouttons() {

        // GridPane des bouttons Pause et Quitter
        GridPane btnGrid = new GridPane();
        btnGrid.setTranslateX(250);
        btnGrid.setTranslateY(150);
        btnGrid.setHgap(10);
        Button btnQuitter = new Button("Quitter");
        Button btnReset = new Button("Reset");
        btnGrid.add(btnReset, 0, 0);
        btnGrid.add(btnQuitter, 1, 0);

        this.root.getChildren().add(btnGrid);

        btnQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Quitter");
                menuView = new MenuView(primaryStage);

                //System.out.println("Menu");
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Reset");
                controlleur.resetGame(vuePuzzle);
                initClavier();
            }
        });
    }

    public void initialisationAll() {

        BackgroundImage myBI= new BackgroundImage(new Image("file:background2.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        motherRoot.setBackground(new Background(myBI));

        initGrille();
        initBouttons();
        initZonePieceChoisie();

        this.motherRoot.getChildren().add(root);
        this.plateauxPuzzle = new Scene(motherRoot, LARGEUR_FENETRE, HAUTEUR_FENTRE/*, Color.web("#323232")*/);
        initClavier();
        primaryStage.setScene(plateauxPuzzle);
        primaryStage.setTitle("Puzzle");
    }

    private void initZonePieceChoisie() {
        // GridPane zone pièce
        int selectedPiece=puzzle.getSelectedPiece();
         pieceGrid = new GridPane();
        pieceGrid.setTranslateX(250);
        pieceGrid.setTranslateY(50);
        Label lbPiece = new Label("Pièces sélectionné : ");
        lbPiece.setTextFill(Color.web("#cd853f"));
        pieceGrid.getChildren().addAll(lbPiece);
        pieceGrid.setRowIndex(lbPiece, 0);
        pieceGrid.setColumnIndex(lbPiece, 0);
        Label lbniveau = new Label("Niveau "+(puzzle.getNiveau()+1));
        lbniveau.setTextFill(Color.web("#cd853f"));
        pieceGrid.getChildren().addAll(lbniveau);
        pieceGrid.setRowIndex(lbniveau, 1);
        pieceGrid.setColumnIndex(lbniveau, 0);
        Rectangle rec = new Rectangle();
        rec.setWidth(25);
        rec.setHeight(25);
        rec.setFill(Color.web(puzzle.getSelecedPieceColor()));
        pieceGrid.setRowIndex(rec, 0);
        pieceGrid.setColumnIndex(rec, 1);
        pieceGrid.getChildren().addAll(rec);
        this.root.getChildren().add(pieceGrid);
    }
     private void updateZonePieceChoisie(){
         int selectedPiece=puzzle.getSelectedPiece();
         pieceGrid.getChildren().clear();

         Label lbPiece = new Label("Pièces sélectionné : ");
         lbPiece.setTextFill(Color.web("#cd853f"));
         pieceGrid.getChildren().addAll(lbPiece);
         pieceGrid.setRowIndex(lbPiece, 0);
         pieceGrid.setColumnIndex(lbPiece, 0);
         Label lbniveau = new Label("Niveau "+(puzzle.getNiveau()+1));
         lbniveau.setTextFill(Color.web("#cd853f"));
         pieceGrid.getChildren().addAll(lbniveau);
         pieceGrid.setRowIndex(lbniveau, 1);
         pieceGrid.setColumnIndex(lbniveau, 0);
         Rectangle rec = new Rectangle();
         rec.setWidth(25);
         rec.setHeight(25);
         rec.setFill(Color.web(puzzle.getSelecedPieceColor()));
         pieceGrid.setRowIndex(rec, 0);
         pieceGrid.setColumnIndex(rec, 1);
         pieceGrid.getChildren().addAll(rec);

     }

    private void initClavier() {
        root.requestFocus();
        this.plateauxPuzzle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        //System.out.println("down");
                        controlleur.clickDown();
                        break;
                    case RIGHT:
                        //System.out.println("right");
                        controlleur.clickRight(vuePuzzle);
                        break;
                    case LEFT:
                        //System.out.println("left");
                        controlleur.clickLeft();
                        break;
                    case UP:
                        //System.out.println("up");
                        controlleur.clickUp();
                        break;
                }
            }
        });
    }

    @Override
    public void update() {
        if(puzzle.isLevelDone()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GAME OVER ");
            alert.setHeaderText("Voulez vous recommencer?");
            alert.setContentText("");
            try{
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    controlleur.resetGame(vuePuzzle);
                    updateZonePieceChoisie();
                    initGrille();

                   // updateLabels();
                } else {
                    //System.out.println("Quitter");
                    menuView = new MenuView(primaryStage);
                    //System.out.println("Menu");
                }
            }catch(Exception e){
            }
        }else{
            updateZonePieceChoisie();
            initGrille();
        }

    }
}
