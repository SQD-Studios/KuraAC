package net.chamosmp.kuraac.Commands;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class KuraMainCommands {

    public static void register(Commands commands) {
        commands.register(Commands.literal("kuraac")
                        .then(Commands.literal("help"))
                        .requires(sender -> sender.getSender().hasPermission("kuraac.*"))
                        .executes(ctx ->{
                            ctx.getSource().getSender().sendPlainMessage("Command List:");
                            return Command.SINGLE_SUCCESS;
                        })

                .requires(sender -> sender.getSender().hasPermission("kuraac.*"))
                .executes(ctx -> {
                    ctx.getSource().getSender().sendRichMessage("<dark_red>Missing arguments.</dark_red>Do /<light_purple>kuraac</light_purple> help to get a list of commands.");
                    return Command.SINGLE_SUCCESS;

                })
                .build());
    }

}

