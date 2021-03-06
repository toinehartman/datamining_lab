import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class main {

	public static void main(String[] args) {
        PageRank pageRank = new PageRank();
        pageRank.importData("data/example.txt");
        System.out.println(pageRank.calculatePageRank(10));

		PageRank pageRank2 = new PageRank();
		pageRank2.importData("data/example2.txt");
		System.out.println(pageRank2.calculatePageRank(10));
		
		TaxationPageRank pageRankTax2 = new TaxationPageRank(0.8);
		pageRankTax2.importData("data/example2.txt");
		System.out.println(pageRankTax2.calculatePageRank(10));

		TaxationPageRank flightRank = new TaxationPageRank(0.8);
		flightRank.importData("data/flight_data.txt");
		System.out.println(sortByValues(flightRank.calculatePageRank(10)));
	}

	/*
	 * Java method to sort Map in Java by value e.g. HashMap or Hashtable
	 * throw NullPointerException if Map contains null values
	 * It also sort values even if they are duplicates
	 */
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
		List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {
			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		//LinkedHashMap will keep the keys in the order they are inserted
		//which is currently sorted on natural ordering
		Map<K,V> sortedMap = new LinkedHashMap<K,V>();

		for(Map.Entry<K,V> entry: entries){
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
