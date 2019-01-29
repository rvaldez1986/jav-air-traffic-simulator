package OwnProject;


import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AirplaneMarker extends SimplePointMarker {
	
	private double speed;
	private Location beginning;
	private Location ending;
	private LocalDateTime start;	
	private boolean front;
	private boolean dir;
	private String begIden;
	private String endIden;
	
	
	
	public AirplaneMarker(double speed, LocalDateTime start,  PointFeature begAirport, PointFeature endAirport) {		
		
		this.speed = speed;  //seconds
		this.start = start;		
		this.beginning = begAirport.getLocation();
		this.ending = endAirport.getLocation();
		this.begIden = begAirport.getStringProperty("city") + " , " + begAirport.getStringProperty("country");
		this.endIden = endAirport.getStringProperty("city") + " , " + endAirport.getStringProperty("country");
		
		this.setLocation(this.beginning);
		
	}
	
	
	public AirplaneMarker(double speed, LocalDateTime start,  Location begAirport, 
			Location endAirport, String begName, String endName) {		
		
		this.speed = speed;  //seconds
		this.start = start;		
		this.beginning = begAirport;
		this.ending = endAirport;
		this.begIden = begName;
		this.endIden = endName;
		
		this.setLocation(this.beginning);
		
	}
	
	public Location getBeginning() {
		
		return(this.beginning);		
		
	}
	
	public Location getEnding() {
		
		return(this.ending);		
		
	}
	
	
	
	public void setCurrentLoc() {		
		
		
		LocalDateTime l = LocalDateTime.now();		
		long secsBetween = ChronoUnit.SECONDS.between(this.start, l);	    
	    double totalDist = this.beginning.getDistance(this.ending);
	    double d =  (secsBetween*this.speed)/totalDist;	
	    int dec = (int) d; 		    
	    double frac = d - dec;		
					
		float b1 = this.beginning.getLat();
		float b2 = this.beginning.getLon();
			
		float a1 = this.ending.getLat() - b1;
		float a2 = this.ending.getLon() - b2;		
		
		this.front = (dec % 2) == 0;
		this.dir = this.ending.getLon() > this.beginning.getLon();
		
		float mylat;
		float mylon;
		
		if(this.front) {
			
			mylat = (float)frac*a1 + b1;
			mylon = (float)frac*a2 + b2;
			
		}else {
			
			mylat = (float)(1-frac)*a1 + b1;
			mylon = (float)(1-frac)*a2 + b2;			
			
		}
		
		Location myloc = new Location(mylat, mylon);
		
		this.setLocation(myloc);	
		
	}
	
	public boolean getFront() {
		
		return(this.front);		
		
	}
	
	
	public void draw(PGraphics pg, float x, float y) {		
		
		
		if(this.dir) {
			if(this.front) {
				
				hmDraw(false, pg, x, y);
		
				
			
			}else {
			
				hmDraw(true, pg, x, y);
			
		
			}
		}else {
			if(this.front) {	
				
				hmDraw(true, pg, x, y);			
				
			
			}else {
			
				hmDraw(false, pg, x, y);			
		
			}		
		
		}
		
		
		if (selected) {
			showTitle(pg, x, y);
		}	
		
		
	}
	
	
	public void showTitle(PGraphics pg, float x, float y){
		
		
		String myTitle = begIden + " , " + endIden;
		
		pg.textSize(10);
		pg.fill(0,0,0);		
		pg.text(myTitle, x, y);
		
	}
	
	
	
	
	
	
	public void hmDraw(boolean sign, PGraphics pg, float x, float y) {
		
		if(sign) {
			
			// Save previous drawing style
			pg.pushStyle();
	
			// IMPLEMENT: drawing triangle for each city
			pg.fill(255, 255, 0);
			pg.ellipse(x, y, 22, 5);	
			pg.triangle(x+5, y-8, x+5, y+8, x-5, y);
			
			
			// Restore previous drawing style
			pg.popStyle();	
			
			
		}else {
			
			// Save previous drawing style
			pg.pushStyle();
				
			// IMPLEMENT: drawing triangle for each city
			pg.fill(255, 255, 0);
			pg.ellipse(x, y, 22, 5);							
			pg.triangle(x-5, y-8, x-5, y+8, x+5, y);
			
					
			// Restore previous drawing style
			pg.popStyle();			
			
		}
		
		
	}

}
