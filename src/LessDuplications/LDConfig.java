package LessDuplications;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public class LDConfig {
    private String fileName;
    private YamlConfiguration config;

    boolean enabled;
    boolean blockInteractInVehicle;
    boolean frameInteractInVehicle;
    boolean frameDestroyInVehicle;
    boolean closeInventoryOnTeleport;

    public LDConfig(String fileName) {
        this.fileName = fileName;
        config = YamlConfiguration.loadConfiguration(new File(this.fileName));

        readConfig();
        saveConfig();
    }

    public void reloadConfig() {
        if (fileName != null) {
            config = YamlConfiguration.loadConfiguration(new File(this.fileName));

            readConfig();
            saveConfig();
        }
    }

    public void readConfig() {
        enabled = config.getBoolean("settings.isPluginEnabled", true);
        blockInteractInVehicle = config.getBoolean("fix.disableBlockInteractInVehicle", true);
        frameInteractInVehicle = config.getBoolean("fix.disableFrameInteractInVehicle", true);
        frameDestroyInVehicle = config.getBoolean("fix.disableFrameDestroyInVehicle", true);
        closeInventoryOnTeleport = config.getBoolean("fix.closeInventoryOnTeleport", true);
    }

    public void saveConfig() {
        config.set("settings.isPluginEnabled", enabled);
        config.set("fix.disableBlockInteractInVehicle", blockInteractInVehicle);
        config.set("fix.disableFrameInteractInVehicle", frameInteractInVehicle);
        config.set("fix.disableFrameDestroyInVehicle", frameDestroyInVehicle);
        config.set("fix.closeInventoryOnTeleport", closeInventoryOnTeleport);

        try {
            config.save(fileName);
        } catch (IOException e) {
            LDMain.logger.info("Не удалось сохранить конфигурационный файл!");
            e.printStackTrace();
        }
    }
}