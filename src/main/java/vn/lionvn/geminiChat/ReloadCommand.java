package vn.lionvn.geminiChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final GeminiChat plugin;

    public ReloadCommand(GeminiChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && !sender.hasPermission("geminichat.reload")) {
            sender.sendMessage("§cBạn không có quyền sử dụng lệnh này.");
            return true;
        }

        plugin.reloadConfig();
        GeminiService.initialize(plugin.getConfig());

        if (plugin.getCommand("askai") != null) {
            plugin.getCommand("askai").setExecutor(new AskAICommand());
        } else {
            plugin.getLogger().warning("Command 'askai' is not registered in plugin.yml.");
        }

        sender.sendMessage("§a[GeminiChat] Đã reload toàn bộ plugin!");
        return true;
    }
}
