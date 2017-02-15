package training;

public class Facade {

    public static void main(String[] args) {
        new Computer().copy();
    }
}

class Computer {
    private Power power = new Power();
    private DVDRom dvd = new DVDRom();
    private HDD hdd = new HDD();

    void copy() {
        power.on();
        dvd.unload();
        hdd.copyFromDVD(dvd);
        dvd.load();
        hdd.copyFromDVD(dvd);
        power.off();
    }
}

class Power {
    void on() {
        System.out.println("Включение питания");
    }

    void off() {
        System.out.println("Выключение питания");
    }
}

class DVDRom {
    private boolean data;

    boolean hasData() {
        return data;
    }

    void load() {
        data = true;
    }

    void unload() {
        data = false;
    }
}

class HDD {
    void copyFromDVD(DVDRom dvd) {
        if (dvd.hasData()) {
            System.out.println("Происходит копирование данных с диска");
        } else {
            System.out.println("Вставьте диск с данными");
        }
    }
}