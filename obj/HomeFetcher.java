package de.janscheel.paper.obj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.janscheel.paper.Homes;

public class HomeFetcher {

	private Home home;
	private UUID uuid;

	public HomeFetcher(UUID uuid) {
		this.uuid = uuid;
	}

	public String getHomeName() {
		String homename = "EMPTY";
		ResultSet rs_homeName = Homes.main.mysql.GetResult("SELECT homename FROM homes WHERE uuid='" + uuid + "'");

		try {
			while (rs_homeName.next()) {
				try {
					homename = rs_homeName.getString("homename");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return homename;
	}

	public String getWorld() {
		String world = "EMPTY";
		ResultSet rs_world = Homes.main.mysql.GetResult(
				"SELECT world FROM homes WHERE uuid='" + uuid + "' AND WHERE homename='" + getHomeName() + "'");

		try {
			while (rs_world.next()) {
				try {
					world = rs_world.getString("world");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return world;
	}

	public Double getX() {
		Double x = null;
		ResultSet rs_x = Homes.main.mysql
				.GetResult("SELECT x FROM homes WHERE uuid='" + uuid + "' AND WHERE homename='" + getHomeName() + "'");

		try {
			while (rs_x.next()) {
				try {
					x = rs_x.getDouble("x");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return x;
	}

	public Double getY() {
		Double y = null;
		ResultSet rs_y = Homes.main.mysql
				.GetResult("SELECT y FROM homes WHERE uuid='" + uuid + "' AND WHERE homename='" + getHomeName() + "'");

		try {
			while (rs_y.next()) {
				try {
					y = rs_y.getDouble("y");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return y;
	}

	public Double getZ() {
		Double z = null;
		ResultSet rs_z = Homes.main.mysql
				.GetResult("SELECT z FROM homes WHERE uuid='" + uuid + "' AND WHERE homename='" + getHomeName() + "'");

		try {
			while (rs_z.next()) {
				try {
					z = rs_z.getDouble("z");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return z;
	}

	public Home getHome() {
		home = new Home(getHomeName(), new Location(Bukkit.getWorld(getWorld()), getX(), getY(), getZ()));
		return home;
	}

	public ArrayList<String> getHomelist() {
		String homelist = null;
		ArrayList<String> emptylistfornull = new ArrayList<String>();
		ArrayList<String> homeArrayList = new ArrayList<String>();
		ResultSet rs_homelist = Homes.main.mysql.GetResult("SELECT homelist FROM homelists WHERE uuid='" + uuid + "'");
		try {
			if (rs_homelist.next()) {
				homelist = rs_homelist.getString("homelist");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (homelist == null) {
			return emptylistfornull;
		}
		homeArrayList.addAll(Arrays.asList(homelist.split("\\s*,\\s*")));
		return homeArrayList;
	}

}
