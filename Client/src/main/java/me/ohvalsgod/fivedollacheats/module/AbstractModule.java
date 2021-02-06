package me.ohvalsgod.fivedollacheats.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.ohvalsgod.fivedollacheats.FiveDolla;
import me.ohvalsgod.fivedollacheats.module.category.ModuleCategory;
import net.minecraft.client.Minecraft;

@Getter
@AllArgsConstructor
public abstract class AbstractModule {

    protected final Minecraft mc = Minecraft.getMinecraft();

    private String name, displayName, description, version, mode;
    private int key;
    private boolean running = false;
    private ModuleCategory category;

    public void onEnable() {
        FiveDolla.getInstance().getEventManager().register(this);
        FiveDolla.getInstance().getModuleManager().getActiveModules().add(this);
    }

    public void onDisable() {
        FiveDolla.getInstance().getEventManager().unregister(this);
        FiveDolla.getInstance().getModuleManager().getActiveModules().remove(this);
    }

    public void toggle() {
        running = !running;
        if (running) {
            onEnable();
        } else {
            onDisable();
        }
    }

}
