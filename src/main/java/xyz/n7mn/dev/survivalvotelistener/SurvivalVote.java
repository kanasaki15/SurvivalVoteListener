package xyz.n7mn.dev.survivalvotelistener;

import com.google.gson.Gson;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import okhttp3.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class SurvivalVote implements Listener {

    private Plugin plugin;

    public SurvivalVote(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void e (VotifierEvent e){
        Vote vote = e.getVote();

        String username = vote.getUsername();
        for (Player player : plugin.getServer().getOnlinePlayers()){
            player.sendMessage(ChatColor.YELLOW + "[ななみ生活鯖] " + ChatColor.RESET + username +"さんが投票しました。");
        }

        try{

            DiscordWebhookData data = new DiscordWebhookData(username + "さんが投票しました。", "投票通知", "", false);

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), new Gson().toJson(data));
            Request request = new Request.Builder()
                    .url("")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            response.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
