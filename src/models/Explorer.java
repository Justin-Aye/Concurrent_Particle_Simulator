package models;

public class Explorer {
    
    private int x;
    private int y;
    private int x_margin;
    private int y_margin;

    /**
     * The constructor for the Explorer
     * @param start_x - Starting x position of the Explorer
     * @param start_y - Starting y position of the Explorer
     * @param screen_w - Width of Screen
     * @param screen_h - Height of Screen
     */
    public Explorer(int start_x, int start_y, int screen_w, int screen_h){
        this.x = start_x;
        this.y = start_y;
        
    }
}
