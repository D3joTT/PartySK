package net.d3jott.partysk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PartySK extends JavaPlugin {

    PartySK instance;
    SkriptAddon addon;

    @Override
    public void onEnable() {
        
        instance = this;
        addon = Skript.registerAddon(this);

        try {
            addon.loadClasses("net.d3jott.partysk", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info("PartySK has been loaded!");

    }

    public PartySK getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}
