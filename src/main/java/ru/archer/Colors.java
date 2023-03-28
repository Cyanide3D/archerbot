package ru.archer;

public enum Colors {

    INDIANRED(205, 92, 92),
    RED(255, 0, 0),
    CRIMSON(220, 20, 60),
    DEEPPINK(255, 20, 147),
    HOTPINK(255, 105, 180),
    PALEVIOLET(219, 112, 147),
    ORANGERED(255, 69, 0),
    ORANGE(255, 165, 0),
    KHAKI(240, 230, 140),
    INDIGO(75, 0, 130),
    TEAL(0, 128, 128),
    STEELBLUE(70, 130, 180),
    NAVY(0, 0, 128),
    CHOCOLATE(210, 105, 30),
    AZURE(240, 255, 255),
    MISTYROSE(255, 228, 225),
    WHITE(255, 255, 255),
    BLACK(0, 0, 0),
    LIMEGREEN(50, 205, 50),
    GREENYELLOW(173, 255, 47),
    DARKGREEN(0, 100, 0),
    BROWN(139, 69, 19),
    DARKSLATEGRAY(47, 79, 79),
    GRAY(128, 128, 128);

    int r, g, b;

    Colors(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
