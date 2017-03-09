package training;

public class Decorator_Wrapper {

    public static void main(String[] args) {
        Printer printer = new QuotesDecorator(
                new LeftBracketDecorator(
                        new RightBracketDecorator(
                                new SpecificPrinter("Привет")
                        )
                )
        );
        printer.print();
    }
}


interface Printer {
    void print();
}

class SpecificPrinter implements Printer {
    private String value;

    SpecificPrinter(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}

abstract class Decorator implements Printer {
    Printer component;

    Decorator(Printer component) {
        this.component = component;
    }
}

class QuotesDecorator extends Decorator {

    QuotesDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("\"");
        component.print();
        System.out.print("\"");
    }
}

class LeftBracketDecorator extends Decorator {

    LeftBracketDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("[");
        component.print();
    }
}

class RightBracketDecorator extends Decorator {

    RightBracketDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print() {
        component.print();
        System.out.print("]");
    }
}