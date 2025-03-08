import java.util.Objects;

public class Person implements Comparable&lt;Person&gt; {
    private final int id;
    private final String name;
    private final Integer age;

    public Person(int id, String name, Integer age) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Integer getAge() { return age; }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

