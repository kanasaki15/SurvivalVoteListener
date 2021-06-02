package xyz.n7mn.dev.survivalvotelistener;

import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalVoteListener extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents( new SurvivalVote(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
