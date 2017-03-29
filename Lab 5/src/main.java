
public class main {

	public static void main(String[] args) {
		APriori ap = new APriori(3);
		
		ap.addBasket("Cat and dog bites");
		ap.addBasket("Yahoo news claims a cat mated with a dog and produced viable offspring");
		ap.addBasket("Cat killer likely is a big dog");
		ap.addBasket("Professional free advice on dog training puppy training");
		ap.addBasket("Cat and kitten training and behavior");
		ap.addBasket("Dog & Cat provides dog training in Eugene Oregon");
		ap.addBasket("Dog and cat is a slang term used by police officers for a male female relationship");
		ap.addBasket("Shop for your show dog grooming and pet supplies");
		
		ap.getFrequentSets(3);
		
	}

}
