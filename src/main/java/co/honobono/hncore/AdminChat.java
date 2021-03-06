package co.honobono.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.honobono.hncore.util.sendPacket;

public class AdminChat implements Listener, CommandExecutor {

	private static Map<Player, Player> admins= new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 0) {
			Player player = (Player) sender;
			if (admins.containsKey(player)) {
				admins.remove(player);
				sendPacket.sendActionBar(player, "§aYour Leave AdminsChat");
				return true;
			} else {
				admins.put(player, player);
				sendPacket.sendActionBar(player, "§aYour Join AdminsChat");
				return true;
			}
		} else {
			String s = "";
			for (String a : args) {
				s = s + " " + a;
			}
			Bukkit.broadcast("<" + ((Player) sender).getDisplayName() + "> " + "§b" + s, "hncore.adminchat");
			return true;
		}
	}

	@EventHandler
	public void adminchat(AsyncPlayerChatEvent event) {
		if (admins.containsKey(event.getPlayer())) {
			event.setCancelled(true);
			Bukkit.broadcast("<" + event.getPlayer().getDisplayName() + "> " + "§b" + event.getMessage(), "hncore.adminchat");
		}
	}
}