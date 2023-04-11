package de.amondev.strider;

import de.amondev.strider.command.InitCommand;
import de.amondev.strider.command.ThreadCommand;
import de.amondev.strider.instance.Instance;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Strider extends JavaPlugin implements Listener {

    public static Instance instance;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("init").setExecutor(new InitCommand());
        getCommand("thread").setExecutor(new ThreadCommand());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(instance == null)return;

        player.teleport(new Location(instance.instanceWorld(), 0, 101,0));
        player.sendMessage(instance.instanceWorld().getName());
    }
}
