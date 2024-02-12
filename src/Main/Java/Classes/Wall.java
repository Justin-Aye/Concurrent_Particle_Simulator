package Main.Java.Classes;

public class Wall {
    private int X1;
    private int X2;
    private int Y1;
    private int Y2;
    private final float angle;
    public Wall(int x1, int x2, int y1, int y2){
        X1 = x1;
        X2 = x2;
        Y1 = y1;
        Y2 = y2;

        angle = (float) (Math.atan2((y2 - y1), (x2 - x1)));
    }

    public boolean is_Colliding(int x, int y){
        float d1 = (float) (Math.sqrt(Math.pow(X1 - x, 2) + (Math.pow(Y1 - y, 2))));      // A to C

        float d2 = (float) (Math.sqrt(Math.pow(X2 - x, 2) + (Math.pow(Y2 - y, 2))));      // B to C

        float d3 = (float) (Math.sqrt(Math.pow(X1 - X2, 2) + (Math.pow(Y1 - Y2, 2))));    // A to B

        return ((d1 + d2) >= d3*0.999 && (d1 + d2) <= d3*1.005);
    }

    public int getX1() {
        return X1;
    }

    public int getX2() {
        return X2;
    }

    public int getY1() {
        return Y1;
    }

    public int getY2() {
        return Y2;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Wall){
            return (((Wall) obj).X1==X1 && ((Wall) obj).Y1==Y1 && ((Wall) obj).X2==X2 && ((Wall) obj).Y2==Y2);
        }
        return false;
    }
}
