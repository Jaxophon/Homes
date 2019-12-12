package de.janscheel.paper.obj;

import org.bukkit.Location;

public class Home {

	private String name;
	private Location loc;

	public Home(String name, Location loc) {
		this.name = name;
		this.loc = loc;
	}

	public Home() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

}
