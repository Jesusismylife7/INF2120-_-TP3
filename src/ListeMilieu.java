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

    /*
    10 12 15
    8 5 3

    return -> 1: 15
                 12 10



    this -> 2: 8
               5 3
     */

    public ListeMilieu<E> diviser() {
        ListeMilieu<E> nouv = new ListeMilieu<>();

        Maillon<E> tmp = superieure;
        for (int i=0; i < superieureTaille; i++){
            nouv.inserer(tmp.elem);
            tmp = tmp.suivant;
            this.supprimer(tmp.elem);
        }
        return nouv;
    }

    /**
     * Insère des valeurs dans la ListeMilieu selon la valeur qui est au milieu.
     * Si la valeur est plus petite que le milieu, insertion dans le Maillon inférieur.
     * Autrement, dans le Maillon supérieur.
     * @param valeur La valeur à insérer.
     */
    public void inserer( E valeur ) {
        // toutes valeurs <= 10 -> inférieure
        // autrement dans supérieur
        System.out.println(valeur);
        int compTo = taille() == 0 ? 0 : valeur.compareTo(milieu());
        if (compTo > 0) {
            // ajouter dans la liste supérieure

            Maillon<E> prec = null;
            Maillon<E> suiv = superieure;
            while (suiv != null && valeur.compareTo(suiv.elem) > 0) {
                prec = suiv;
                suiv = superieure.suivant;
            }

            // 2-> 3-> 6-> 9x
            if (prec == null) {
                superieure = new Maillon<E>(valeur, superieure);
            } else {
                prec.suivant = new Maillon<E>(valeur, suiv);
            }

            superieureTaille++;

        } else if (compTo < 0) {
            // ajouter dans la liste inférieure
            Maillon<E> prec = null;
            Maillon<E> suiv = inferieure;
            while (valeur.compareTo(suiv.elem) > 0) {
                prec = suiv;
                suiv = inferieure.suivant;
            }

            // ajouter au début de la liste
            if (prec == null) {
                inferieure = new Maillon<E>(valeur, inferieure);
            } else {
                prec.suivant = new Maillon<E>(valeur, suiv);
            }

            inferieureTaille++;

        } else {
            // ajouter dans la liste inférieure avant le premier
            inferieure = new Maillon< E >(valeur, inferieure);
            inferieureTaille++;
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

    /**
     * A tester
     * @param valeur
     */
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

    public void equilibrer() {
        // le nb elem de superieur = nb elem de inferieur ou nb elem inferieur - 1

        // return -> 1: 15 17 18 19
        //              12 10 4

        int diff_taille = inferieureTaille - superieureTaille;
        while(diff_taille != 0 || diff_taille != 1) {
            if (diff_taille < 0) {
                // transfert superieur vers inferieur
                // le premier de supérieur devient le premier de inférieur
                Maillon<E> tmp = superieure.suivant;
                superieure.suivant = inferieure;
                inferieure = superieure;
                superieure = tmp;
            } else if (diff_taille > 1) {
                //transfert inferieur -> superieur
                Maillon<E> tmp = inferieure.suivant;
                inferieure.suivant = superieure;
                superieure = inferieure;
                inferieure = tmp;
            }

            diff_taille -= 2;
        }
    }

    @Override
    public String toString() {
        System.out.println("yo");
        String sup = "";
        Maillon<E> tmp = superieure;
        for(int i = 0; i < superieureTaille; i++) {
            sup = sup + tmp.elem.toString();
            tmp = tmp.suivant;
        }

        String inf = "";
        tmp = inferieure;
        for(int i = 0; i < inferieureTaille; i++) {
            inf = inf + tmp.elem.toString() + ", ";
            tmp = tmp.suivant;
        }

        return "sup : "+sup+"\ninf : "+inf;
    }
}