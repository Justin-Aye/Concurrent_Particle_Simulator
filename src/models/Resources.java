package models;

import java.util.concurrent.CopyOnWriteArrayList;

public class Resources {
    private final CopyOnWriteArrayList<Particle> particles;
    private final CopyOnWriteArrayList<Wall> walls;

    private int spriteX;
    private int spriteY;

    public Resources() {
        particles = new CopyOnWriteArrayList<Particle>();
        walls = new CopyOnWriteArrayList<Wall>();
        spriteX = 500;
        spriteY = 500;
    }

    public CopyOnWriteArrayList<Particle> getParticles() {
        return this.particles;
    }

    public CopyOnWriteArrayList<Wall> getWalls() {
        return this.walls;
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }

    public void addWall(Wall wall) {
        if (!walls.contains(wall)) {
            this.walls.add(wall);
        }
    }

    public void clearParticles() {
        this.particles.clear();
        System.gc();
    }

    public void clearWalls() {
        this.walls.clear();
        System.gc();
    }

    public void setSpriteSpawn(int x, int y) {
        this.spriteX = x;
        this.spriteY = y;
    }

    public void setSpriteSpawn(String x, String y) {
        this.spriteX = Integer.parseInt(x);
        this.spriteY = Integer.parseInt(y);
    }

    public int getSpriteX() {
        return this.spriteX;
    }

    public int getSpriteY() {
        return this.spriteY;
    }
}
