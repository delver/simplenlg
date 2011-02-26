package simplenlg.aggregation;

import java.util.Arrays;
import java.util.List;

import simplenlg.features.DiscourseFunction;
import simplenlg.features.Feature;
import simplenlg.features.InternalFeature;
import simplenlg.framework.ElementCategory;
import simplenlg.framework.ListElement;
import simplenlg.framework.NLGElement;

public class FunctionalSet {

	private List<NLGElement> components;
	private DiscourseFunction function;
	private ElementCategory category;
	private Periphery periphery;

	public static FunctionalSet newInstance(DiscourseFunction func,
			ElementCategory category, Periphery periphery,
			NLGElement... components) {

		FunctionalSet pair = null;

		if (components.length >= 2) {
			pair = new FunctionalSet(func, category, periphery, components);
		}

		return pair;

	}

	FunctionalSet(DiscourseFunction func, ElementCategory category,
			Periphery periphery, NLGElement... components) {
		this.function = func;
		this.category = category;
		this.periphery = periphery;
		this.components = Arrays.asList(components);
	}

	public boolean formIdentical() {
		boolean ident = true;
		NLGElement firstElement = this.components.get(0);

		for (int i = 1; i < this.components.size() && ident; i++) {
			ident = firstElement.equals(components.get(i));
		}

		return ident;
	}

	public boolean lemmaIdentical() {
		return false;
	}

	public void elideLeftMost() {
		for(int i = 0; i < this.components.size()-1; i++) {
			recursiveElide(components.get(i));		
		}
	}

	public void elideRightMost() {
		for(int i = this.components.size()-1; i > 0; i--) {
			recursiveElide( components.get(i) );
			
		}
	}
	
	private void recursiveElide(NLGElement component) {
		if(component instanceof ListElement) {
			for(NLGElement subcomponent: component.getFeatureAsElementList(InternalFeature.COMPONENTS)) {
				recursiveElide(subcomponent);
			}
		} else {
			component.setFeature(Feature.ELIDED, true);
		}
	}

	public DiscourseFunction getFunction() {
		return function;
	}

	public ElementCategory getCategory() {
		return category;
	}

	public Periphery getPeriphery() {
		return periphery;
	}
	
	public List<NLGElement> getComponents() {
		return this.components;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		for(NLGElement elem: this.components) {
			buffer.append("ELEMENT: ").append(elem.toString()).append("\n");
		}
		
		return buffer.toString();
	}
	
}
