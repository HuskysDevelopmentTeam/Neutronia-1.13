package net.hdt.neutronia.entity.projectile;

import net.hdt.neutronia.init.NEntityTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Particles;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySpear extends EntityThrowable
{
    public EntitySpear(World worldIn)
    {
        super(NEntityTypes.SPEAR, worldIn);
    }

    public EntitySpear(World worldIn, EntityLivingBase throwerIn)
    {
        super(NEntityTypes.SPEAR, throwerIn, worldIn);
    }

    public EntitySpear(World worldIn, double x, double y, double z)
    {
        super(NEntityTypes.SPEAR, x, y, z, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entity != null)
        {
            result.entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 1);
        }

        for (int j = 0; j < 8; ++j)
        {
            this.world.spawnParticle(Particles.ITEM_SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote)
        {
            this.remove();
        }
    }
}