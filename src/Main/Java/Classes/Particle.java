package Main.Java.Classes;

public class Particle {
    private int x, y, diameter;
    private float x_speed, y_speed;
    private final float speed;
    private int angle;

    public Particle(float s, int a, int d){
        this.x = 200;
        this.y = 200;
        this.diameter = d;
        this.speed = s;
        this.angle = a % 360;

        this.x_speed = (float) (s * Math.cos(Math.toRadians(angle)));
        this.y_speed = (float) (s * Math.sin(Math.toRadians(angle)));
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
        this.x_speed = (float) (speed * Math.cos(Math.toRadians(angle)));
        this.y_speed = (float) (speed * Math.sin(Math.toRadians(angle)));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public float getX_speed() {
        return x_speed;
    }

    public void setX_speed(float x_speed) {
        this.x_speed = x_speed;
    }

    public float getY_speed() {
        return y_speed;
    }

    public void setY_speed(float y_speed) {
        this.y_speed = y_speed;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getFutureX(float dt){
        return (int) (this.x + x_speed*dt);
    }

    public int getFutureY(float dt){
        return (int) (this.y + y_speed*dt);
    }

    public void updateXY(){
        this.x = Math.round(x + x_speed);
        this.y = Math.round(y - y_speed);
    }
}
