package net.hdt.neutronia.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBandage extends ItemBase {

    public ItemBandage() {
        super("bandage", ItemGroup.COMBAT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.getHealth() < playerIn.getMaxHealth() && !worldIn.isRemote && playerIn.getHeldItem(handIn).getItem() == this) {
            float f = playerIn.getHealth();
            playerIn.setHealth(f + 1.0F);
            playerIn.getHeldItem(handIn).shrink(1);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TextComponentTranslation(I18n.format("item.neutronia.bandage.desc_1")));
        tooltip.add(new TextComponentTranslation(I18n.format("item.neutronia.bandage.desc_2")));
    }

}
