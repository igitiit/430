import java.util.Comparator;

public class MultiFieldComparator implements Comparator&lt;Person&gt; {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1 == p2) return 0;
        if (p1 == null) return -1;
        if (p2 == null) return 1;
        
        int ageCompare = Integer.compare(
            p1.getAge() != null ? p1.getAge() : Integer.MIN_VALUE,
            p2.getAge() != null ? p2.getAge() : Integer.MIN_VALUE
        );
        return ageCompare != 0 ? ageCompare : p1.getName().compareTo(p2.getName());
    }
}

