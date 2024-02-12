package Main.Java.Classes;
import java.util.concurrent.CopyOnWriteArrayList;

public class SharedResources {
    private final CopyOnWriteArrayList<Particle> particles;
    private final CopyOnWriteArrayList<Wall> walls;
    
    public SharedResources(){
        particles = new CopyOnWriteArrayList<>();
        walls = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<Particle> getParticles() {
        return particles;
    }

    public CopyOnWriteArrayList<Wall> getWalls() {
        return walls;
    }

    public void Add_Particle(Particle p){
        this.particles.add(p);
    }

    public void Clear_Particles(){
        this.particles.clear();
        System.gc();
    }

    public void Add_Wall(Wall w){
        if(!walls.contains(w))
            this.walls.add(w);
    }

    public void Clear_Walls(){
        this.walls.clear();
        System.gc();
    }
}
