package com.skinbook.skinbook.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.skinbook.skinbook.util.Token;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Authorization {
    public static int execute(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        String copyText = Token.getToken(context.getSource().getPlayer().getUuid().toString());

        Text line = Text.translatable("-----------------------------------------").setStyle(Style.EMPTY.withFormatting(Formatting.DARK_GREEN, Formatting.STRIKETHROUGH));
        Text instruction = Text.translatable("Paste your verification code in your browser").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://example.com")));
        Text code = Text.translatable("Copy verification code").setStyle(Style.EMPTY.withFormatting(Formatting.GREEN, Formatting.BOLD, Formatting.UNDERLINE).withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, copyText)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("Click to copy to clipboard"))));

        context.getSource().sendFeedback(line);
        context.getSource().sendFeedback(instruction);
        context.getSource().sendFeedback(code);
        context.getSource().sendFeedback(line);

        return 1;
    }
}
