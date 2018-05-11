package jeuxplateau.utilitaires;

import jeuxplateau.Modele.Piece;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class JsonParsing {

    private JSONObject parseJson(String filename) throws Exception {

        Object obj = new JSONParser().parse(new FileReader(filename));

        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject;
    }

    public static Piece genererPieceAleatoire(String filename) {
        try {
            JSONObject json = new JsonParsing().parseJson(filename);

            // Nombre de pièces
            int nbPieces = Integer.parseInt(json.get("nombrePieces").toString());

            // Génération d'un numéro au hasard
            Random rn = new Random();
            int idPiece = rn.nextInt(nbPieces);

            // Tableau de toutes les pièces
            JSONArray piecesArray = (JSONArray) json.get("pieces");
            JSONObject pieceMap = (JSONObject) piecesArray.get(idPiece);

            // Matrice de la pièce
            //ArrayList<ArrayList<Long>> matrice = new ArrayList<>();
            JSONArray matriceJSON = (JSONArray) pieceMap.get("points");
            int[][] matrice = new int[4][4];

            for (int i = 0; i < matriceJSON.size(); i++) {
                JSONArray elt = (JSONArray) matriceJSON.get(i);
                int[] ligne = new int[4];

                for(int j = 0; j < elt.size(); j++) {
                    int number = (int) (long) elt.get(j);

                    ligne[j] = number;
                }

                matrice[i] = ligne;
            }

            // Couleur de la pièce
            String couleur = (String) pieceMap.get("couleur");

            // Orientation par défaut et point d'orientation de la pièce
            int orientation = Integer.parseInt(json.get("orientationDefaut").toString());

            // Tableau point orientation
            Map pointOrientationMap = (Map) json.get("pointOrientation");
            int pointOrientationX = Integer.parseInt(pointOrientationMap.get("x").toString());
            int pointOrientationY = Integer.parseInt(pointOrientationMap.get("y").toString());
            int pointOrientation[] = { pointOrientationX, pointOrientationY };

            // Création de la pièce
            Piece piece = new Piece(orientation, idPiece, matrice, pointOrientation, couleur);

            return piece;

        } catch (Exception e) {
            e.getStackTrace();
        }

        return new Piece();
    }

    public static Vector<Piece> genererPieces(String filename) {

        try {
            JSONObject json = new JsonParsing().parseJson(filename);
            Vector<Piece> pieces = new Vector<>();

            // Nombre de pièces
            int nbPieces = Integer.parseInt(json.get("nombrePieces").toString());

            for (int noPiece = 0; noPiece < nbPieces; noPiece++) {

                // Tableau de toutes les pièces
                JSONArray piecesArray = (JSONArray) json.get("pieces");
                JSONObject pieceMap = (JSONObject) piecesArray.get(noPiece);

                // Matrice de la pièce
                //ArrayList<ArrayList<Long>> matrice = new ArrayList<>();
                JSONArray matriceJSON = (JSONArray) pieceMap.get("points");
                int[][] matrice = new int[4][4];

                for (int i = 0; i < matriceJSON.size(); i++) {
                    JSONArray elt = (JSONArray) matriceJSON.get(i);
                    int[] ligne = new int[4];

                    for(int j = 0; j < elt.size(); j++) {
                        int number = (int) (long) elt.get(j);

                        ligne[j] = number;
                    }

                    matrice[i] = ligne;
                }

                // Couleur de la pièce
                String couleur = (String) pieceMap.get("couleur");

                // Orientation par défaut et point d'orientation de la pièce
                int orientation = Integer.parseInt(json.get("orientationDefaut").toString());

                // Tableau point orientation
                Map pointOrientationMap = (Map) json.get("pointOrientation");
                int pointOrientationX = Integer.parseInt(pointOrientationMap.get("x").toString());
                int pointOrientationY = Integer.parseInt(pointOrientationMap.get("y").toString());
                int pointOrientation[] = { pointOrientationX, pointOrientationY };

                // Création de la pièce
                Piece piece = new Piece(orientation, noPiece, matrice, pointOrientation, couleur);

                pieces.add(piece);
            }

            System.out.println(pieces);

            return pieces;

        } catch (Exception e) {
            e.getStackTrace();
        }

        return new Vector<Piece>();
    }
}
