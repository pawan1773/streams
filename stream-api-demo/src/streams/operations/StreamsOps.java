package streams.operations;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsOps {

	public static void main(String[] args) {
		final List<Integer> numbers = List.of(1, 3, 2, 4, 2, 3, 4, 4, 4, 3);

		// Sort a list of number in reverse order and join by commas
		String reversed = numbers.stream().sorted(Comparator.reverseOrder()).map(n -> n.toString())
				.collect(Collectors.joining(", "));
		System.out.println("Reversed: [ " + reversed + " ]");
		System.out.println("==========================================");

		// Sum using reduce
		Integer reduceSum = numbers.stream().reduce(0, Integer::sum);
		System.out.println("Sum using reduce: " + reduceSum);
		System.out.println("==========================================");

		// Sum by changing to primitive
		int sum = numbers.stream().mapToInt(s -> s).sum();
		System.out.println("Primitive sum: " + sum);
		System.out.println("==========================================");

		// Group by identity and find count
		Map<Integer, Long> groupByIdentity = numbers.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println("Group by identity: " + groupByIdentity);
		System.out.println("==========================================");

		List<Person> persons = List.of(new Person("male", "Pawan"), new Person("female", "Manmeet"),
				new Person("male", "Prabh"), new Person("male", "Joginder"), new Person("female", "Richa"),
				new Person("male", "Navi"), new Person("female", "Sukhman"), new Person("female", "Tina"),
				new Person("female", "Kuldip"));

		// Group by gender and find count
		Map<String, Long> map = persons.stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
		map.forEach((k, v) -> System.out.println("key => " + k + ", count => " + v));
		System.out.println("==========================================");

		// Group by gender and get max
		Map<String, Optional<Person>> groupByAndMaxBy = persons.stream().collect(Collectors
				.groupingBy(Person::getGender, Collectors.maxBy((p1, p2) -> p1.getGender().compareTo(p2.getGender()))));
		System.out.println("Group by and max by: " + groupByAndMaxBy);

		// Group by gender
		Map<String, List<Person>> groupByGender = persons.stream().collect(Collectors.groupingBy(Person::getGender));
		groupByGender.forEach((k, v) -> System.out.println("key => " + k + ", person => " + v));
		System.out.println("==========================================");

		// Map person stream to list of names, convert to upper case and join
		String join = persons.stream().map(Person::getName).map(String::toUpperCase).collect(Collectors.joining(" | "));
		System.out.println(join);
		System.out.println("==========================================");
	}

}

class Person {
	private String gender;
	private String name;

	public Person(String gender, String name) {
		super();
		this.gender = gender;
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [gender=" + gender + ", name=" + name + "]";
	}

}