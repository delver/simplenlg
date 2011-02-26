package simplenlg.aggregation;

import java.util.ArrayList;
import java.util.List;

import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.NLGModule;

/**
 * An Aggregator performs aggregation on clauses, by applying a set of
 * prespecified rules on them and returning the result.
 * 
 * @author Albert Gatt, University of Malya & University of Aberdeen
 * 
 */
public class Aggregator extends NLGModule {

	private List<AggregationRule> _rules;
	private NLGFactory _factory;

	/**
	 * Creates an instance of Aggregator
	 */
	public Aggregator() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialise() {
		this._rules = new ArrayList<AggregationRule>();
		this._factory = new NLGFactory();
	}

	/**
	 * Set the factory that this aggregator should use to create phrases. The
	 * factory will be passed on to all the component rules.
	 * 
	 * @param factory
	 *            the phrase factory
	 */
	public void setFactory(NLGFactory factory) {
		this._factory = factory;

		for (AggregationRule rule : this._rules) {
			rule.setFactory(this._factory);
		}
	}

	/**
	 * Add a rule to this aggregator. Aggregation rules are applied in the order
	 * in which they are supplied.
	 * 
	 * @param rule
	 *            the rule
	 */
	public void addRule(AggregationRule rule) {
		rule.setFactory(this._factory);
		this._rules.add(rule);
	}

	/**
	 * Get the rules in this aggregator.
	 * 
	 * @return the rules
	 */
	public List<AggregationRule> getRules() {
		return this._rules;
	}

	/**
	 * Apply aggregation to a single phrase. This will only work if the phrase
	 * is a coordinated phrase, whose children can be further aggregated.
	 * 
	 * @see {@link AggregationRule#apply(NLGElement)}
	 */
	@Override
	public NLGElement realise(NLGElement element) {
		NLGElement result = element;

		for (AggregationRule rule : this._rules) {
			NLGElement intermediate = rule.apply(result);

			if (intermediate != null) {
				result = intermediate;
			}
		}

		return result;
	}

	/**
	 * Apply aggregation to a list of elements. This method iterates through the
	 * rules supplied via {@link #addRule(AggregationRule)} and applies them to
	 * the elements.
	 * 
	 * @param elements
	 *            the list of elements to aggregate
	 * @return a list of the elements that remain after the aggregation rules
	 *         have been applied
	 */
	@Override
	public List<NLGElement> realise(List<NLGElement> elements) {
		for (AggregationRule rule : this._rules) {
			elements = rule.apply(elements);
		}

		return elements;
	}

}
