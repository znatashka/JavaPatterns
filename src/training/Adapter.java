package training;

public class Adapter {

    public static void main(String[] args) {
        // через наследование
        VectorGraphics vg = new VectorAdapterFromRaster();
        vg.drawLine();
        vg.drawSquare();

        // через композицию
        VectorGraphics vg2 = new VectorAdapterFromRaster2();
        vg2.drawLine();
        vg2.drawSquare();
    }
}

interface VectorGraphics {
    void drawLine();

    void drawSquare();
}

class RasterGraphics {
    void drawRasterLine() {
        System.out.println("Рисуем линию");
    }

    void drawRasterSquare() {
        System.out.println("Рисуем квадрат");
    }
}

class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphics {

    @Override
    public void drawLine() {
        drawRasterLine();
    }

    @Override
    public void drawSquare() {
        drawRasterSquare();
    }
}

class VectorAdapterFromRaster2 implements VectorGraphics {

    private RasterGraphics rasterGraphics = new RasterGraphics();

    @Override
    public void drawLine() {
        rasterGraphics.drawRasterLine();
    }

    @Override
    public void drawSquare() {
        rasterGraphics.drawRasterSquare();
    }
}
