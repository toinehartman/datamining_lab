import java.util.List;
import java.util.Set;

public class main {
	
	public static void exercise1_1() {
		// ADD CODE HERE
		System.out.println("EXERCISE 1_1");

		String plane = "The plane was ready for touch down";
		String quarterback = "The quarterback scored a touchdown";

		ShingleSet s1 = new ShingleSet(5);
		ShingleSet s2 = new ShingleSet(5);
		ShingleSet s3 = new ShingleSet(5);
        ShingleSet s4 = new ShingleSet(5);

		s1.shingleString(plane);
		s3.shingleStrippedString(plane);
		s2.shingleString(quarterback);
		s4.shingleStrippedString(quarterback);

        System.out.println(String.format("s1: %s", s1));
        System.out.println(String.format("s2: %s", s2));
        System.out.println(String.format("s3: %s", s3));
        System.out.println(String.format("s4: %s", s4));

        System.out.println(String.format("Jaccard distance (regular): %f", s1.jaccardDistance(s2)));
        System.out.println(String.format("Jaccard distance (stripped): %f", s3.jaccardDistance(s4)));

        System.out.println();
    }

	public static void exercise1_2() {
		// ADD CODE HERE
        System.out.println("EXERCISE 1_2");

        MinHash mh = new MinHash();
        ShingleSet s1 = new ShingleSet(1);
        ShingleSet s2 = new ShingleSet(1);
        ShingleSet s3 = new ShingleSet(1);
        ShingleSet s4 = new ShingleSet(1);

        s1.shingleString("ad");
        s2.shingleString("c");
        s3.shingleString("bde");
        s4.shingleString("acd");

//        mh.addHashFunction(new HashFunction(1, 1));
//        mh.addHashFunction(new HashFunction(3, 1));
        mh.addRandomHashFunctions(100);

        mh.addSet(s1);
        mh.addSet(s2);
        mh.addSet(s3);
        mh.addSet(s4);

        MinHashSignature mhs = mh.computeSignature();
//        System.out.println(mhs);

		exercise1_3(mh);
	}
	
	public static void exercise1_3(MinHash mh) {
		// ADD CODE HERE
        int bs = 1000;
        int r = 5;

        System.out.println("EXERCISE 1_3");

        Set<List<Integer>> candidates = LSH.computeCandidates(mh.computeSignature(), bs, r);
        System.out.println(candidates);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// exercise 1.1
		exercise1_1();
		
		// exercise 1.2
		exercise1_2();
	}

}
