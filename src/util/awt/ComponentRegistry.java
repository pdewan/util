package util.awt;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class ComponentRegistry {
	static List<Component> components = new ArrayList();

	public static void register(Component theFrame) {
		components.add(theFrame);
	}

	public static Component getComponent(int id) {
		return components.get(id);
	}

	public static int getComponentId(Component theComponent) {
		return components.indexOf(theComponent);
	}
}
