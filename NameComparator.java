import java.util.Comparator;

public class NameComparator implements Comparator&lt;Person&gt; {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1 == p2) return 0;
        if (p1 == null) return -1;
        if (p2 == null) return 1;
        return p1.getName().compareTo(p2.getName());
    }
}

