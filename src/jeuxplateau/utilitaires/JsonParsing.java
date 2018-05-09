package jeuxplateau.utilitaires;

import jeuxplateau.Modele.Piece;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Vector;

public class JsonParsing {

    private JSONObject parseJson(String filename) throws Exception {

        Object obj = new JSONParser().parse(new FileReader(filename));

        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject;
    }

    public static Piece genererPieceAleatoire(String filename) {
        try {

            JSONObject json = new JsonParsing().parseJson(filename);

            Piece piece = new Piece();

            return piece;

        } catch (Exception e) {
            e.getStackTrace();
        }

        return new Piece();
    }

    public static Vector<Piece> genererPieces(String filename) {
        Vector<Piece> pieces = new Vector<>();

        return pieces;
    }
}
