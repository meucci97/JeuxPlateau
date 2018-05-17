package jeuxplateau.Modele;

import jeuxplateau.Vue.Observateur;

public interface Observable{
        void addObservateur(Observateur o);
        //void removeObservateur(Observateur o);
        void notifyObsevateur();
}
