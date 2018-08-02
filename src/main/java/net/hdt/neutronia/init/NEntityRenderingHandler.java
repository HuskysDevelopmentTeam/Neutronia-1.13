package net.hdt.neutronia.init;

import net.hdt.neutronia.entity.*;
import net.hdt.neutronia.entity.render.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.dimdev.rift.listener.client.EntityRendererAdder;

import java.util.Map;

public class NEntityRenderingHandler implements EntityRendererAdder {

    @Override
    public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager renderManager) {
        entityRenderMap.put(EntityAxodile.class, new RenderAxodile(renderManager));
        entityRenderMap.put(EntityMummy.class, new RenderMummy(renderManager));
        entityRenderMap.put(EntityMummyVillager.class, new RenderMummyVillager(renderManager));
        entityRenderMap.put(EntityBloodPhantom.class, new RenderRedPhantom(renderManager));
        entityRenderMap.put(EntityEnderPhantom.class, new RenderEnderPhantom(renderManager));
        entityRenderMap.put(EntityShadowPhantom.class, new RenderShadowPhantom(renderManager));
        entityRenderMap.put(EntityInferno.class, new RenderInferno(renderManager));
        entityRenderMap.put(EntityAnchored.class, new RenderAnchored(renderManager));
        entityRenderMap.put(EntityLostMiner.class, new RenderLostMiner(renderManager));
        entityRenderMap.put(EntityYetiGolem.class, new RenderYetiGolem(renderManager));
        entityRenderMap.put(EntitySteampunkGolem.class, new RenderSteampunkGolem(renderManager));
        entityRenderMap.put(EntityPharaohGolem.class, new RenderPharaoGolem(renderManager));
        entityRenderMap.put(EntityForsakenDiver.class, new RenderForsakenDiver(renderManager));
        entityRenderMap.put(EntityScubaVillager.class, new RenderScubaVillager(renderManager));
        entityRenderMap.put(EntityDrownedScubaVillager.class, new RenderDrownedScubaVillager(renderManager));
        entityRenderMap.put(EntitySandDiverAquatic.class, new RenderSandDiverAquatic(renderManager));
        entityRenderMap.put(EntityAlbadon.class, new RenderAlbadon(renderManager));
        entityRenderMap.put(EntityAxolotl.class, new RenderAxolotl(renderManager));
        entityRenderMap.put(EntityArcticWolf.class, new RenderArcticWolf(renderManager));
        entityRenderMap.put(EntityFox.class, new RenderFox(renderManager));
        entityRenderMap.put(EntitySeaSwallowedCaptain.class, new RenderSeaSwallowedCaptain(renderManager));
        entityRenderMap.put(EntityOlDiggy.class, new RenderOlDiggy(renderManager));
        entityRenderMap.put(EntityWellWisher.class, new RenderWellWisher(renderManager));
        entityRenderMap.put(EntityScorp.class, new RenderScorp(renderManager));
        entityRenderMap.put(EntityPlatypus.class, new RenderPlatypus(renderManager));
    }

}
