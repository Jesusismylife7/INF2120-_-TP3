import java.util.ArrayList;
import java.util.List;

public class ListeIndex<E extends Comparable< E > > {

    private List<ListeMilieu<E>> milieux ;
    private int nbrListe;

    public ListeIndex() {
        milieux = new ArrayList<>(10);
        nbrListe = 0;
    }

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
        boolean estInseree = false;
        int i;
        if (nbrListe == 0){
            ListeMilieu<E> ajou = new ListeMilieu<>();
            ajou.inserer(valeur);
            milieux.add(ajou);
        } else {
            for (i = 0; i < milieux.size() && milieux.get(i) != null
                    && milieux.get(i+1) != null
                    && !estInseree; i++) {
                if (i != milieux.size() - 1
                        && milieux.get(i).minima().compareTo(valeur) <= 0
                        && milieux.get(i+1).minima().compareTo(valeur) > 0) {
                    milieux.get(i).inserer(valeur);


                    estInseree = true;
                } else if (!estInseree && i == milieux.size() - 1
                        && milieux.get(i).minima().compareTo(valeur) <= 0) {
                    milieux.get(i).inserer(valeur);

                }
            }

            if (estInseree && !(milieux.get(i).taille() <= 2 * nbrListe)) {
                milieux.add(milieux.get(i).diviser());
            }
        }


        //valeur ---E; creer --listMilieu; ajoute lui. si il existe deja, il faut verifier.
    }

    /**
     * Retourne le nombre de ListMilieu dans la ListeIndex.
     * @return
     */
    public int nbrListe() {
        return nbrListe;
    }

    /**
     * Supprime la valeur si elle existe.
     * @param valeur : la valeur à supprimer
     */
    public void supprimer( E valeur ) {
//valeur (E); milieux (ListMilieux), arraylist; E --- ListMilieux


       if(nbrListe > 0) {
            for (ListeMilieu<E> mil : milieux) {
                if (mil.contient(valeur)) {
                    mil.supprimer(valeur);
                }
            }
        }


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