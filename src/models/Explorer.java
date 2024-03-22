package models;

public class Explorer {
    
    // Center Point
    private int center_x;
    private int center_y;

    // Periphery dimensions
    private final int PERIPHERY_WIDTH = 33;
    private final int PERIPHERY_HEIGHT = 19;

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
        this.screen_w = screen_w;
        this.screen_h = screen_h;
        // Adjusting coordinates such that (0,0) is at bottom-left corner
        this.center_x = start_x;
        this.center_y = screen_h - start_y;
    }

    public void move_left(){
        if(this.center_x > 0){
            this.center_x -= 1;
        }
    }

    public void move_right(){
        if(this.center_x < this.screen_w){
            this.center_x += 1;
        }
    }

    public void move_up(){
        if(this.center_y > 0){
            this.center_y -= 1;
        }
    }

    public void move_down(){
        if(this.center_y < this.screen_h){
            this.center_y += 1;
        }
    }

    public boolean isDetected(int x, int y, int diameter){
        return (x + diameter >= this.center_x - PERIPHERY_WIDTH / 2 && 
                x <= this.center_x + PERIPHERY_WIDTH / 2 &&
                y + diameter >= this.center_y - PERIPHERY_HEIGHT / 2 && 
                y <= this.center_y + PERIPHERY_HEIGHT / 2);
    }

    public int getV1_x() {
        return center_x - PERIPHERY_WIDTH / 2;
    }

    public int getV1_y() {
        return center_y - PERIPHERY_HEIGHT / 2;
    }

    public int getV2_x() {
        return center_x + PERIPHERY_WIDTH / 2;
    }

    public int getV2_y() {
        return center_y + PERIPHERY_HEIGHT / 2;
    }

    public int getCenter_x() {
        return center_x;
    }
    
    public int getCenter_y() {
        return center_y;
    }
}
