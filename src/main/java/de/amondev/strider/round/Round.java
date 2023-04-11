package de.amondev.strider.round;

import de.amondev.strider.controller.InstanceController;
import de.amondev.strider.instance.Instance;
import de.amondev.strider.thread.ThreadMap;
import org.bukkit.entity.Player;

import java.util.List;

public class Round {

    private ThreadMap threadMap;

    private List<Player> players;

    public Round() {

    }

    public void relocate(String instanceName, String key) {
        Instance instance = InstanceController.instance.instance(instanceName).orElseThrow();
        ThreadMap relocate = instance.thread(key);

        players.forEach(player -> player.teleport(relocate.center()));
    }
}
