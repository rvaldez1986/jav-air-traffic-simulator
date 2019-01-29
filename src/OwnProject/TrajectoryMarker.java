package OwnProject;


import java.util.List;
import java.util.ArrayList;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.utils.MapPosition;



public class TrajectoryMarker extends SimpleLinesMarker {
	
		
	public TrajectoryMarker(Location start, Location end) {
		super(start, end);
	}
	
	

	
	public void draw(PGraphics pg, List<MapPosition> mapPositions) {
		
		MapPosition mp1 = mapPositions.get(0);
		MapPosition mp2 = mapPositions.get(1);
		
		pg.pushStyle();
		
		// IMPLEMENT: drawing triangle for each city
		pg.stroke(0, 0, 255);
		pg.strokeWeight(0.9f); 
		
		pg.line(mp1.x, mp1.y, mp2.x, mp2.y);
		
		
		
		// Restore previous drawing style
		pg.popStyle();	

		
	}
	
	
	
	

}
