# Orbit Simulator (Euler's Method)

## An Introduction

As both a computer science and physics student, I have taken many physics classes throughout my educational journey and have noticed the difficulties educators encounter when trying to teach conceptually-difficult concepts to a wide variety of students with different prefered learning styles. This personal project attempts to help visual-learners in introductory physics courses better grasp the concept of how the curving of spacetime by heavy masses translates to gravity. The core idea behind this application is to have saved "Simulations", where each Simulation is a playground for students to experiment with how masses in space effect oneanother and the spacetime around them. To do so, students will be able to add "Objects" (each with a mass and initial velocity) to a Simulation and see how the Objects effect oneanother gravitationally. Furthermore, students should be able to change between "Reference Frames" of Objects in each Simulaiton to better grasp the idea behind relativity and relativistic (potentially) addition of velocities. Finally, if there is time (as this part is quite ambitious), I would like each simulation to have a 2D "Grid" which represents spacetime. Thus when a student adds an Object to their Simulation, the object should warp the grid just as a planet would warp spacetime. 

## Class Documentation (OUTDATED)

### Simulation Overview
Each `Simulation` is the mathematical backbone of the application. It will manage updating its `Object` elements (position, velocity, acceleration), updating the `Grid` tensor, and switching/maintaining the current reference frame. To do so, each Simulation will have the following elements:

1. `stationaryReferenceFrame` is of type `Object`. This represents the stationary reference frame in each simulation. Thus it is initialized with a centered position, 0 velocity, and 0 mass.
2. `currentReferenceFrame` is of type `Object`. This points to whatever `Object` the user has set as current reference frame that the user would like to enter. `currentReferenceFrame` is initialized to point to `stationaryReferenceFrame`.
3. `objects` is a list of `Object`. `objects` is initialized with the `stationaryReferenceFrame`.
4. `grid` is of type `Grid`. Since the design of `Grid` heavily depends on the design of the UI elements, this will not be implimented for Phase 1.

For `Simulation` to work as intended, it will have the following "main" methods:

1. `updateObject()` takes an `Object` and updates its elements using the following helper methods:
    1. `updateLocation()` takes an `Object` and updates its location using that object's current velocity.
    2. `updateVelocity()` takes an `Object` and updates its velocity using that object's current acceleration.
    3. `updateAcceleration()` takes an `Object` and updates its acceleration using that object's current acceleration and the acceleration applied by each `Object` in `objects`.
2. `switchReferenceFrame()` takes an `Object` and is called by the main method when the user wants to switch reference frames. It updates `currentReferenceFrame` by pointing it to whatever `Object` was called as a parameter.
3. `updateReferenceFrame()` takes no parameters and should call the following helper methods:
    1. `updateAllObjects()` which updates all `Object` in `objects`such that their elements are changed by the elements of `currentReferenceFrame`.
    2. `updateCurrentReferenceFrame` which resets the elements of `currentReferenceFrame` so it has a centered position, 0 velocity, and 0 acceleration.


### UI Overview
For Phase 1, the UI will be very simple. The goal for now is to provide a place to call the main methods in `Simulation` depending on what the user would like to do. The user should be prompted to create a new simulation or open an existing one (which we'll impliement in Phase 2). Then the simulation should run (by looping a series of methods in `Simulation`) and the user should be prompted to add an `Object` to their `Simulation`, or to get the elements of an `Object` in their `Simulation`. To do so, `UI` will need the following elements:

1. `simulations` which is a list of `Simulation`.
2. `runningSimulation` is of type `Boolean` and is true when a simulation is running. `runningSimulation` is initialized to false.
3. `runningProgram` is of type `Boolean` and is true when the program is running. `runningProgram` in initialized to true.

Furthermore, for `UI` to work as intended, the main method will be a loop that loops while `runningProgram` is true. In this loop, the user will be promted to either...

1. Create a new `Simulation`.
2. Open an existing `Simulation` from simulations.
3. Close the program.

If the user decides to open an existing `Simulation`, then the UI will prompt the user to choose a simulation. Once the user has choosen a simulation, the program will run `runSimulation()` which will take a `Simulation` as a parameter and is a loop that loops only while `runningSimulation` is true. For every loop, it prompts the user to either...

1. Add an `Object`.
2. Change reference frame.
3. Get the elements for an `Object`.
4. Update the `Simulation`.
5. Close the `Simulation`.


### Object Overview
An `Object` is simply and object in a `Simulation` (i.e. think of an `Object` in a `Simulation` like a planet in space). Objects have the following elements:

1. `xPosition` which is a number representing the x position of the object.
2. `yPosition` which is a number representing the y position of the object.
3. `xVelocity` which is a number representing the x velocity of the object.
4. `yVelocity` which is a number representing the y velocity of the object.
5. `xAcceleration` which is a number representing the x acceleration of the object. This is initialized as 0.
6. `yAcceleration` which is a number representing the y acceleration of the object. This is initialized as 0.
7. `mass` which is a number representing the mass of the object.
