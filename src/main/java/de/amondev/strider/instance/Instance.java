package de.amondev.strider.instance;

import de.amondev.strider.thread.ThreadMap;
import de.amondev.strider.util.SessionId;
import org.bukkit.*;

import java.util.*;

public class Instance {

    private SessionId instanceId;

    private World instanceWorld;
    private List<ThreadMap> threadList;

    private Map<String, ThreadMap> threadKeyMap;

    private ThreadGenerator generator;

    public Instance() {
        this.instanceId = SessionId.generate();

        this.threadKeyMap = new HashMap<>();

        this.instanceWorld = generate("instance_" + instanceId.string());
        this.threadList = new ArrayList<>();

        this.generator = new ThreadGenerator(this);

        generator.generate("fallback", 0, 0, 0);
    }

    private World generate(String name) {
        WorldCreator creator = new WorldCreator(name);

        creator.generator(new InstanceChunkGenerator());

        return Bukkit.createWorld(creator);
    }

    public ThreadMap generateThread(String name) {
        return generator.generate(name);
    }

    public World instanceWorld() {
        return instanceWorld;
    }

    public ThreadMap thread(String threadId) {
        return threadKeyMap.get(threadId.toLowerCase());
    }

    public class ThreadGenerator {

        private Instance instance;
        private Direction direction;

        public ThreadGenerator(Instance instance) {
            this.instance = instance;
            this.direction = Direction.NORTH;
        }

        public ThreadMap generate(String name, int threadSortId, int x, int z) {
            ThreadMap threadMap = new ThreadMap(instance, SessionId.generate(), threadSortId, new Location(instanceWorld, x, 100, z));
            threadList.add(threadMap);
            threadMap.paste(Material.GOLD_BLOCK);

            threadKeyMap.put(name.toLowerCase().replace(" ", "_"), threadMap);
            return threadMap;
        }

        public ThreadMap generate(String name) {
            int sortId = threadList.size();
            Location location = center(sortId).orElseThrow();

            return generate(name, sortId, location.getBlockX(), location.getBlockZ());
        }

        public Optional<Location> center(int threadSortId) {
            Optional<ThreadMap> optionalThreadMap = before(threadSortId);

            if(optionalThreadMap.isEmpty()) {
                System.out.println("failed to get plot before");
                return Optional.empty();
            }

            ThreadMap thread = optionalThreadMap.get();

            int x = thread.center().getBlockX();
            int z = thread.center().getBlockZ();

            if(movable(x, z)) {
                direction = Direction.byId(direction.next);
            }


            if(direction == Direction.NORTH) {
                z = z - 30;
            }else if(direction == Direction.EAST) {
                x = x + 30;
            }else if(direction== Direction.SOUTH) {
                z = z + 30;
            }else if(direction == Direction.WEST) {
                x = x - 30;
            }

            return Optional.of(new Location(instanceWorld, x, 100, z));
        }

        private boolean movable(int x, int z) {
            Direction next = Direction.byId(direction.next);

            if(next == Direction.NORTH) {
                z = z - 30;
            }else if(next == Direction.EAST) {
                x = x + 30;
            }else if(next == Direction.SOUTH) {
                z = z + 30;
            }else if(next == Direction.WEST) {
                x = x - 30;
            }

            for(ThreadMap thread : threadList) {
                if(thread.center().getBlockX() == x && thread.center().getBlockZ() == z) {
                    return false;
                }
            }

            return true;
        }

        private Optional<ThreadMap> before(int threadSortId) {
            for(ThreadMap thread : threadList) {
                if(thread.threadSortId() == threadSortId - 1) {
                    return Optional.of(thread);
                }
            }

            return Optional.empty();
        }

        private enum Direction {

            NORTH(1, 2),
            EAST(2, 3),
            SOUTH(3, 4),
            WEST(4, 1);

            private int current;
            private int next;

            public static Direction byId(int i) {
                for(Direction cache : Direction.values()) {
                    if(cache.current == i) {
                        return cache;
                    }
                }

                return Direction.NORTH;
            }

            Direction(int current, int next) {
                this.current = current;
                this.next = next;
            }

            public int getCurrent() {
                return current;
            }

            public int getNext() {
                return next;
            }
        }

    }
}
