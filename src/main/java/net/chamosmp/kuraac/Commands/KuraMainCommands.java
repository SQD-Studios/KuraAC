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
                            ctx.getSource().getSender().sendPlainMessage("Here is the list of commands:");
                            return Command.SINGLE_SUCCESS;
                        })

                .requires(sender -> sender.getSender().hasPermission("kuraac.*"))
                .executes(ctx -> {
                    ctx.getSource().getSender().sendPlainMessage("KuraAC Built To Perform. Do /kuraac help for more info!");
                    return Command.SINGLE_SUCCESS;

                })
                .build()); // WE ARE THEMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
    }

}

