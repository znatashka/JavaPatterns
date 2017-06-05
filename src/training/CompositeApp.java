package training;

import java.util.ArrayList;
import java.util.List;

public class CompositeApp {

    public static void main(String[] args) {
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        Shape circle1 = new CircleC();
        Shape triangle1 = new TriangleC();
        Shape square1 = new SquareC();

        Shape circle2 = new CircleC();
        Shape triangle2 = new TriangleC();
        Shape square2 = new SquareC();

        composite2.addComponent(triangle2);
        composite2.addComponent(square2);
        composite2.addComponent(circle2);

        composite1.addComponent(circle1);
        composite1.addComponent(square1);
        composite1.addComponent(triangle1);
        composite1.addComponent(composite2);

        composite1.draw();

        System.out.println("------------------------");

        composite1.removeComponent(circle1);
        composite2.removeComponent(triangle2);

        composite1.draw();
    }
}

interface Shape {
    void draw();
}

class SquareC implements Shape {

    @Override
    public void draw() {
        System.out.println("Привет, я - Квадрат");
    }
}

class TriangleC implements Shape {

    @Override
    public void draw() {
        System.out.println("Привет, я - Треугольник");
    }
}

class CircleC implements Shape {

    @Override
    public void draw() {
        System.out.println("Привет, я - Круг");
    }
}

class Composite implements Shape {
    private List<Shape> components = new ArrayList<>();

    void addComponent(Shape component) {
        components.add(component);
    }

    void removeComponent(Shape component) {
        components.remove(component);
    }

    @Override
    public void draw() {
        components.forEach(Shape::draw);
    }
}