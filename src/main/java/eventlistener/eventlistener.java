package eventlistener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class eventlistener implements Listener {
    private final JavaPlugin plugin;

    public eventlistener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (Bukkit.getPlayer(event.getDamager().getName()) != null) {
            if (Bukkit.getPlayer(event.getEntity().getName()) != null) {
                for (int i = 0; i < Objects.requireNonNull(plugin.getConfig().getList("Organization")).size(); i++) {
                    ItemStack itemStack = new ItemStack(Material.valueOf(String.valueOf(Objects.requireNonNull(plugin.getConfig().getList("Organization")).get(i))));
                    if (Objects.requireNonNull(Bukkit.getPlayer(event.getDamager().getName())).getInventory().containsAtLeast(itemStack, 1)) {
                        if(Objects.requireNonNull(Bukkit.getPlayer(event.getEntity().getName())).getInventory().containsAtLeast(itemStack,1)){
                            event.setDamage(0);
                            break;
                        }
                    }
                }
                ItemStack itemStack = new ItemStack(Material.valueOf("WHITE_BANNER"));
                if(Objects.requireNonNull(Bukkit.getPlayer(event.getDamager().getName())).getInventory().containsAtLeast(itemStack,1)||Objects.requireNonNull(Bukkit.getPlayer(event.getEntity().getName())).getInventory().containsAtLeast(itemStack,1)){
                    if(plugin.getConfig().getBoolean("NeutralPlayer")) {
                        event.setDamage(0);
                    }
                }
            }
        }
    }
}