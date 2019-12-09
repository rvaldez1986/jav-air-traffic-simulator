# jav-air-traffic-simulator
Java project to simulate airplane traffic using google unfolding maps

![atpimg](https://user-images.githubusercontent.com/19597283/51938530-5016eb00-23db-11e9-86df-a1097f9dc5fc.png)

### About

This program has the following capabilities:

* It uses a google map and geolocation data for stablishing airport and aiplanes real time positions.
* The program uses a variable that stablishes the number of airplanes to simulate.
* Each instance of the airplane object has its own speed and direction as parameters.
* The location of each airplane changes based on a call to the java clock.
* Taking andvantage of the draw method and the constant update in rendering, the airplanes appear in constant motion.
* When one clicks on an airplane, the trajectory line and the two airport destinations become visible as markers.
* The program can support from 1 to 10,000 planes and some more. With more planes the program starts to get slow.


