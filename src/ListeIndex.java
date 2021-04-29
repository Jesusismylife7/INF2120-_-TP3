import java.util.ArrayList;
import java.util.List;

public class ListeIndex<E extends Comparable< E > > {

    private List<ListeMilieu<E>> milieux = new ArrayList<>();

    /**
     * Vérifie si la valeur est dans la ListeIndex
     * @param valeur
     * @return
     */
    public boolean contient( E valeur ) {
        boolean trouver = false;

        for (ListeMilieu<E> mil: milieux) {
            if (mil.contient(valeur)) {
                trouver = true;
            }
        }

        return trouver;
    }
    /**
        Retourne la ListeMilieu à l'index i.
        @param i : index à retourner
     */
    public ListeMilieu<E> get( int i ) {
        return milieux.get(i);
    }

    /**
     * Insère la valeur dans la bonne ListeMilieu (selon le minima et maxima de chaque
     * liste).
     * @param valeur
     */
    public void inserer( E valeur ) {

    }

    /**
     * Retourne le nombre de ListMilieu dans la ListeIndex.
     * @return
     */
    public int nbrListe() {
        return milieux.size();
    }

    /**
     * Supprime la valeur si elle existe.
     * @param valeur : la valeur à supprimer
     */
    public void supprimer( E valeur ) {

    }

    /**
     * Retourne le nombre d'éléments dans la ListeIndex.
     * @return
     */
    public int taille() {
        int taille_totale = 0;
        for (ListeMilieu<E> mil : milieux) {
            taille_totale += mil.taille();
        }
        return taille_totale;
    }
}