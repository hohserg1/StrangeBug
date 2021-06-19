package hohserg.strange.bug;

import net.minecraftforge.fml.common.Mod;

@Mod("strange_bug")
public class Main {
    public Main() {
        try {
            System.out.println("Main successful: " + Class.forName("hohserg.strange.bug.TestCompanion"));
        } catch (ClassNotFoundException e) {
            System.out.println("Main failed: ");
            e.printStackTrace();
        }
        ClassFromSubmodule.test();
        System.out.println();
    }
}
