package training;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FactoryMethod {

    public static void main(String[] args) {
        WatchMaker watchMaker = getMakerByName("Digital");
        Watch watch = watchMaker.createWatch();
        watch.showTime();

        watchMaker = getMakerByName("Rome");
        watch = watchMaker.createWatch();
        watch.showTime();

        watchMaker = getMakerByName("Other");
        watch = watchMaker.createWatch();
        watch.showTime();
    }

    private static WatchMaker getMakerByName(String maker) {
        switch (maker) {
            case "Digital":
                return new DigitalWatchMaker();
            case "Rome":
                return new RomeWatchMaker();
            default:
                throw new RuntimeException("Не поддерживается производственная линия часов: " + maker);
        }
    }
}

interface Watch {
    void showTime();
}

class DigitalWatch implements Watch {

    @Override
    public void showTime() {
        System.out.println("Электронные часы: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}

class RomeWatch implements Watch {

    @Override
    public void showTime() {
        System.out.println("Римские часы: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}

interface WatchMaker {
    Watch createWatch();
}

class DigitalWatchMaker implements WatchMaker {

    @Override
    public Watch createWatch() {
        return new DigitalWatch();
    }
}

class RomeWatchMaker implements WatchMaker {

    @Override
    public Watch createWatch() {
        return new RomeWatch();
    }
}