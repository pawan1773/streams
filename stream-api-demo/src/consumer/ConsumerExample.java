package consumer;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerExample {

	public static void main(String[] args) {
		final Consumer<String> upperCaseConsumer = String::toUpperCase;
	
		final BiConsumer<Integer, Integer> addBiConsumer = (a, b) -> System.out.println(a + b);
		final BiConsumer<Integer, Integer> multiplyBiConsumer = (a, b) -> System.out.println(a * b);
		final BiConsumer<Integer, Integer> divBiConsumer = (a, b) -> System.out.println(a / b);
		
		// consumer chaining
		addBiConsumer.andThen(multiplyBiConsumer).andThen(divBiConsumer).accept(25, 5);

		final List<String> vowels = List.of("a", "e", "i", "o", "u");

		vowels.stream().forEach(upperCaseConsumer);
	}

}
