package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Interfaces.IEnhancedEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;

public class EnchantmentAdvancedSmite extends EnchantmentBase implements IEnchantmentDamage, IEnhancedEnchantment  {
	public EnchantmentAdvancedSmite()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AdvancedSmite");
		this.setRegistryName("AdvancedSmite");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedSmiteEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.AdvancedSmite;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }
    
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentDamage) && !(fTest instanceof IEnchantmentDamage);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	 return fTest.getItem() instanceof ItemAxe ? true : super.canApply(fTest);
    }
    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
    {
    	if(!(entiti instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase entity = (EntityLivingBase) entiti;
    
    	if(entity.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD && level > 2)
 		{				
 			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 80 + (level * 10), MathHelper.clamp(level - 3, 0 , 1)));			
 		}
    }
    
    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
        return creatureType == EnumCreatureAttribute.UNDEAD ? (float)level * 3.25F : 0.0f;
    }

    }
