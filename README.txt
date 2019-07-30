MARS SIMULATOR PROJECT

Purpose of the project is to explore the explorer on a table top of size 5X5. The project includes the source code, unit test samples. 
The compiled version of the project is also attached with it, for easy execution. The compiled version is in the 'lib' folder and to run
it you just need to run the script from the source folder[./runsimulator.sh].

Terms Used in project:-

Canvas is used for table top(com/ove/simulator/Canvas.java).
Coordinates is used for the axis points(com/ove/simulator/Coordinates.java).
InputCommands for the input parameters contains the name of the command and the axis point(com/ove/simulator/InputCommands.java).

'PLACE', 'BLOCK', 'EXPLORER' and 'REPORT' are the type of commands.

'PLACE' and 'BLOCK' has the xAxis and yAxis which are used to populate on the canvas(com/ove/simulator/Canvas.java).

InputManager(com/ove/simulator/InputManager.java) is used to check and validation of the request.


-----------Requirements----------

 -java 7 or above.
 -Maven 

---------- Source Code build from terminal----------

mvn clean install -Dmaven.test.skip=true

This will comppile the source code and create a executable jar MarsSimulator-0.0.1-SNAPSHOT.jar under the target folder.


---copying dependencies-------------

mvn clean install -Dmaven.test.skip dependency:copy-dependencies

This command will create a copy of dependencies and put that in the lib folder.

--------Run JUnit test cases-----------

mvn test

------For JAVADOC Creation---------
mvn javadoc:javadoc


------------Run the project from terminal--------------- 

Run the script file from the source folder
./runsimulator.sh
