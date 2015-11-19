package main;

import java.util.HashMap;
import java.util.Map;

public class CounterSingleton {

	private static CounterSingleton instance = null;
	private Map<String, Integer> counter;

	public CounterSingleton() {
		this.counter = new HashMap<String, Integer>();
		addShutDownHook();
	}

	public static CounterSingleton getInstance() {
		if (instance == null) {
			instance = new CounterSingleton();
		}
		return instance;
	}

	public void incrementMethodCount(String name) {
		if (this.counter.containsKey(name)) {
			int count = this.counter.get(name);
			this.counter.put(name, count + 1);

		} else {
			this.counter.put(name, 2);
		}
		System.out.println(this.counter.get("a"));
	}

	private void addShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new ShutDownClass(this.counter));
	}

	private class ShutDownClass extends Thread {
		private Map<String, Integer> counterMap;

		public ShutDownClass(Map<String, Integer> counterMap) {
			this.counterMap = counterMap;
		}

		@Override
		public void run() {
			System.out.println(this.counterMap);
		}
	}

}
