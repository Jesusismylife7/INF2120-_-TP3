/**
 *
 * @param <E>
 */
public class Maillon< E extends Comparable< E > > {
    E elem;
    Maillon< E > suivant;

    /**
     *
     * @param elem
     */
    public Maillon(E elem) {
        this.elem = elem;
    }

    /**
     *
     * @param elem
     * @param suivant
     */
    public Maillon(E elem, Maillon< E > suivant) {
        this.elem = elem;
        this.suivant = suivant;
    }
}
