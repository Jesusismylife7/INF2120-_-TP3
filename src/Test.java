public class Test {
    public static void main(String[] args) {
        ListeMilieu <Integer> list = new ListeMilieu<>();
        list.inserer(1);
        list.inserer(2);
        list.inserer(0);
        list.inserer(5);
        list.inserer(10);

    //    System.out.println(list.inferieure.elem);
        System.out.println(list.superieure.elem);
    }
}
