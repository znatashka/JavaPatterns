package training;

public class StateApp {

    public static void main(String[] args) {
        // Human
        HumanS human = new HumanS();
        human.setState(new Work());
        for (int i = 0; i < 10; i++) {
            human.doSomething();
        }

        System.out.println("###");

        // Radio
        Radio radio = new Radio();
        radio.setStation(new RadioDFM());

        for (int i = 0; i < 10; i++) {
            radio.play();
            radio.nextStation();
        }
    }
}

// Human
class HumanS {
    private Activity state;

    void setState(Activity state) {
        this.state = state;
    }

    void doSomething() {
        state.doSomething(this);
    }
}

interface Activity {
    void doSomething(HumanS context);
}

class Work implements Activity {

    @Override
    public void doSomething(HumanS context) {
        System.out.println("Работаем!!!");
        context.setState(new WeekEnd());
    }
}

class WeekEnd implements Activity {

    private int count = 0;

    @Override
    public void doSomething(HumanS context) {
        if (count < 3) {
            System.out.println("Отдыхаем [Zzz]");
            count++;
        } else {
            context.setState(new Work());
        }
    }
}

// Radio
interface Station {
    void play();
}

class Radio7 implements Station {

    @Override
    public void play() {
        System.out.println("Радио 7...");
    }
}

class RadioDFM implements Station {

    @Override
    public void play() {
        System.out.println("Радио DFM...");
    }
}

class VestiFM implements Station {

    @Override
    public void play() {
        System.out.println("Вести FM...");
    }
}

class Radio {

    private Station station;

    void setStation(Station station) {
        this.station = station;
    }

    void nextStation() {
        if (station instanceof Radio7) {
            setStation(new RadioDFM());
        } else if (station instanceof RadioDFM) {
            setStation(new VestiFM());
        } else if (station instanceof VestiFM) {
            setStation(new Radio7());
        }
    }

    void play() {
        station.play();
    }
}