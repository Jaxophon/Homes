package de.janscheel.paper.obj;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import de.janscheel.paper.Homes;

public class HomeCreator {

	public static void saveHome(Player p, Home home) {
		Homes.main.mysql.ExecuteCommand("INSERT INTO homes(playername, uuid, homename, world, x, y, z) VALUES('"
				+ p.getName() + "', '" + p.getUniqueId().toString() + "', '" + home.getName() + "','"
				+ home.getLoc().getWorld().getName() + "', '" + home.getLoc().getX() + "', '" + home.getLoc().getY()
				+ "', ' " + home.getLoc().getZ() + "') ON DUPLICATE KEY UPDATE " + "world ='"
				+ home.getLoc().getWorld().getName() + "'," + "x ='" + home.getLoc().getX() + "'," + "y ='"
				+ home.getLoc().getY() + "'," + "z ='" + home.getLoc().getZ() + "'");

		Homes.main.mysql.ExecuteCommand("INSERT INTO homelists(playername, uuid, homelist) VALUES('" + p.getName()
				+ "', '" + p.getUniqueId().toString() + "', '" + addHomeToList(p, home.getName())
				+ "') ON DUPLICATE KEY UPDATE homelist = '" + addHomeToList(p, home.getName()) + "'");
	}

	public static String addHomeToList(Player p, String name) {
		HomeFetcher hf = new HomeFetcher(p.getUniqueId());
		ArrayList<String> homes = hf.getHomelist();
		if (!homes.contains(name)) {
			homes.add(name);
		}

		return arrayListToString(homes);
	}

	public static void removeFromList(Player p, String name) {
		HomeFetcher hf = new HomeFetcher(p.getUniqueId());
		ArrayList<String> homes = hf.getHomelist();

		if (homes.contains(name)) {
			homes.remove(name);

		}

		String homeList = arrayListToString(homes);

		// Home aus der Homelist entfernen
		Homes.main.mysql.ExecuteCommand("UPDATE homelists SET homelist='" + homeList + "'");

		// Home aus Homes entfernen
		Homes.main.mysql.ExecuteCommand("DELETE FROM homes WHERE homename='" + name + "'");
	}

	public static String arrayListToString(ArrayList<String> list) {
		StringBuilder sbString = new StringBuilder("");

		for (String home : list) {
			sbString.append(home).append(",");
		}
		String strList = sbString.toString();

		if (strList.length() > 0) {
			strList = strList.substring(0, strList.length() - 1);
		}

		return strList;
	}
}