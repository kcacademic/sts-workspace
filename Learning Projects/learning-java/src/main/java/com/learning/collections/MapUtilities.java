package com.learning.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class MapUtilities {

	private static <K, V extends Comparable<V>> V getMax1(Map<K, V> map) {

		Map.Entry<K, V> maxEntry = null;

		for (Map.Entry<K, V> entry : map.entrySet()) {

			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

		return maxEntry.getValue();
	}

	private static <K, V extends Comparable<V>> V getMax2(Map<K, V> map) {

		Entry<K, V> maxEntry = Collections.max(map.entrySet(), new Comparator<Entry<K, V>>() {
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});

		return maxEntry.getValue();
	}

	private static <K, V extends Comparable<V>> V getMax3(Map<K, V> map) {

		Entry<K, V> maxEntry = Collections.max(map.entrySet(),
				(Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));

		return maxEntry.getValue();
	}
	
	private static <K, V extends Comparable<V>> V getMax4(Map<K, V> map) {

		Entry<K, V> maxEntry = Collections.max(map.entrySet(), Comparator.comparing(Map.Entry::getValue));

		return maxEntry.getValue();
	}

	private static <K, V extends Comparable<V>> V getMax5(Map<K, V> map) {

		Optional<Entry<K, V>> maxEntry = map.entrySet().stream()
				.max((Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue().compareTo(e2.getValue()));

		return maxEntry.get().getValue();
	}

	private static <K, V extends Comparable<V>> V getMax6(Map<K, V> map) {

		Optional<Entry<K, V>> maxEntry = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));

		return maxEntry.get().getValue();
	}

	public static void main(String[] args) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		map.put(1, 3);
		map.put(2, 4);
		map.put(3, 5);
		map.put(4, 6);
		map.put(5, 7);

		System.out.println(getMax1(map));
		System.out.println(getMax2(map));
		System.out.println(getMax3(map));
		System.out.println(getMax4(map));
		System.out.println(getMax5(map));
		System.out.println(getMax6(map));

	}

}
