package de.amondev.strider.command;

import de.amondev.strider.Strider;
import de.amondev.strider.instance.Instance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class InitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Strider.instance = new Instance();
        return false;
    }
}
