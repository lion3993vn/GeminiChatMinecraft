package vn.lionvn.geminiChat;

import org.bukkit.plugin.java.JavaPlugin;

public final class GeminiChat extends JavaPlugin {

    private static GeminiChat instance;

    public static GeminiChat getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Load config
        saveDefaultConfig();
        GeminiService.initialize(getConfig());

        if (this.getCommand("askai") != null) {
            this.getCommand("askai").setExecutor(new AskAICommand());
        } else {
            getLogger().warning("Command 'askai' is not registered in plugin.yml.");
        }

        if (this.getCommand("aireload") != null) {
            this.getCommand("aireload").setExecutor(new ReloadCommand(this));
        } else {
            getLogger().warning("Command 'aireload' is not registered in plugin.yml.");
        }

        getLogger().info("GeminiChat đã bật!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GeminiChat đã tắt!");
    }
}
