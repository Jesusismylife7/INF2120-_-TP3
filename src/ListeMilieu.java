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
            this.supprimer(tmp.elem);
            tmp = tmp.suivant;

        }
        superieureTaille =0;
        Maillon<E> pmt = inferieure;
        superieure = pmt;
        //tmp = superieure;
        pmt  = pmt.suivant;

        int i;
        for( i=1; i < inferieureTaille /2; i++){
            superieure = new Maillon<>(pmt.elem, superieure);
            pmt  = pmt.suivant;
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
        // System.out.println(valeur);
        int compTo = taille() == 0 ? 0 : valeur.compareTo(milieu());
        if (compTo > 0) {
            // ajouter dans la liste supérieure

            Maillon<E> prec = null;
            Maillon<E> suiv = superieure;
            while (suiv != null && valeur.compareTo(suiv.elem) > 0) {
                prec = suiv;
                //La boucle finissait pas il fallait mettre suiv pour que ça s'arrête
                suiv = suiv.suivant;
            }

            // 2-> 3-> 6-> 9x
            // 3x
            if (prec == null) {
                superieure = new Maillon<E>(valeur);
            } else {
                prec.suivant = new Maillon<E>(valeur, suiv);
            }

            superieureTaille++;

        } else if (compTo < 0) {
            // ajouter dans la liste inférieure
            Maillon<E> prec = null;
            Maillon<E> suiv = inferieure;
            while (suiv != null && valeur.compareTo(suiv.elem) < 0) {
                prec = suiv;
                suiv = suiv.suivant;
            }

            // ajouter au début de la liste
            if (prec == null) {
                inferieure = new Maillon<E>(valeur);
            } else {
                prec.suivant = new Maillon<E>(valeur, suiv);
            }

            inferieureTaille++;

        } else {
            // ajouter dans la liste inférieure avant le premier
            inferieure = new Maillon< E >(valeur, inferieure);
            inferieureTaille++;
        }

        equilibrer();
    }

    public E milieu() {
        return inferieure.elem;
    }


    public E minima() {
        if (inferieureTaille == 0) {
            return null;
        }
        Maillon<E> tmp = inferieure;
        while (tmp.suivant != null) {
            tmp = tmp.suivant;
        }

        return tmp.elem;
    }

    public E maxima() {
        if (superieureTaille == 0) {
            return inferieure.elem;
        }
        Maillon<E> tmp = superieure;
        while (tmp.suivant != null) {
            tmp = tmp.suivant;
        }

        return tmp.elem;
    }

    /**
     * Cette méthode sert a supprimer la valeur mise dans les éléments.
     * @param valeur la veuleur a supprimer.
     */
    public void supprimer( E valeur ) {
        Maillon<E> tmp ;
        boolean dans_inf = false;
        if (valeur.compareTo(milieu()) > 0){
            tmp = superieure;
            superieureTaille--;
        } else {
            tmp  = inferieure;
            inferieureTaille--;
            dans_inf = true;
        }
        // 6 -> 7 ->8
        Maillon <E> prec = null;
        while (tmp.elem.compareTo(valeur) != 0){
            prec = tmp;
            tmp = tmp.suivant;
        }

        // si au début de la liste
        if (prec == null) {
            if (dans_inf) {
                inferieure = inferieure.suivant;
            } else {
                superieure = superieure.suivant;
            }
        } else {
            prec.suivant = tmp.suivant;
        }

        equilibrer();
    }

    /*

     */
    public int taille() {
        return superieureTaille + inferieureTaille;
    }

    //*

    public void equilibrer() {
        // le nb elem de superieur = nb elem de inferieur ou nb elem inferieur - 1

        // return -> 1: 4 10 12
        //              2 1 0

        // 2 :  18 19
        //      15 17

        int diff_taille = inferieureTaille - superieureTaille;
        while(diff_taille != 0 && diff_taille != 1) {
            if (diff_taille < 0) {
                // transfert superieur vers inferieur
                // le premier de supérieur devient le premier de inférieur

                inferieure = new Maillon<>(superieure.elem, inferieure);
                superieure = superieure.suivant;
                inferieureTaille++;
                superieureTaille--;
            } else {
                //transfert inferieur -> superieur
                superieure = new Maillon<>(inferieure.elem, superieure);
                inferieure = inferieure.suivant;
                inferieureTaille--;
                superieureTaille++;
            }

            diff_taille = inferieureTaille - superieureTaille;
        }
    }

    @Override
    public String toString() {
        String sup = "";
        Maillon<E> tmp = superieure;
        for(int i = 0; i < superieureTaille; i++) {
            sup = sup + tmp.elem.toString() + " ";
            tmp = tmp.suivant;
        }

        String inf = "";
        tmp = inferieure;
        for(int i = 0; i < inferieureTaille; i++) {
            inf = inf + tmp.elem.toString() + " ";
            tmp = tmp.suivant;
        }

        return "sup : "+sup+"\ninf : "+inf;
    }
}
