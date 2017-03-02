package training;

public class Prototype {

    public static void main(String[] args) {
        Human origin = new Human("Vasya", 18);
        System.out.println(origin);

        Human copy = origin.copy();
        System.out.println(copy);

        HumanFactory factory = new HumanFactory(copy);

        System.out.println(factory.makeCopy());

        factory.setPrototype(new Human("Валерия", 30));
        System.out.println(factory.makeCopy());
    }
}

interface Copyable<T> {
    T copy();
}

class Human implements Copyable<Human> {
    private String name;
    private int age;

    Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Human copy() {
        return new Human(name, age);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class HumanFactory {
    private Human human;

    HumanFactory(Human human) {
        this.human = human;
    }

    void setPrototype(Human human) {
        this.human = human;
    }

    Human makeCopy() {
        return human.copy();
    }
}