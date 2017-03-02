package training;

import java.util.ArrayList;
import java.util.List;

public class Composite {

    public static void main(String[] args) {
        Composite_ composite1 = new Composite_();
        Composite_ composite2 = new Composite_();

        Shape circle1 = new Circle_();
        Shape triangle1 = new Triangle_();
        Shape square1 = new Square_();

        Shape circle2 = new Circle_();
        Shape triangle2 = new Triangle_();
        Shape square2 = new Square_();

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

class Square_ implements Shape {

    @Override
    public void draw() {
        System.out.println("Привет, я - Квадрат");
    }
}

class Triangle_ implements Shape {

    @Override
    public void draw() {
        System.out.println("Привет, я - Треугольник");
    }
}

class Circle_ implements Shape {

    @Override
    public void draw() {
        System.out.println("Привет, я - Круг");
    }
}

class Composite_ implements Shape {
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