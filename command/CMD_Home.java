package de.janscheel.paper.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.janscheel.paper.obj.Home;
import de.janscheel.paper.obj.HomeCreator;
import de.janscheel.paper.obj.HomeFetcher;

public class CMD_Home implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§l§eUm diesen Befehl ausführen zu können musst du ein Spieler sein§l§8!");
			return true;
		}

		Player p = (Player) sender;
		HomeFetcher hf = new HomeFetcher(p.getUniqueId());

		// DONE
		// /home [help]
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
			if (p.hasPermission("homes.help")) {
				p.sendMessage("§l§8/§2home §8[§fhelp§8]");
				p.sendMessage("§l§8/§2home §8<§fname§8>");
				p.sendMessage("§l§8/§2home §fset §8<§fname§8>");
				p.sendMessage("§l§8/§2home §fdelete §8<§fname§8>");
				p.sendMessage("§l§8/§2home §flist");
				return true;
			} else {
				// /home help no perms
				p.sendMessage("§4§lDu hast keine Permission dafür");
				return true;

			}

			// DONE
			// /home set <name>
		} else if (args[0].equalsIgnoreCase("set")) {
			if (p.hasPermission("home.set")) {
				String name = args[1];
				Home home = new Home(name, p.getLocation());

				if (hf.getHomelist().size() >= 3 && !hf.getHomelist().contains(name)) {
					p.sendMessage("Du hast zu viele Homes");
					return true;
				}

				HomeCreator.saveHome(p, home);
				p.sendMessage("§l§2Homepunkt wurde gesetzt§8!");
				return true;

				// /home set no perms
			} else {
				p.sendMessage("§4§lDu hast keine Permission dafür");
				return true;

			}

			// /home delete <name>
		} else if (args[0].equalsIgnoreCase("delete")) {
			if (p.hasPermission("homes.delete")) {
				HomeCreator.removeFromList(p, args[1]);
				// /home delete no perms
			} else {
				p.sendMessage("§4§lDu hast keine Permission dafür");
				return true;

			}

			// DONE
			// /home list
		} else if (args[0].equalsIgnoreCase("list")) {
			if (p.hasPermission("homes.list")) {
				p.sendMessage(hf.getHomelist().toString());
				// /home list no perms
			} else {
				p.sendMessage("§4§lDu hast keine Permission dafür");
				return true;

			}
		}

		return true;
	}

}
