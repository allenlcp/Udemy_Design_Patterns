package e006_singleton_challenge;

public class Test {
    public static void main(String[] args) {

        MakeACaptain c1 = MakeACaptain.getInstance();
        MakeACaptain c2 = MakeACaptain.getInstance();

        System.out.println(c1 == c2); // comparing instances here not content (ie not using .equals())

    }
}
