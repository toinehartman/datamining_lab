import java.util.HashSet;
import java.util.Set;


public class StringSet extends HashSet<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to copy a set.
	 * @param set
	 */
	public StringSet(Set<String> set) {
		super(set);
	}

	/**
	 * Default constructor.
	 */
	public StringSet() {
		super();
	}

	/**
	 * Copy another StringSet
	 * @param set
	 */
	public StringSet(StringSet set) {
		super(set);
	}

	/**
	 * Override the hashCode method
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		for (String s: this)
			hash += s.hashCode();
		return hash;
	}
	
	/**
	 * Override the equals method
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof StringSet)
			return ((StringSet)o).hashCode() == hashCode();
		return false;
	}
}
