# Particle Simulator with Explorer Mode
A Java program that simulates particles within a graphical user interface (UI), offering two distinct modes of interaction. In DEVELOPER MODE, users can dynamically add particles to the simulation. In EXPLORER MODE, users can navigate and explore the simulated environment using a sprite. This project is for the partial completion of the course STDISCM at De La Salle University. The course is headed by and submitted to Sir Ryan Austin Fernandez.

## Specifications
A user should be able to add particles to the environment with an initial position (x, y), an initial angle Θ (0 degrees is east, and degrees increase in an anticlockwise manner, e.g., 90 degrees is north), and a velocity V (in pixel per second). The particle will travel in a straight line, bouncing off the four walls of the canvas. Particles do not collide with other particles. All collisions are elastic, which means particles do not slow down or speed up after a collision. The canvas should be 1280x720 pixels. Coordinate (0,0) is the southwest corner of the canvas. Coordinate (1280,720) is the northeast corner.

Particles can be added in batches. This is in three forms:
- Provide an integer n indicating the number of particles to
add. Keep the velocity and angle constant. Provide a start
point and end point. Particles are added with a uniform
distance between the given start and end points.
- Provide an integer n indicating the number of particles to
add. Keep the start point and velocity constant. Provide a
start Θ and end Θ. Particles are added with uniform distance
between the given start Θ and end Θ.
- Provide an integer n indicating the number of particles to
add. Keep the start point and angle constant. Provide a start
velocity and end velocity. Particles are added with a uniform
difference between the given start and end velocities.
Additionally, a user can also add walls, given two endpoints
(x1, y1) and (x2, y2), which the particles will also bounce off
of.

The user can also switch to "explorer mode" by clicking the "Enter Explorer Mode" button located at the bottom right of the screen. A sprite will spawn in the space (at a location of your choosing), and the user can control this sprite using WASD, or arrow keys. The canvas used for viewing developer mode will not transform into a zoomed in version of the sprite's periphery. The dimensions of the periphery are 19 rows by 33 columns, with the sprite rendered in the very center. As the sprite moves around the space, any balls/particles that enter its periphery are rendered in motion on the screen until they leave the periphery. 

For "Developer Mode", ensure that your screen resolution is high enough to show all particles on-screen. 
For "Explorer Mode", you only need to ensure you can display all the particles in the sprite's periphery.
Show the FPS counter on-screen every 0.5 seconds.

## Running Locally
1. Clone the repository 
```bash
git clone https://github.com/Justin-Aye/Concurrent_Particle_Simulator.git
```
2. Open the project in your preferred IDE
3. Run the Main.java file
