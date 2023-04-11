package de.amondev.strider.thread;

import de.amondev.strider.instance.Instance;
import de.amondev.strider.util.SessionId;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class ThreadMap {

    private Instance instance;
    private SessionId threadId;
    private int threadSortId;

    private Location center;

    public ThreadMap(Instance instance, SessionId threadId, int threadSortId, Location center) {
        this.instance = instance;
        this.threadId = threadId;
        this.threadSortId = threadSortId;
        this.center = center;
    }

    public void paste(Material material) {
                int x = center.getBlockX();
                int z = center.getBlockZ();

                for(int i = x - 5; i < x + 5; i++) {
                    for(int i2 = z - 5; i2 < z + 5; i2++) {
                        instance.instanceWorld().getBlockAt(new Location(instance.instanceWorld(), i, 100, i2)).setType(material);
                        System.out.println(i + ":" + i2);
                    }
                }

        //paste schematic
    }

    public Instance instance() {
        return instance;
    }
    public SessionId threadId() {
        return threadId;
    }
    public int threadSortId() {
        return threadSortId;
    }

    public Location center() {
        return center;
    }
}
