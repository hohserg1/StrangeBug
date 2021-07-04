package hohserg.strange.bug;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("strange_bug")
public class Main {
	public Main() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
	}

	private void onCommonSetup(FMLCommonSetupEvent event) {
		try {
			System.out.println("Classloader for " + getClass().getName() + " is " + getClass().getClassLoader());
			Class testCompanionClass = Class.forName("hohserg.strange.bug.TestCompanion");
			System.out.println("Main successful: class " + testCompanionClass.getName() + ", classloader " + testCompanionClass.getClassLoader());
		} catch (ClassNotFoundException e) {
			System.out.println("Main failed: ");
			e.printStackTrace();
		}
		ClassFromSubmodule.test();
		System.out.println();
	}
}
