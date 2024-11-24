# A NOTE TO THE TA GRADING THIS

Hi! I've had some technical difficulties getting my project started as I was unable to log into my UBC GitHub account through VSCode and tried many different things to log in but after a few hours nothing worked. Because I wanted to get my project started, I just started it using my personal github account and so that is where all my commit history is. The repository for my project on my personal account can be found here:

https://github.com/wmostrenko/project-97652119.git

However I am unable to upload this link to PrairieLearn and so I've logged into my UBC GitHub account online and created a new repository (the one you are looking at now) and uploaded all the files from my most recent commit. Therefore all the code on this repository should be working just as intended however this repository won't have any of my commit history. Therefore when grading my commit history, if possible, I would very much so appreciate it if you could view my commit history using the repository from my personal account (linked above). I know there is probably a way to clone my history from my personal repository to my UBC one and I have tried for many hours but nothing has seemed to work. Appologies for the inconvinience!

# A Relativistic Visualization

## An Introduction

As both a computer science and physics student, I have taken many physics classes throughout my educational journey and have noticed the difficulties educators encounter when trying to teach conceptually-difficult concepts to a wide variety of students with different prefered learning styles. This personal project attempts to help visual-learners in introductory physics courses better grasp the concept of how the curving of spacetime by heavy masses translates to gravity. The core idea behind this application is to have saved "Simulations", where each Simulation is a playground for students to experiment with how masses in space effect oneanother and the spacetime around them. To do so, students will be able to add "Objects" (each with a mass and initial velocity) to a Simulation and see how the Objects effect oneanother gravitationally. Furthermore, students should be able to change between "Reference Frames" of Objects in each Simulaiton to better grasp the idea behind relativity and relativistic (potentially) addition of velocities. Finally, if there is time (as this part is quite ambitious), I would like each simulation to have a 2D "Grid" which represents spacetime. Thus when a student adds an Object to their Simulation, the object should warp the grid just as a planet would warp spacetime. 

## User Stories

### Phase 0

- As a user, I would like to create multiple Simulations.
- As a user, I would like to add multiple Objects to my Simulations and specify their mass, initial velocity, and position.
- As a user, I would like to view a list of my Objects in my Simulations.
- As a user, I would like to enter the Reference Frame of each object from my list of Objects in my Simulation.

### Phase 2

- As a user, I would like to have the option to save the state (Objects and their respective properties) of my Simulations.
- As a user, I would like to load a previoulsy saved Simulation and continue observing/playing where I previously left off.

## Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by selecting the "Add Random Objects" button in the main window. This open a window where you can specify the number of objects to generate, then generate them by pressing the "Add Random Objects Button".
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting the "Change Reference Frames" button in the main window. This will open a window with a list of buttons that correspond to each object in your simulation. When you click on one of these buttons, you will enter the reference frame of that object (the stationary reference frame is included in this!).
- You can locate my visual component by observing the simulation panel on the left-hand side of the main window (note that you can only see objects if you add them to the simulation!).
- You can save the state of my application by selecting the "Save Simulation" button in the command bar in the main window. Pressing this will save your simulation to a file with the name of your simulation.
- You can reload the state of my application by selecting the "Load Simulation" button in the command bar of the main window. Doing so will open a new window where you can type the name of your previous simulation. Once you do so, clicking the "Load Simulation" button will open a new main window linked to the simulation whose name you typed!

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

## Phase 4: Task 2

Sun Nov 24 13:28:45 PST 2024
Added new object to simulation.
Sun Nov 24 13:28:50 PST 2024
Removed object from simulation.
Sun Nov 24 13:29:01 PST 2024
Added new object to simulation.
Sun Nov 24 13:29:03 PST 2024
Changed reference frame.
Sun Nov 24 13:29:10 PST 2024
Changed simulation time step.
Sun Nov 24 13:29:14 PST 2024
Changed simulation name.
Sun Nov 24 13:29:17 PST 2024
Saved simulation.

**A NOTE TO THE TA**
Hello! As you may have noticed, my Event and EventLog classes aren't in my model package! This was intentional as since my model package has a class named Object (in hind-sight, this was a pretty bad move on my part). So if Event/EventLog were in model, their references the standard java class "Object" would try and reference my model class "Object" and things would break. Not sure how else I could fix this so I just decided I had to put the Event/EventLog classes in a separate package.

## Phase 4: Task 3

Looking at my UML Design Diagram, I feel I have developed a pretty clean program. Initially, all my UI SubWindows were just individual classes and I didn't have the SubWindow, TextAndButtonWindow, and ButtonWindow classes which would have made this diagram a disaster so I'm very glad I was able to refactor my code before the end of Phase 3. In terms of future refactoring that I would do if I had more time, I would likely use a better computational physics method than Euler's method as once around 200 objects are added to the simulation, it gets very very slow. But if I were to refactor my Simulation and Object classes using different methods (likely using matricies and computaitonal physics optimization techniques), I would be able to add far more obejcts to my simulations before the program slows down, and I would also likely be able to draw future object trajectories.