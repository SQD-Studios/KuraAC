package net.chamosmp.kuraac.Commands;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class KuraMainCommands {

    public static void register(Commands commands) {
        commands.register(Commands.literal("kuraac")
                        .then(Commands.literal("kuraac help"))
                        .executes(ctx ->{
                            ctx.getSource().getSender().sendPlainMessage("KuraAC Built To Perform. Do /kuraac help for more info!");
                            return Command.SINGLE_SUCCESS;
                        })

                .requires(sender -> sender.getSender().hasPermission("kuraac.*"))
                .executes(ctx -> {
                    ctx.getSource().getSender().sendPlainMessage("KuraAC Built To Perform. Do /kuraac help for more info!");
                    return Command.SINGLE_SUCCESS;

                })
                .build());
    }

}



