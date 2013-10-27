package LessDuplications;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;

public class Main extends JavaPlugin {
    
    boolean enablePlugin;
    boolean interactBlock;
    boolean interactFrame;
    boolean interactFrameinvehicle;
    
    Logger log = Logger.getLogger("Minecraft");
    
    @Override
    public void onEnable() {
        /*try {
            loadCFG();
        } catch (IOException e) {
            log.info("[LessDuplications] Невозможно прочитать файл конфигурации.");
        }*/
        
        Bukkit.getServer().getPluginManager().registerEvents(new PluginEventHandler(), this);
        log.info("[LessDuplications] Плагин успешно загружен!");
        
        /*if ( enablePlugin == true ) {
            Bukkit.getServer().getPluginManager().registerEvents(new PluginEventHandler(), this);
            
            log.info("[LessDuplications] Плагин успешно загружен!");
        } else {
            log.info("[LessDuplications] Плагин загружен, но не выполняет требуемых функций.");
            log.info("[LessDuplications] Для включения поменяйте значение enablePlugin на true.");
        }*/
    }
    
    @Override
    public void onDisable() {
        HandlerList.unregisterAll(new PluginEventHandler());
    }
    
    public void loadCFG() throws IOException {
        File CFGFile = new File(this.getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(CFGFile);
        
        config.addDefault("enablePlugin", true);
        config.addDefault("interact.block", true);
        config.addDefault("interact.frame", true);
        config.addDefault("interact.frameinvehicle", true);
        
        config.options().copyDefaults(true);
        config.save(CFGFile);
        
        enablePlugin = config.getBoolean("enablePlugin");
        interactBlock = config.getBoolean("interact.block");
        interactFrame = config.getBoolean("interact.frame");
        interactFrameinvehicle = config.getBoolean("interact.frameinvehicle");
    }
}