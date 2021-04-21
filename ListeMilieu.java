/**
 * liste simplement chaînée
 * le point d'attache est au milieu de la liste (inférieure et supérieure)
 * les éléments de la liste doivent être maintenus triés (en ordre)
 * @param <E>
 */
public class ListeMilieu< E extends Comparable< E > > {

    Maillon< E > inferieure;
    int inferieureTaille =0;
    Maillon< E > superieure;
    int superieureTaille =0;


    public ListeMilieu() {
        inferieure = null;
        superieure = null;

    }

    public ListeMilieu<E> diviser() {
        ListeMilieu<E> nouv = new ListeMilieu<>();
        E elem = superieure.elem;
        Maillon<E> tmp = superieure.suivant;
        nouv.inserer(elem);

        for (int i=0; i < superieureTaille; i++){
            nouv.inserer(tmp.elem);
            tmp = tmp.suivant;
        }
        return nouv;
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

            Maillon<E> prec = null;
            Maillon<E> suiv = superieure;
            while (valeur.compareTo(suiv.elem) < 0) {
                prec = suiv;
                suiv = superieure.suivant;
            }

            // 2-> 3-> 6-> 9x
            if (prec == null) {
                superieure = new Maillon<E>(valeur, superieure);
            } else {
                prec.suivant = new Maillon<E>(valeur, suiv);
            }

        } else {
            // ajouter dans la liste inférieure avant le premier
            inferieure = new Maillon< E >(valeur, inferieure);
        }
    }

    public E milieu() {
        return inferieure.elem;
    }

    public E minima() {
        Maillon<E> tmp = inferieure;
        while (tmp.suivant != null)
            tmp=  tmp.suivant;

        return tmp.elem;
    }

    public E maxima() {
        Maillon<E> tmp = superieure;
        while (tmp.suivant != null)
            tmp=  tmp.suivant;

        return tmp.elem;
    }

    public void supprimer( E valeur ) {
        Maillon<E> tmp ;
        if (valeur.compareTo(milieu()) > 0){
            tmp = superieure;
            superieureTaille--;
        } else {
            tmp  = inferieure;
            inferieureTaille--;
        }

        while (tmp.suivant.elem.compareTo(valeur) != 0){
            tmp = tmp.suivant;
        }
        tmp.suivant  = tmp.suivant.suivant;


    }

    public int taille() {
        return superieureTaille + inferieureTaille;
    }
}