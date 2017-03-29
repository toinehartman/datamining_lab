import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class APriori {
	/**
	 * The dataset. Each basket could be thought of as sentences, each entry as words.
	 */
	protected List<Set<String>> baskets;

	/**
	 * The threshold for when an item is frequent.
	 */
	protected int supportThreshold;

	/**
	 * Constructor for the A-Priori class.
	 * @param s The support threshold value.
	 */
	public APriori(int st) {
		supportThreshold = st;
		baskets = new ArrayList<Set<String>>();
	}

	/**
	 * Adds a basket (sentence) to the list of baskets.
	 * @param basket The basket to add.
	 */
	public void addBasket(String basket) {
		baskets.add(new HashSet<String>(Arrays.asList(basket.toLowerCase().split(" "))));
	}

	/**
	 * Computes all subsets of size k from set.
	 * @param set The set of which the subsets should be computed.
	 * @param k The size of the computed subsets.
	 * @return A set of subsets.
	 */
	public static Set<StringSet> getSubsets(Set<String> set, int k) {
		Set<StringSet> result = new HashSet<StringSet>();
		StringSet setList = new StringSet(set);

		StringSet subset = new StringSet();
		getSubsets_(setList, subset, k, result);
		return result;
	}

	/**
	 * Recursive method for getSubsets.
	 */
	private static void getSubsets_(StringSet set, StringSet subset, int subsetSize, Set<StringSet> candidates) {
		if (subsetSize == subset.size()) {
			candidates.add((StringSet)subset.clone());
		} else {
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String s = it.next();
				subset.add(s);
				StringSet clone = new StringSet(set);
				clone.remove(s);
				getSubsets_(clone, subset, subsetSize, candidates);
				subset.remove(s);
			}
		}
	}

	/**
	 * Constructs candidates based on the previous set of frequent itemsets (L_k-1)
	 * @param filteredCandidates The set of frequent k-1 itemsets
	 * @return A set of candidate itemsets of size k
	 */
	public Set<StringSet> constructCandidates(Set<StringSet> filteredCandidates, int k) {
		// the result
		Set<StringSet> candidates = new HashSet<StringSet>();

		if (filteredCandidates == null) {
			// add all initial words to the items set
			for (Set<String> basket: baskets) {
				for (String s: basket) {
					StringSet sl = new StringSet();
					sl.add(s);
					candidates.add(sl);
				}
			}
		} else {
			
			StringSet[] sets = new StringSet[filteredCandidates.size()];
			sets = filteredCandidates.toArray(sets);
			
			for(int i = 0; i < filteredCandidates.size(); i++){
				StringSet s1 = sets[i];
				for(int j = i + 1; j < filteredCandidates.size(); j++){
					StringSet s2 = sets[j];
					StringSet union = new StringSet();
					union.addAll(s1);
					union.addAll(s2);
					
					// Only add those of the correct size
					if(union.size() == k){
						candidates.add(union);
					//	System.out.println(union+"\n");
					}
				}
			}
			
		}
	//	System.out.println(candidates);
		return candidates;
	}

	/**
	 * Calculates the support value of each set in candidates.
	 * @param candidates The set of candidate sets.
	 * @param k The size of the candidates.
	 * @return A mapping of sets to support value.
	 */
	public Map<StringSet, Integer> countCandidates(Set<StringSet> candidates, int k) {
		// the result
		Map<StringSet, Integer> candidatesCount = new HashMap<StringSet, Integer>();

		
		for(StringSet candidate: candidates){
			int support = 0;
			//Get all subsets
			Set<StringSet> subsets = getSubsets(candidate, k-1);
			
			//Count how many times the candidate's k-1 subsets appear in the baskets
			for (Set<String> basket: baskets) {
//				System.out.println(basket);
				for(Set<String> subset: subsets){
//					System.out.println(" Locate: " + subset);
					if(basket.containsAll(subset)){
						support++;
//						System.out.println("incremented support. Found subset " + subset);
					}
				}	
			}
			candidatesCount.put(candidate, support);	
			//System.out.print(" total count = " + support + "\n");
		}
		
		return candidatesCount;
	}


	/**
	 * Removes those candidates that have a support value lower than supportThreshold.
	 * @param candidatesCount The map of sets to support value.
	 * @return A set of filtered candidates.
	 */
	public Set<StringSet> filterCandidates(Map<StringSet, Integer> candidatesCount) {
		// the result
		Set<StringSet> filteredCandidates = new HashSet<StringSet>();

		//Check all candidates and only add them if their count is higher than the support threshold.
		for(StringSet candidate: candidatesCount.keySet()){
//			System.out.println(candidatesCount.get(candidate));
			if(candidatesCount.get(candidate) >= supportThreshold){	
//				System.out.println("added to frequent items");
				filteredCandidates.add(candidate);
			}
		}

		return filteredCandidates;
	}

	/**
	 * Calculates frequent sets of size k from the baskets.
	 * @param k The size of the frequent sets.
	 * @return Set of frequent itemsets with size k
	 */
	public Set<StringSet> getFrequentSets(int k) {
		// the result
		Set<StringSet> filteredCandidates = null;

		for(int i = 1; i <= k; i++){
			filteredCandidates = constructCandidates(filteredCandidates, i);
			Map<StringSet, Integer> counts = countCandidates(filteredCandidates, i);
			filteredCandidates = filterCandidates(counts);
			System.out.println(filteredCandidates);			
		}
		return filteredCandidates;
	}


}
