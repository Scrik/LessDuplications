package LessDuplications;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LDEventHandler implements Listener {
    private LDMain mainInstance;

    public LDEventHandler(LDMain instance) {
        mainInstance = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockInteractEvent(PlayerInteractEvent e) {
        if (mainInstance.config.blockInteractInVehicle == false) return;

        if ( e.getPlayer().isInsideVehicle() && e.hasBlock() ) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void FrameInteractEvent(PlayerInteractEntityEvent e) {
        if (mainInstance.config.frameInteractInVehicle == false) return;

        if ( e.getRightClicked().getType() == EntityType.ITEM_FRAME ) {
            e.getPlayer().closeInventory();

            if ( e.getPlayer().isInsideVehicle() ) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityBreakEvent(HangingBreakByEntityEvent e) {
        if (mainInstance.config.frameDestroyInVehicle == false) return;

        if ( e.getRemover().isInsideVehicle() ) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerTeleportEvent(PlayerTeleportEvent e) {
        if (mainInstance.config.closeInventoryOnTeleport == false) return;

        e.getPlayer().closeInventory();
    }
}