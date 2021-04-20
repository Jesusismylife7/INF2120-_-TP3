/**
 * liste simplement chaînée
 * le point d'attache est au milieu de la liste (inférieure et supérieure)
 * les éléments de la liste doivent être maintenus triés (en ordre)
 * @param <E>
 */
public class ListeMilieu< E extends Comparable< E > > {

    Maillon< E > inferieure;
    Maillon< E > superieure;

    public ListeMilieu() {
    }

    public ListeMilieu<E> diviser() {
        return null;
    }

    /**
     * Écrire le commentaire de cette méthode
     * @param valeur
     */
    public void inserer( E valeur ) {
        // toutes valeurs <= 10 -> inférieure
        // autrement dans supérieur
        int compTo = valeur.compareTo(milieu());
        if (compTo > 0) {
            // ajouter dans la liste supérieure

            Maillon<E> prec = null;
            Maillon<E> suiv = superieure;
            while (valeur.compareTo(suiv.elem) > 0) {
                prec = suiv;
                suiv = superieure.suivant;
            }

            // 2-> 3-> 6-> 9x
            if (prec == null) {
                superieure = new Maillon<E>(valeur, superieure);
            } else {
                prec.suivant = new Maillon<E>(valeur, suiv);
            }

        } else if (compTo < 0) {
            // ajouter dans la liste inférieure

        } else {
            // ajouter dans la liste inférieure avant le premier
            inferieure = new Maillon< E >(valeur, inferieure);
        }
    }

    public E milieu() {
        return inferieure.elem;
    }

    public E minima() {
        return null;
    }

    public E maxima() {
        return null;
    }

    public void supprimer( E valeur ) {
    }

    public int taille() {
        return 0;
    }
}