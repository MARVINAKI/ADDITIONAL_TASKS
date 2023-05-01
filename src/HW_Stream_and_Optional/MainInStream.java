package HW_Stream_and_Optional;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainInStream {
	public static void main(String[] args) {

		List<Integer> integerList = Arrays.asList(4, 3, 2, 7, 12, 45, 0, -2, 11, 11, 56, 56, -3, -2, 8, null);
		List<Integer> integersRandomList = Stream.generate(() -> new Random().nextInt(-100, 100)).distinct().limit(15).toList();
		System.out.println("Not sorted first list " + integerList);
		findMinMax(integerList.stream(), Comparator.naturalOrder(), (min, max) -> System.out.println(min + " " + max));
		System.out.println("Not sorter second list " + integersRandomList);
		findMinMax(integersRandomList.stream(), Comparator.naturalOrder(), (min, max) -> System.out.println(min + " " + max));

		findEvenNumber(integerList.stream());
//		ArrayList<String> list = new ArrayList<>();
//		list.add("d");
//		list.add("y");
//		list.add("c");
//		list.add("b");
//		list.add("e");
//		list.add("f");
//		list.add("z");
//		list.add("x");
//		list.add("a");
//
//		for (int i = 0; i < list.size(); i++) {
//			for (int j = i + 1; j < list.size(); j++) {
//				if (list.get(i).compareTo(list.get(j)) > 0) {
//					String temp = list.get(i);
//					list.set(i, list.get(j));
//					list.set(j, temp);
//				}
//			}
//		}
//		System.out.println(list);
//
//		Supplier<ArrayList<String>> arrayListSupplier1 = new Supplier<ArrayList<String>>() {
//			@Override
//			public ArrayList<String> get() {
//				return new ArrayList<>();
//			}
//		};
//		Supplier<ArrayList<String>> arrayListSupplier2 = () -> new ArrayList<>();
//		Supplier<ArrayList<String>> arrayListSupplier3 = ArrayList::new;
//
//		ArrayList<String> sortedList1 = list.stream().sorted().collect(Collectors.toCollection(arrayListSupplier1));
//		ArrayList<String> sortedList2 = (ArrayList<String>) list.stream().sorted().collect(Collectors.toList());
//		List<String> sortedList3 = list.stream().sorted().toList();
//		System.out.println(sortedList1);
//		System.out.println(sortedList2);
//		System.out.println(sortedList3);
//
//		List<Integer> intList = Arrays.asList(1, 2, 9, 8, 5, 0, null, 3, 7, 6, 4, 4);
//		ArrayList<Integer> checkedList = (ArrayList<Integer>) intList.stream().filter(x -> x != null).filter(x -> x % 2 == 0).collect(Collectors.toList());
//		ArrayList<Integer> checkedList1 = (ArrayList<Integer>) intList.stream().filter(Objects::nonNull).sorted().distinct().collect(Collectors.toList());
//		System.out.println(checkedList);
//		System.out.println(checkedList1);
	}

	public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> comparator, BiConsumer<? super T, ? super T> minMaxConsumer) {
		List<? extends T> list = stream.filter(Objects::nonNull).sorted(comparator).toList();
		minMaxConsumer.accept(
				list.isEmpty() ? null : list.stream().min(comparator).get(),
				list.isEmpty() ? null : list.stream().max(comparator).get()
		);
	}

	public static void findEvenNumber(Stream<Integer> stream) {
		List<Integer> list = stream.filter(Objects::nonNull).filter(x -> x % 2 == 0).toList();
		System.out.println("List of even numbers: " + list);
		System.out.println("Count of even numbers: " + list.size());
	}
}