package me.ohvalsgod.fivedollacheats.module.category;

public enum ModuleCategory {

    DEFAULT("Default", true),
    COMBAT("Combat", false),
    MOVEMENT("Movement", false),
    RENDER("Render", false),
    WORLD("World", false),
    FIVEDOLLAEXPLOITS("Five Dolla Exploits", false);

    String displayName;
    boolean hidden;

    ModuleCategory(String displayName, boolean hidden) {
        this.displayName = displayName;
        this.hidden = hidden;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isHidden() {
        return hidden;
    }
}
