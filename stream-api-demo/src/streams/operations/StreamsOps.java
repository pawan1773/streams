package streams.operations;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsOps {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5);

		numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

		Integer sum = numbers.stream().reduce(0, Integer::sum);

		System.out.println("Sum ==> " + sum);

		List<Person> persons = List.of(new Person("male", "Pawan"), new Person("female", "Manmeet"),
				new Person("male", "Prabh"), new Person("male", "Joginder"), new Person("female", "Richa"),
				new Person("male", "Navi"), new Person("female", "Sukhman"), new Person("female", "Tina"),
				new Person("female", "Kuldip"));

		Map<Integer, Long> groups = numbers.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(groups);

		Map<String, Long> map = persons.stream()
				.collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
		map.forEach((k, v) -> System.out.println("key => " + k + ", count => " + v));

		System.out.println("=================");

		Map<String, Optional<Person>> maxBy = persons.stream().collect(Collectors.groupingBy(Person::getGender,
				Collectors.maxBy((p1, p2) -> p1.getGender().compareTo(p2.getGender()))));
		System.out.println(maxBy);

		Map<String, List<Person>> groupByGender = persons.stream().collect(Collectors.groupingBy(Person::getGender));
		groupByGender.forEach((k, v) -> System.out.println("key => " + k + ", person => " + v));

		String join = persons.stream().map(Person::getName).map(String::toUpperCase).collect(Collectors.joining(" | "));
		System.out.println(join);
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