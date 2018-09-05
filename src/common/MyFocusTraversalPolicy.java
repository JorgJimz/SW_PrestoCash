package common;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

public class MyFocusTraversalPolicy extends FocusTraversalPolicy {

	Vector<Component> order;

	public MyFocusTraversalPolicy(Vector<Component> order) {
		this.order = new Vector<Component>(order.size());
		this.order.addAll(order);
	}

	@Override
	public Component getComponentAfter(Container focusCycleRoot,
			Component aComponent) {
		int i = (order.indexOf(aComponent) + 1) % order.size();
		return order.get(i);
	}

	@Override
	public Component getComponentBefore(Container focusCycleRoot,
			Component aComponent) {
		int i = order.indexOf(aComponent) - 1;
		if (i < 0) {
			i = order.size() - 1;
		}
		return order.get(i);
	}

	@Override
	public Component getDefaultComponent(Container focusCycleRoot) {
		return order.get(0);
	}

	@Override
	public Component getFirstComponent(Container focusCycleRoot) {
		 return order.lastElement();
	}

	@Override
	public Component getLastComponent(Container focusCycleRoot) {
		return order.firstElement();
	}

}
