import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SON {
	public static int supportThreshold = 3;

	/**
	 * The first map class performs the first pass of the SON algorithm
	 */
	public static class FirstMap extends Mapper<LongWritable, Text, Text, IntWritable> {
		/**
		 * A writable 1. Needed for the MapReduce framework.
		 */
		private final static IntWritable one = new IntWritable(1);

		/**
		 * The APriori helper object.
		 */
		private APriori ap = new APriori(supportThreshold);

		/**
		 * The (first) map function. This function loads lines in the main memory and computes
		 * frequent doubletons when all data is received.
		 */
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			// add code here
		}
	}

	/**
	 * The first reduce class just passes on the data.
	 */
	public static class FirstReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		/**
		 * The (first) reduce function. This function just passes on the data.
		 */
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			// add code here
		}
	}

	/**
	 * The method used to set up the job for the first pass.
	 * @param input The input directory.
	 * @param output The output directory.
	 */
	public static void firstPass(String input, String output) throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "SON - Pass 1");
		job.setJarByClass(SON.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(FirstMap.class);
		job.setCombinerClass(FirstReduce.class);
		job.setReducerClass(FirstReduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));

		job.waitForCompletion(true);
	}

	/**
	 * The second map class.
	 */
	public static class SecondMap extends Mapper<LongWritable, Text, Text, IntWritable> {
		/**
		 * A set of (candidate) doubletons. These get read from the output file of the first map function.
		 */
		private Set<StringSet> pairs = null;

		/**
		 * The hash map of counted doubletons.
		 */
		private Map<StringSet, Integer> pairCount = null;

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			readPairs();
			pairCount = new HashMap<StringSet, Integer>();
		}

		/**
		 * Parses the output of the previous MapReduce job.
		 */
		public void readPairs() {
			try {
				BufferedReader br = new BufferedReader(new FileReader("first_output/part-r-00000"));
				pairs = new HashSet<StringSet>();

				// every line is in the form of "[first, second]	1"

				// parse the file
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] split = line.split("(, )|(\\[)|(\\])");
					StringSet l = new StringSet();
					l.add(split[1]);
					l.add(split[2]);
					pairs.add(l);
				}

				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * The (second) map function. This function takes as input the result from the first
		 * MapReduce job and a chunk of the data. For each pair in the data it increases the
		 * count in pairCount and when all data is processed, it sends out all pairs with their count.
		 */
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			Set<String> uniqueWords = new HashSet<String>(Arrays.asList(line.toLowerCase().split(" ")));

			// add code here
		}
	}

	/**
	 * The second reduce class.
	 */
	public static class SecondReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		/**
		 * The (second) reduce function. This function counts the sum of all support values for all received pairs.
		 */
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			// add code here
		}
	}

	/**
	 * The method that handles setting up the job for the second MapReduce task.
	 * @param input
	 * @param output
	 */
	public static void secondPass(String input, String output) throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "SON - Pass 2");
		job.setJarByClass(SON.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(SecondMap.class);
		job.setCombinerClass(SecondReduce.class);
		job.setReducerClass(SecondReduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));

		job.waitForCompletion(true);
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 4)
		{
			System.err.println("Please use the supplied Run configuration for running this exercise, or construct your own.");
			return;
		}

		SON.supportThreshold = Integer.parseInt(args[3]);
		firstPass(args[0], args[1]);
		secondPass(args[0], args[2]);
	}
}