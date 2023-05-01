package HW_Stream_and_Optional;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MainInStream {
	public static void main(String[] args) {

		ArrayList<Double> list = getIntArray(15);


		//	Predicate

		for (Double d : list) {
			System.out.print(d + " " + new Predicate<Integer>() {
				@Override
				public boolean test(Integer integer) {
					return list.get(integer) > 0;
				}
			}.test(list.indexOf(d)));
			System.out.print("	|| lambda version -> " + d + " " + ((Predicate<Integer>) x -> list.get(x) < 0).test(list.indexOf(d)));
			System.out.println();
		}


		//	Consumer

		new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println("Hello, " + s + "!");
			}
		}.accept("Kostya");

		((Consumer<String>) s -> System.out.println("Hello, " + s + " by lambda!")).accept("Kostya");


		//	Function

		for (Double d : list) {
			System.out.print("Double: " + d + " -> Long: " + new Function<Double, Long>() {
				@Override
				public Long apply(Double aDouble) {
					return Math.round(aDouble);
				}
			}.apply(d));
			System.out.println();
		}

		for (Double d : list) {
			System.out.println(((Function<Double, Long>) Math::round).apply(d));
		}


		//	Supplier

		System.out.println("Случайное число от 0 до 100: " + new Supplier<Integer>() {
					@Override
					public Integer get() {
						return new Random().nextInt(0, 101);
					}
				}.get()
		);
		System.out.println("Случайное число от 0 до 100 (by lambda): " + ((Supplier<Integer>) () -> new Random().nextInt(0, 101)).get());


		//	ternaryOperator

	}

	private static ArrayList<Double> getIntArray(int size) {
		ArrayList<Double> list = new ArrayList<>();
		while (list.size() != size) list.add((double) Math.round(new Random().nextDouble(-100, 100)));
		return list;
	}

	private static <T, U> Function<T, U> ternaryOperator(
			Predicate<? super T> condition,
			Function<? super T, ? extends U> ifTrue,
			Function<? super T, ? extends U> ifFalse
	) {
		return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
	}
}