# MIPS32 Single Cycle IDE
This will be a single cycle 32 bit MIPS IDE.

## Project structure
Currently the project is segregated in:
- `App` folder - where lays the executable
- `Controller` folder
- `View` folder - responsible for the GUI

## Current status and work to do
At the moment we have a text editor which recognizes the changes of the Day/Night button state.

TODO:
- actually implement new colours for day/night modes
- functionality for saving the current file on the hard disk
- highlighting keywords (like MOV, ADD)
- display binary verion of the code
- compile and run the code
- simulator