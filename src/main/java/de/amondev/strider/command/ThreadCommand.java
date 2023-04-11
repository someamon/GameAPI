package de.amondev.strider.command;

import de.amondev.strider.Strider;
import de.amondev.strider.util.SessionId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ThreadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(Strider.instance == null)return true;

        Strider.instance.generateThread("thread" + SessionId.generate(20));
        return false;
    }
}
