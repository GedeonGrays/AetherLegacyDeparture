package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.DivineSunSpiritModel;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntityDivineSunSpirit;

public class DivineSunSpiritRenderer extends RenderBiped {

    private static final ResourceLocation SPIRIT = Aether.locate("textures/bosses/sun_spirit/divine_sun_spirit.png");

    private static final ResourceLocation SPIRIT_FROZE = Aether.locate("textures/bosses/sun_spirit/frozen_sun_spirit.png");

    public DivineSunSpiritRenderer() {
        super(new DivineSunSpiritModel(0.0F, 0.0F), 1.0F);

        this.shadowSize = 0.8F;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase spirit, float partialTickTime) {
        GL11.glTranslated(0.0D, 0.5D, 0.0D);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return ((EntityDivineSunSpirit) entity).isFreezing() ? SPIRIT_FROZE : SPIRIT;
    }

}
