import java.util.ArrayList;
import java.util.List;

public class ListeIndex<E extends Comparable< E > > {

    private List<ListeMilieu<E>> milieux ;

    public ListeIndex() {
        milieux = new ArrayList<>(10);
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
     * @param valeur La valeur a insérer
     */
    public void inserer( E valeur ) {
        boolean estInseree = false;

        if (nbrListe() == 0) {
            milieux.add(new ListeMilieu<E>());
        }

        for (int i = 0; i < nbrListe() && !estInseree; i++) {

            ListeMilieu<E> mil = milieux.get(i);

            boolean is_last_list = i == nbrListe() - 1;
            boolean smaller_than_first = !is_last_list && i == 0 && valeur.compareTo(mil.minima()) < 0;

            if (is_last_list || smaller_than_first || (valeur.compareTo(mil.minima()) >= 0
                && valeur.compareTo(milieux.get(i+1).minima()) < 0)) {

                mil.inserer(valeur);
                estInseree = true;

            }

            if (estInseree && mil.taille() > 2 * nbrListe()) {
                milieux.add(i+1, mil.diviser());
                return;
            }
        }

        //valeur ---E; creer --listMilieu; ajoute lui. si il existe deja, il faut verifier.
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
//valeur (E); milieux (ListMilieux), arraylist; E --- ListMilieux


       if(nbrListe() > 0) {
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

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < nbrListe(); i++) {
            ret += milieux.get(i).toString() + "\n";
        }

        return ret;
    }
}
