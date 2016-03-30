package com.blackwolves.subscriber;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.blackwolves.seeder.YahooRunnable;

/**
 * @author daniel.grane
 *
 */
public class SubscriberRunnable {

	private static final Logger logger = LoggerFactory.getLogger(SubscriberRunnable.class);

	public SubscriberRunnable() {
	}

	public static void main(String[] args) {
		logger.info("Starting Subscriber...");
		int index = Integer.parseInt(args[0]);
		List<String[]> seeds = YahooRunnable.generateSeedsList("subscriber.csv");
		ExecutorService executor = null;
		for (int i = index; i < seeds.size() - 1; i++) {
			executor = Executors.newFixedThreadPool(10);
			logger.info("Count is: " + i);
			subscribeToNewsletters(seeds, i, executor);
			i = i + 10;
			if (executor != null) {
				executor.shutdown();
				while (!executor.isTerminated()) {

				}
			}

		}
		logger.info("Finished all threads");

		
	}

	private static void subscribeToNewsletters(List<String[]> seeds, int index, ExecutorService executor) {
		int limit = index + 10;
		for (int i = index; i <= limit; i++) {
			String[] seed = seeds.get(i);
			MDC.put("logFileName", seed[0]);
			Subscriber subscriber = new Subscriber(seed);
			Runnable worker = subscriber;
			logger.info("Executing thread: " + i + " with seed: " + seed[0] + " " + seed[1]);
			executor.execute(worker);
		}
	}
}
