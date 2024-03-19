package models;

public class Explorer {
    
    // Center Point
    private int center_x;
    private int center_y;

    // Top Left Vertex Point
    private int v1_x;
    private int v1_y;

    // Bottom Right Vertex Point
    private int v2_x;
    private int v2_y;

    private int screen_w;
    private int screen_h;

    /**
     * The constructor for the Explorer
     * @param start_x - Starting x position of the Explorer
     * @param start_y - Starting y position of the Explorer
     * @param screen_w - Width of Screen
     * @param screen_h - Height of Screen
     */
    public Explorer(int start_x, int start_y, int screen_w, int screen_h){
        this.center_x = start_x;
        this.center_y = start_y;

        this.v1_x = start_x - 16;
        this.v1_y = start_y - 9;

        this.v2_x = start_x + 16;
        this.v2_y = start_y + 9;

        this.screen_w = screen_w;
        this.screen_h = screen_h;
    }

    public void move_left(){
        if(this.center_x > 0){
            this.center_x -= 1;
            this.v1_x -= 1;
            this.v2_x -= 1;
        }
    }

    public void move_right(){
        if(this.center_x < this.screen_w){
            this.center_x += 1;
            this.v1_x += 1;
            this.v2_x += 1;
        }
    }

    public void move_up(){
        if(this.center_y > 0){
            this.center_y -= 1;
            this.v1_y -= 1;
            this.v2_y -= 1;
        }
    }

    public void move_down(){
        if(this.center_y < this.screen_h){
            this.center_y += 1;
            this.v1_y += 1;
            this.v2_y += 1;
        }
    }

    public boolean isDetected(int x, int y){
        return x > v1_x && x < v2_x && y > v1_y && y < v2_y;
    }

    public int getV1_x() {
        return v1_x;
    }

    public int getV1_y() {
        return v1_y;
    }

    public int getV2_x() {
        return v2_x;
    }

    public int getV2_y() {
        return v2_y;
    }

    public int getCenter_x() {
        return center_x;
    }
    
    public int getCenter_y() {
        return center_y;
    }
}
