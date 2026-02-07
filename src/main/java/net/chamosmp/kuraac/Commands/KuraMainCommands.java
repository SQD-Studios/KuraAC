package net.chamosmp.kuraac.Commands;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.Commands;

public class KuraMainCommands {

    Commands.literal("kuraac")
            .requires(sender -> sender.getSender().hasPermission("kuraac.*"))
            .executes(ctx -> {
        ctx.getSource().getSender().sendRichMessage("<gold>You have permission to run this command!");
        return Command.SINGLE_SUCCESS;
        player.sendPlainMessage("KuraAC Built To Perform. Do /kuraac help for more info!")
    });



}



