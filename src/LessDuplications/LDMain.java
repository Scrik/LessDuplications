package LessDuplications;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class LDMain extends JavaPlugin {

    protected LDConfig config;
    public static Logger logger = Logger.getLogger("LessDuplications");
    
    private static String prefix = ChatColor.GREEN + "[GameTK]";

    @Override
    public void onEnable() {
        config = new LDConfig("plugins/LessDuplications/config.yml");
        
        if (config.enabled == false) {
            logger.info("[LessDuplications] Плагин не загружен - isPluginEnabled = false.");
            return;
        }

        Bukkit.getServer().getPluginManager().registerEvents(new LDEventHandler(this), this);
        logger.info("[LessDuplications] Плагин успешно загружен!");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(new LDEventHandler(this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0) {
            LDShowHelp((Player) sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            config.reloadConfig();
            
            LDSendMessage(ChatColor.GREEN + "Конфигурация была перезагружена.", (Player) sender);
        } else {
            LDShowHelp((Player) sender);
        }

        return true;
    }
    
    public static void LDBroadcastMessage(String message) {
        Bukkit.broadcastMessage(prefix + " " + message);
    }

    public static void LDSendMessage(String message, Player p) {
        p.sendMessage(prefix + " " + message);
    }
    
    public static void LDShowHelp(Player p) {
        LDSendMessage(ChatColor.GREEN + "LessDuplications - команды:", p);
        LDSendMessage(ChatColor.BLUE + "/ld reload - перезагрузить конфиг", p);
    }
}