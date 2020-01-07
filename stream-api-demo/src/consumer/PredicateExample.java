package consumer;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class PredicateExample {

	public static void main(String[] args) {
		IntPredicate evenPredicate = n -> n % 2 == 0;
		IntPredicate oddPredicate = n -> n % 2 != 0;

		IntStream.rangeClosed(1, 10).filter(evenPredicate).boxed().forEach(System.out::println);
		System.out.println("=====");
		IntStream.rangeClosed(1, 10).filter(oddPredicate).boxed().forEach(System.out::println);
	}

}
