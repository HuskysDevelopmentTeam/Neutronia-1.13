package net.hdt.neutronia.init;

import com.mojang.brigadier.CommandDispatcher;
import net.hdt.neutronia.commands.CommandFindBiome;
import net.hdt.neutronia.commands.CommandTeleportToDimension;
import net.minecraft.command.CommandSource;
import org.dimdev.rift.listener.CommandAdder;

public class NCommands implements CommandAdder {

    @Override
    public void registerCommands(CommandDispatcher<CommandSource> dispatcher) {
        CommandFindBiome.register(dispatcher);
        CommandTeleportToDimension.register(dispatcher);
    }

}
