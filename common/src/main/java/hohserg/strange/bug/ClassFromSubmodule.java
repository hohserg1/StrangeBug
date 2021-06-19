package hohserg.strange.bug;

public class ClassFromSubmodule {
    public static void test() {
        try {
            System.out.println("ClassFromSubmodule successful: " + Class.forName("hohserg.strange.bug.TestCompanion"));
        } catch (Throwable e) {
            System.out.println("ClassFromSubmodule failed: ");
            e.printStackTrace();
        }
    }
}
