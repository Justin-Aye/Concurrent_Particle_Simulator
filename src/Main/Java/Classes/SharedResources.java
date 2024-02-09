package Main.Java.Classes;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SharedResources {
    private final ArrayList<Particle> particles;
    private final ArrayList<Wall> walls;
    private Semaphore lock;

    public SharedResources(){
        particles = new ArrayList<>();
        walls = new ArrayList<>();
    }

    public synchronized ArrayList<Particle> getParticles() {
        return particles;
    }

    public synchronized ArrayList<Wall> getWalls() {
        return walls;
    }

    public synchronized void Add_Particle(Particle p){
        this.particles.add(p);
    }

    public synchronized void Clear_Particles(){
        this.particles.clear();
        System.gc();
    }

    public synchronized void Add_Wall(Wall w){
        if(!walls.contains(w))
            this.walls.add(w);
    }

    public synchronized void Clear_Walls(){
        this.walls.clear();
        System.gc();
    }
}
