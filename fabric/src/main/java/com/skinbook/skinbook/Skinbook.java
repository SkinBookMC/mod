package com.skinbook.skinbook;

import com.skinbook.skinbook.commands.MainCommand;
import net.fabricmc.api.ModInitializer;

public class Skinbook implements ModInitializer {
    @Override
    public void onInitialize() {
        MainCommand.registerCommand();
    }
}
