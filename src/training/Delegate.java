package training;

public class Delegate {

    public static void main(String[] args) {
        Painter painter = new Painter();

        painter.setGraphics(new Triangle());
        painter.draw();

        painter.setGraphics(new Square());
        painter.draw();

        painter.setGraphics(new Circle());
        painter.draw();
    }
}

interface Graphics {
    void draw();
}

class Triangle implements Graphics {
    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }
}

class Circle implements Graphics {
    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }
}

class Square implements Graphics {
    @Override
    public void draw() {
        System.out.println("Рисуем квадрат");
    }
}

class Painter {
    private Graphics graphics;

    void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    void draw() {
        graphics.draw();
    }
}