package net.hdt.neutronia.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.hdt.neutronia.util.Reference;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class CommandTeleportToDimension {

    private static final List<String> aliases = Lists.newArrayList(Reference.MOD_ID, "TP", "tp", "tpdim", "tpdimension", "teleport");

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = literalArgument("tpdim")
                .executes(context -> {
                    Entity entity = context.getSource().getEntity();
                    entity.world.newExplosion(null, entity.posX, entity.posY, entity.posZ, 1, true, true);
                    return 0;
                })
                .then(requiredArgument("dimension", IntegerArgumentType.integer(0))
                        .executes(context -> {
                            Entity entity = context.getSource().getEntity();
                            entity.world.newExplosion(null, entity.posX, entity.posY, entity.posZ, context.getArgument("strength", Integer.class), true, true);
                            return 0;
                        })
                        .then(requiredArgument("biome_name", StringArgumentType.string())
                                .executes(context -> 0)));
        dispatcher.register(builder);
    }

    private static LiteralArgumentBuilder<CommandSource> literalArgument(String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    private static <T> RequiredArgumentBuilder<CommandSource, T> requiredArgument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

}