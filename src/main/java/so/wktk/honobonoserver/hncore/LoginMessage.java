package so.wktk.honobonoserver.hncore;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.util.Other;

public class LoginMessage implements Listener{
	private Plugin instance = HNCore.getInstance();

	@EventHandler
	public void LoginMsg(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage("");

		String JoinMessage = instance.getConfig().getString("LoginMessage.LoginMessage");
			Bukkit.broadcastMessage(Other.TrimS(JoinMessage, player));
		if(!(player.hasPlayedBefore())) {
			List<String> First = instance.getConfig().getStringList("LoginMessage.FirstLogin");
			for(String first : First) {
				first = Other.TrimS(first, player);
				Bukkit.broadcastMessage(first);
			}
		}

		List<String> LoginMessage = instance.getConfig().getStringList("LoginMessage.Login");
		for ( String m : LoginMessage) {
			player.sendMessage(Other.TrimS(m, player));
		}
		//hideコマンド使用者を適応
		Map<Player, Player> hiders = show_hide.gethider();
		for (Entry<Player, Player> entry : hiders.entrySet()) {
            Player hider = entry.getValue();
            player.hidePlayer(hider);
        }
	}

	@EventHandler
	public void LogoutMsg(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String LogoutMessage = instance.getConfig().getString("LoginMessage.LogoutMessage");
		event.setQuitMessage(Other.TrimS(LogoutMessage, player));
	}
}
