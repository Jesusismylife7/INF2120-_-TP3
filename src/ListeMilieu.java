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

    public boolean contient( E valeur ) {

        if (taille() == 0) {
            return false;
        }

        Maillon< E > tmp = valeur.compareTo(milieu()) > 0 ? superieure : inferieure;

        while (tmp != null && tmp.elem.compareTo(valeur) != 0) {
            tmp = tmp.suivant;
        }

        return tmp != null;
    }

    /**
     * Divise la ListeMilieu en deux. Retourne la liste supérieure et conserve l'inférieure.
     * @return La nouvelle ListeMilieu contenant les valeurs supérieures.
     */
    public ListeMilieu<E> diviser() {
        ListeMilieu<E> nouv = new ListeMilieu<>();

        Maillon<E> tmp = superieure;

        for (int i=1; i <= superieureTaille; i++){
            nouv.inserer(tmp.elem);
            this.supprimer(tmp.elem);
            tmp = tmp.suivant;

        }
        if (tmp != null){
            nouv.inserer(tmp.elem);
            this.supprimer(tmp.elem);
        }

        equilibrer();

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
                superieure = new Maillon<E>(valeur, suiv);
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
                inferieure = new Maillon<E>(valeur, suiv);
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
        if (taille() < 0) return null;

        if (superieureTaille == 0) {
            return inferieure.elem;
        }
        Maillon<E> tmp = superieure;
        while (tmp != null && tmp.suivant != null) {
            tmp = tmp.suivant;
        }
        if (tmp == null) return null;
        else
        return tmp.elem;
    }

    /**
     * Cette méthode sert a supprimer la valeur mise dans les éléments.
     * @param valeur la veuleur a supprimer.
     */
    public void supprimer( E valeur ) {

        Maillon < E > prec = null;
        Maillon< E > tmp;

        if (valeur.compareTo(milieu()) > 0 && superieure != null) {
            tmp = superieure;
            while (tmp != null && tmp.elem.compareTo(valeur) != 0){
                prec = tmp;
                tmp = tmp.suivant;
            }

            if (prec == null) {
                superieure = tmp.suivant;
                superieureTaille--;
            } else if (tmp != null) {
                prec.suivant = tmp.suivant;
                superieureTaille--;
            }
        } else if (inferieure != null) {
            tmp = inferieure;
            while (tmp != null && tmp.elem.compareTo(valeur) != 0){
                prec = tmp;
                tmp = tmp.suivant;
            }

            if (prec == null) {
                inferieure = tmp.suivant;
                inferieureTaille--;
            } else if (tmp != null) {
                prec.suivant = tmp.suivant;
                inferieureTaille--;
            }
        }

        equilibrer();
    }

    /*

     */
    public int taille() {
        return superieureTaille + inferieureTaille;
    }

    /*
        Cette methode sert aequilibrer les elements inférieur et supperieur
     */

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
        for(int i = 0; i < superieureTaille && tmp != null; i++) {
            sup = sup + tmp.elem.toString() + " ";
            tmp = tmp.suivant;
        }

        String inf = "";
        tmp = inferieure;
        for(int i = 0; i < inferieureTaille && tmp != null; i++) {
            inf = inf + tmp.elem.toString() + " ";
            tmp = tmp.suivant;
        }

        return "inf : "+inf+"\nsup : "+sup;
    }
}
