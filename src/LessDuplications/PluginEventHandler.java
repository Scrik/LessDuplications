package LessDuplications;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PluginEventHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockInteractEvent(PlayerInteractEvent e) {
        if ( e.getPlayer().isInsideVehicle() && e.hasBlock() ) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityInteractEvent(PlayerInteractEntityEvent e) {
        if ( e.getRightClicked().getType() == EntityType.ITEM_FRAME ) {
            e.getPlayer().closeInventory();
            
            if ( e.getPlayer().isInsideVehicle() ) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityBreakEvent(HangingBreakByEntityEvent e) {
        if ( e.getRemover().isInsideVehicle() ) {
            e.setCancelled(true);
        }
    }
}
