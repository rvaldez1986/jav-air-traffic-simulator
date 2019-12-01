# jav-air-traffic-simulator
Java project to simulate airplane traffic using google unfolding maps

![atpimg](https://user-images.githubusercontent.com/19597283/51938530-5016eb00-23db-11e9-86df-a1097f9dc5fc.png)

This is a java based project for an airport demand estimator based on an airplane traffic simulator. It uses a google map and geolocation data. The project creates airplaneMarkers that constantly updated their position simmulating airplane flights from selected locations (airports). The program receives as a parameter the number of airplanes to simulate and randomly selects destinations for each airplane. The airplanes constanly move from one location to the other and depending on the direction of the trip (nort - south) or (south - north) the marker changes so that the airplane always faces his destination. Finally when one hovers on top of an airplane you see the airport where it started its flight and where it will end. And when one clicks on the airplane you see the trajectory line and the two airports as markers. Taking andvantage of the draw method and that it is updated constantly, when the map is rendered you see airplanes in motion.
For the implementation I first created a class that extends SimpleLinesMarker for the trajectory. This uses two coordinates instead of the simple point marker that uses only one.

Then I created a brand new Airplane Marker class for each airplane that based on the beginning and end locations, constantly updates its location . The draw method on the airplane class was conditional on the direction of the plane.

The location of each airplane changes based on a call to the java clock. I created both clicked and mouse moved functionalities so that its easier for the user to locate the plane trajectory on the map.
The airplane class and the trajectory class are dependent on each other. The airplane class has a getter that provides its current location. The program can support from 1 to 10,000 planes and some more. With more planes the program starts to get slow.

