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
                                .executes(context -> {

                                    return 0;
                                })));
        dispatcher.register(builder);
    }

    private static LiteralArgumentBuilder<CommandSource> literalArgument(String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    private static <T> RequiredArgumentBuilder<CommandSource, T> requiredArgument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    static class Facing {
        private final Vec3d field_200549_a;
        private final Entity field_200550_b;
        private final EntityAnchorArgument.Type field_201125_c;

        public Facing(Entity p_i48274_1_, EntityAnchorArgument.Type p_i48274_2_) {
            this.field_200550_b = p_i48274_1_;
            this.field_201125_c = p_i48274_2_;
            this.field_200549_a = p_i48274_2_.func_201017_a(p_i48274_1_);
        }

        public Facing(Vec3d p_i48246_1_) {
            this.field_200550_b = null;
            this.field_200549_a = p_i48246_1_;
            this.field_201125_c = null;
        }

        public void func_201124_a(CommandSource p_201124_1_, Entity p_201124_2_) {
            if (this.field_200550_b != null) {
                if (p_201124_2_ instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)p_201124_2_).func_200618_a(p_201124_1_.getEntityAnchorType(), this.field_200550_b, this.field_201125_c);
                } else {
                    p_201124_2_.func_200602_a(p_201124_1_.getEntityAnchorType(), this.field_200549_a);
                }
            } else {
                p_201124_2_.func_200602_a(p_201124_1_.getEntityAnchorType(), this.field_200549_a);
            }

        }
    }

}