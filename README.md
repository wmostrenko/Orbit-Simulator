# A Relativistic Visualization

## An Introduction

As both a computer science and physics student, I have taken many physics classes throughout my educational journey and have noticed the difficulties educators encounter when trying to teach conceptually-difficult concepts to a wide variety of students with different prefered learning styles. This personal project attempts to help visual-learners in introductory physics courses better grasp the concept of how the curving of spacetime by heavy masses translates to gravity. The core idea behind this application is to have saved "Simulations", where each Simulation is a playground for students to experiment with how masses in space effect oneanother and the spacetime around them. To do so, students will be able to add "Objects" (each with a mass and initial velocity) to a Simulation and see how the Objects effect oneanother gravitationally. Furthermore, students should be able to change between "Reference Frames" of Objects in each Simulaiton to better grasp the idea behind relativity and relativistic (potentially) addition of velocities. Finally, if there is time (as this part is quite ambitious), I would like each simulation to have a 2D "Grid" which represents spacetime. Thus when a student adds an Object to their Simulation, the object should warp the grid just as a planet would warp spacetime. 

## User Stories

### Phase 0

- As a user, I would like to create multiple Simulations.
- As a user, I would like to add multiple Objects to my Simulations and specify their mass and initial velocity.
- As a user, I would like to view a list of my Objects in my Simulations.
- As a user, I would like to enter the Reference Frame of each object from my list of Objects in my Simulation.
- As a user, I would like to change the speed/pause my Simulation.
- As a user, I would like to resize my window which will expand the Grid but keep all the UI elements the same size.

## Class Documentation

### Simulation Class (very rough draft)

The current plan is to have the Simulation class (which has a list of Objects and a Grid) be where most of the backbone "computations" of the application occur. The Simulation class should be responsible for the following:
1. Updating the location of each object given the velocity of each Object.
1. Updating the velocity of each object given the acceleration of each Object.
2. Updating the acceleration on each Object given the locations of all the other Objects.
3. Updating the tensor object of the Grid given the current location and mass of each Object.
Each of the above will likely represent the main methods in the Simulation class. The Simulation class should represent the mathematical backbone of the application. Also, the Simulation class would always have an Object (unvisable to the user) that has 0 mass and is unaffected by gravity. This Object would be considered the Stationary Observer and will allow users to switch reference frames between Objects and the Stationary Observer (since both these things are really just Objects). The Stationary Observer will be initialized with 0 velocity, 0 mass, and a position centered in the window.

~~Furthermore, there should be something (I'm not sure whether it should be a class or method somewhere yet) that takes in a Simulation, and depending on the current reference frame (which corresponds to an the position and velocity of an Object), translates the positions/velocities of all the other Objects such that the Object that the reference frame is corresponding to has a centered position and a zero velocity. This would be the cleanest solution as there would be one main simulation (in the rest reference frame) where all the basic computation happens, then for each tick of the clock, the main simulaiton.~~

Another approach would be to have a "switch reference frames" method in the Simulation class that would be called by the user when they want to "switch reference frames." This method will take in an Object and point ReferenceFrame to that object. Then there will be another "update reference frame" method that will be called each time the simulation loops. This method will modify all other Objects' properties (position, velocity, acceleration) such that the ReferenceFrame (and in turn the object it points to) will now have 0 velocity, 0 acceleration, and a centred position. When the Simulation updates the position/velocity/accelerations, it will do so even to the ReferenceFrame, but immediatly after, will call this "update reference frame" method that will ensure the ReferenceFrame object still has 0 velocity, 0 acceleration, and a centred position.

