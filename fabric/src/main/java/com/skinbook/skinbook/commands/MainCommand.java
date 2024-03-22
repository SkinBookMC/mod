package com.skinbook.skinbook.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class MainCommand {
    public static void registerCommand() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("sb")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal("Invalid Use! Use /sb help for more information."));
                    return 1;
                })
                .then(ClientCommandManager.literal("auth")
                        .executes(Authorization::execute)
                )
        ));
    }
}
