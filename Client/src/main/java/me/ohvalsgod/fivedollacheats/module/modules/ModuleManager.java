package me.ohvalsgod.fivedollacheats.module.modules;

import lombok.Getter;
import me.ohvalsgod.fivedollacheats.module.AbstractModule;
import me.ohvalsgod.fivedollacheats.module.category.ModuleCategory;
import me.ohvalsgod.fivedollacheats.module.modules.combat.Killaura;
import me.ohvalsgod.fivedollacheats.module.modules.fivedollaexploits.Phase;
import me.ohvalsgod.fivedollacheats.module.modules.movement.Flight;
import me.ohvalsgod.fivedollacheats.module.modules.movement.Speed;
import me.ohvalsgod.fivedollacheats.module.modules.movement.Sprint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ModuleManager {

    private Set<AbstractModule> abstractModules;
    private List<AbstractModule> activeModules;

    public ModuleManager() {
        abstractModules = new HashSet<>();
        activeModules = new ArrayList<>();
    }

    public void init() {
        //  COMBAT
        abstractModules.add(new Killaura());

        //  FIVE DOLLA EXPLOITS
        abstractModules.add(new Phase());

        //  MOVEMENT
        abstractModules.add(new Sprint());
        abstractModules.add(new Flight());
        abstractModules.add(new Speed());

        //  RENDER

        //  WORLD
    }

    public void close() {
        for (AbstractModule abstractModule : abstractModules) {
            abstractModule.onDisable();
        }
    }

    public AbstractModule getByName(String name) {
        for (AbstractModule abstractModule : abstractModules) {
            if (abstractModule.getName().equalsIgnoreCase(name)) {
                return abstractModule;
            }
        }
        return null;
    }

    public AbstractModule getModuleByKey(int key) {
        for (AbstractModule abstractModule : abstractModules) {
            if (abstractModule.getKey() == key) {
                return abstractModule;
            }
        }
        return null;
    }

    public Set<AbstractModule> getByCategory(ModuleCategory category) {
        Set<AbstractModule> toReturn = new HashSet<>();
        for (AbstractModule abstractModule : abstractModules) {
            if (abstractModule.getCategory().equals(category)) {
                abstractModules.add(abstractModule);
            }
        }
        return toReturn;
    }

}
