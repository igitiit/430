import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List&lt;Person&gt; people = Arrays.asList(
            new Person(3, "Charlie", 25),
            new Person(1, "Alice", 30),
            new Person(4, "Bob", null),
            new Person(2, "David", 20),
            new Person(5, null, 35)
        );

        // Natural ordering using Comparable (id)
        System.out.println("\nNatural ordering (id):");
        printSortedList(new ArrayList<>(people), null);

        // NameComparator
        System.out.println("\nSorted by name:");
        printSortedList(new ArrayList<>(people), new NameComparator());

        // MultiFieldComparator
        System.out.println("\nSorted by age then name:");
        printSortedList(new ArrayList<>(people), new MultiFieldComparator());

        // Java 8 Comparators
        System.out.println("\nJava 8 implementations:");
        Comparator&lt;Person&gt; modernNameComparator = Comparator
            .comparing(Person::getName, Comparator.nullsLast(String::compareTo));
            
        Comparator&lt;Person&gt; modernAgeNameComparator = Comparator
            .comparing(Person::getAge, Comparator.nullsFirst(Integer::compare))
            .thenComparing(Person::getName, Comparator.nullsLast(String::compareTo));

        System.out.println("Sorted by name (modern):");
        printSortedList(new ArrayList<>(people), modernNameComparator);

        System.out.println("Sorted by age then name (modern):");
        printSortedList(new ArrayList<>(people), modernAgeNameComparator.reversed());
    }

    private static void printSortedList(List&lt;Person&gt; people, Comparator&lt;Person&gt; comparator) {
        System.out.println("Original: " + people);
        if (comparator == null) {
            Collections.sort(people);
        } else {
            people.sort(comparator);
        }
        System.out.println("Sorted:   " + people);
    }
}

