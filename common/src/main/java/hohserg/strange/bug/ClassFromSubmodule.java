package hohserg.strange.bug;

public class ClassFromSubmodule {
	public static void test() {
		try {
			System.out.println("Classloader for " + ClassFromSubmodule.class.getName() + " is " + ClassFromSubmodule.class.getClassLoader());
			Class testCompanionClass = Class.forName("hohserg.strange.bug.TestCompanion");
			System.out.println("ClassFromSubmodule successful: class " + testCompanionClass.getName() + ", classloader " + testCompanionClass.getClassLoader());
		} catch (Throwable e) {
			System.out.println("ClassFromSubmodule failed: ");
			e.printStackTrace();
		}
	}
}
