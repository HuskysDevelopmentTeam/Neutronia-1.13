package net.hdt.neutronia.init;

import com.google.common.collect.Lists;
import net.hdt.neutronia.entity.*;
import net.hdt.neutronia.entity.projectile.EntitySpear;
import net.hdt.neutronia.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import org.dimdev.rift.listener.EntityTypeAdder;

import java.util.List;
import java.util.function.Function;

public class NEntityTypes implements EntityTypeAdder {

    public static final List<EntityType> ENTITIES = Lists.newArrayList();

    public static EntityType AXOLOTL;
    public static EntityType GULPER_SQUID;
    public static EntityType ANCIENT_GOLEM;
    public static EntityType CLAY_GOLEM;
    public static EntityType DIAMOND_GOLEM;
    public static EntityType GOLD_GOLEM;
    public static EntityType PHARAOH_GOLEM;
    public static EntityType STEAMPUNK_GOLEM;
    public static EntityType WOOD_GOLEM;
    public static EntityType YETI_GOLEM;
    public static EntityType ALBADON;
    public static EntityType ANCHORED;
    public static EntityType FIREFLY;
    public static EntityType FORSAKEN;
    public static EntityType SPEAR;
    public static EntityType AXODILE;
    public static EntityType BLOOD_PHANTOM;
    public static EntityType ENDER_PHANTOM;
    public static EntityType SHADOW_PHANTOM;
    public static EntityType MUMMY;
    public static EntityType ARCTIC_WOLF;
    public static EntityType SCUBA_DIVER;
    public static EntityType DROWNED_SCUBA_VILLAGER;
    public static EntityType WELL_WISHER;
    public static EntityType FOX;
    public static EntityType GREAT_HUNGER;
    public static EntityType INFERNO;
    public static EntityType LOST_MINER;
    public static EntityType NECROMANCER;
    public static EntityType OL_DIGGY;
    public static EntityType SANDDIVER_AQUATIC;
    public static EntityType SEA_SWALLOWED_CAPTAIN;
    public static EntityType WHALE;
    public static EntityType PLATYPUS;
    public static EntityType MUMMY_VILLAGER;
    public static EntityType SCORPION;

    @Override
    public void registerEntityTypes() {
         AXOLOTL = registerEntityType("axolotl", EntityAxolotl.class, EntityAxolotl::new);
         GULPER_SQUID = registerEntityType("gulper_squid", EntityGulperSquid.class, EntityGulperSquid::new);
         ANCIENT_GOLEM = registerEntityType("ancient_golem", EntityAncientGolem.class, EntityAncientGolem::new);
         CLAY_GOLEM = registerEntityType("clay_golem", EntityClayGolem.class, EntityClayGolem::new);
         DIAMOND_GOLEM = registerEntityType("diamond_golem", EntityDiamondGolem.class, EntityDiamondGolem::new);
         GOLD_GOLEM = registerEntityType("gold_golem", EntityGoldGolem.class, EntityGoldGolem::new);
         PHARAOH_GOLEM = registerEntityType("pharaoh_golem", EntityPharaohGolem.class, EntityPharaohGolem::new);
         STEAMPUNK_GOLEM = registerEntityType("steampunk_golem", EntitySteampunkGolem.class, EntitySteampunkGolem::new);
         WOOD_GOLEM = registerEntityType("wood_golem", EntityWoodGolem.class, EntityWoodGolem::new);
         YETI_GOLEM = registerEntityType("yeti_golem", EntityYetiGolem.class, EntityYetiGolem::new);
         ALBADON = registerEntityType("albadon", EntityAlbadon.class, EntityAlbadon::new);
         ANCHORED = registerEntityType("anchored", EntityAnchored.class, EntityAnchored::new);
         FIREFLY = registerEntityType("firefly", EntityFirefly.class, EntityFirefly::new);
         FORSAKEN = registerEntityType("forsaken", EntityForsakenDiver.class, EntityForsakenDiver::new);
         SPEAR = registerEntityType("spear", EntitySpear.class, EntitySpear::new);
         AXODILE = registerEntityType("axodile", EntityAxodile.class, EntityAxodile::new);
         BLOOD_PHANTOM = registerEntityType("blood_phantom", EntityBloodPhantom.class, EntityBloodPhantom::new);
         ENDER_PHANTOM = registerEntityType("ender_phantom", EntityEnderPhantom.class, EntityEnderPhantom::new);
         SHADOW_PHANTOM = registerEntityType("shadow_phantom", EntityShadowPhantom.class, EntityShadowPhantom::new);
         MUMMY = registerEntityType("mummy", EntityMummy.class, EntityMummy::new);
         ARCTIC_WOLF = registerEntityType("arctic_wolf", EntityArcticWolf.class, EntityArcticWolf::new);
         SCUBA_DIVER = registerEntityType("scuba_diver", EntityScubaDiver.class, EntityScubaDiver::new);
         DROWNED_SCUBA_VILLAGER = registerEntityType("drowned_scuba_villager", EntityDrownedScubaVillager.class, EntityDrownedScubaVillager::new);
         WELL_WISHER = registerEntityType("well_wisher", EntityWellWisher.class, EntityWellWisher::new);
         FOX = registerEntityType("fox", EntityFox.class, EntityFox::new);
         GREAT_HUNGER = registerEntityType("great_hunger", EntityGreatHunger.class, EntityGreatHunger::new);
         INFERNO = registerEntityType("inferno", EntityInferno.class, EntityInferno::new);
         LOST_MINER = registerEntityType("lost_miner", EntityLostMiner.class, EntityLostMiner::new);
         NECROMANCER = registerEntityType("necromancer", EntityNecromancer.class, EntityNecromancer::new);
         OL_DIGGY = registerEntityType("ol_diggy", EntityOlDiggy.class, EntityOlDiggy::new);
         SANDDIVER_AQUATIC = registerEntityType("aquatic_sanddiver", EntitySandDiverAquatic.class, EntitySandDiverAquatic::new);
         SEA_SWALLOWED_CAPTAIN = registerEntityType("sea_swallowed_captain", EntitySeaSwallowedCaptain.class, EntitySeaSwallowedCaptain::new);
         WHALE = registerEntityType("whale", EntityWhale.class, EntityWhale::new);
         PLATYPUS = registerEntityType("platypus", EntityPlatypus.class, EntityPlatypus::new);
    }

    private static EntityType registerEntityType(String name, Class<? extends Entity> clazz, Function<? super World, ? extends Entity> entity) {
        EntityType type = EntityType.registerEntityType(Reference.MOD_ID + ":" + name, EntityType.Builder.create(clazz, entity).func_200706_c());
        ENTITIES.add(type);
        return type;
    }

}