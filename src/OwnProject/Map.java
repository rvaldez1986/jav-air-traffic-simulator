package OwnProject;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;


public class Map extends PApplet {
	
	UnfoldingMap map;
	MarkerManager<Marker> markerManager;
	AirplaneMarker airplaneManual;
	AirplaneMarker lastSelected;
	AirplaneMarker lastClicked;
	List<Marker> airplaneList;
	TrajectoryMarker line1;
	TrajectoryMarker line2;
	AirportMarker airport1;
	AirportMarker airport2;
	
	
	public void setup() {
		
		// setting up PAppler
		size(800,600, OPENGL);
		
		// setting up map and default events
		//map = new UnfoldingMap(this, 50, 50, 750, 550);
		map = new UnfoldingMap(this, 200, 50, 650, 600, new Google.GoogleMapProvider());
		
		MapUtils.createDefaultEventDispatcher(this, map);
		
		markerManager = map.getDefaultMarkerManager();
		
		
		
		List<PointFeature> airport = ParseFeed.parseAirports(this, "airports.dat");	
		airplaneList = new ArrayList<Marker>();
		
		
		double speed1 = 50.5;
		LocalDateTime l2 = LocalDateTime.of(2018, Month.NOVEMBER, 1,0,00,0,00000);	
		
		int numPlanes = 10;
		
		for(int i = 0; i<numPlanes; i++){
			
			airplaneList.add(new AirplaneMarker(speed1, l2, airport.get(new Random().nextInt(airport.size())), 
					airport.get(new Random().nextInt(airport.size()))));
		}
				
		//airplane1 = new AirplaneMarker(speed1, l2, airport.get(100), airport.get(120));
		
		
		Location Quito = new Location(-0.180653f, -78.467834f);
		Location Madrid = new Location(40.416775f, -3.703790f);
		
		airplaneManual = new AirplaneMarker(speed1, l2, Quito, Madrid, "Quito", "Madrid");		
		
		airplaneList.add(airplaneManual);	
		
		markerManager.addMarkers(airplaneList);
		
		
		
		
		
		
	}
	
	public void draw() {
		
		
		background(0);	
		
		for(Marker m : airplaneList) {
			
			((AirplaneMarker) m).setCurrentLoc();
			
		}
		
		
		
		
		
		//SimpleLinesMarker line1 = new SimpleLinesMarker(airplaneManual.getBeginning(), airplaneManual.getLocation());
		
		//TrajectoryMarker line1 = new TrajectoryMarker(airplaneManual);
		
		
		
		if (lastClicked != null) {
			
			if(lastClicked.getFront()) {
				
				line2 = new TrajectoryMarker(lastClicked.getBeginning(), lastClicked.getLocation());
				
			}else {
				
				line2 = new TrajectoryMarker(lastClicked.getEnding(), lastClicked.getLocation());				
				
			}
			
			
			
			markerManager.addMarker(line2);
			
			
		}
		
				
		map.draw();	
		
		
		if (lastClicked != null) {
			
			markerManager.removeMarker(line2);
			
		}
		
	}
	
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(airplaneList);
		
		//loop();
	}
	
	@Override
	public void mouseClicked()
	{
		if (lastClicked != null) {
			
			lastClicked = null;
			markerManager.removeMarker(line2);
			markerManager.removeMarker(airport1);
			markerManager.removeMarker(airport2);
			
		}
		else if (lastClicked == null) 
		{
			checkAirplanesForClick();
			
		}
	}
	
	private void checkAirplanesForClick()
	{
		if (lastClicked != null) return;
		// Loop over the earthquake markers to see if one of them is selected
		for (Marker marker : airplaneList) {
			if (marker.isInside(map, mouseX, mouseY)) {
				
				lastClicked = (AirplaneMarker)marker;
				
				airport1 = new AirportMarker(lastClicked.getBeginning());			
				airport2 = new AirportMarker(lastClicked.getEnding());
				
				markerManager.addMarker(airport1);
				markerManager.addMarker(airport2);
				
				
				//markerManager.addMarker(line2);
				
			}
		}		
	}
	
	
	
	private void selectMarkerIfHover(List<Marker> markers){
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) {
			
			AirplaneMarker marker = (AirplaneMarker)m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
	
	

}
