package vn.lionvn.geminiChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AskAICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Hãy nhập câu hỏi: /askai <câu hỏi>");
            return true;
        }

        String question = String.join(" ", args);
        player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.LIGHT_PURPLE + "GeminiChat" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "Đang gửi câu hỏi...");


        // Chạy bất đồng bộ để không lag server
        Bukkit.getScheduler().runTaskAsynchronously(GeminiChat.getInstance(), () -> {
            String answer = GeminiService.askGemini(question);
            Bukkit.getScheduler().runTask(GeminiChat.getInstance(), () -> {
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "Gemini" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + answer);
            });
        });

        return true;
    }
}
