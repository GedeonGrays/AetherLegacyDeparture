package com.gildedgames.the_aether.client.renders.entity;

import com.gildedgames.the_aether.Aether;
import com.gildedgames.the_aether.client.models.entities.EnhancedSliderModel;
import com.gildedgames.the_aether.entities.bosses.slider.EntityEnhancedSlider;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class EnhancedSliderRenderer extends RenderLiving {

    private static final ResourceLocation TEXTURE_AWAKE_RED = Aether.locate("textures/bosses/slider/ancient_slider_awake_critical.png");

    private static final ResourceLocation TEXTURE_AWAKE = Aether.locate("textures/bosses/slider/ancient_slider_awake.png");

    private static final ResourceLocation TEXTURE_SLEEP = Aether.locate("textures/bosses/slider/ancient_slider_asleep.png");

    private static final ResourceLocation TEXTURE_SLEEP_RED = Aether.locate("textures/bosses/slider/ancient_slider_asleep_critical.png");

    private static final ResourceLocation TEXTURE_GLOW = Aether.locate("textures/bosses/slider/slider_awake_glow.png");

    private static final ResourceLocation TEXTURE_GLOW_RED = Aether.locate("textures/bosses/slider/slider_awake_critical_glow.png");

    public EnhancedSliderRenderer() {
        super(new EnhancedSliderModel(0.0F, 12.0F), 1.5F);

        this.setRenderPassModel(this.mainModel);
    }

    protected int renderEyeGlow(EntityEnhancedSlider slider, int pass, float particleTicks) {
        if (slider.isInvisible()) {
            return 0;
        } else if (pass == 0 && slider.isAwake()) {
            this.bindTexture(slider.criticalCondition() ? TEXTURE_GLOW_RED : TEXTURE_GLOW);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (slider.isInvisible()) {
                GL11.glDepthMask(false);
            } else {
                GL11.glDepthMask(true);
            }

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            return 1;
        }

        return -1;
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float particleTicks) {
        return this.renderEyeGlow((EntityEnhancedSlider) entity, pass, particleTicks);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f) {
    	EntityEnhancedSlider slider = (EntityEnhancedSlider) entity;

        ((EnhancedSliderModel) this.mainModel).hurtAngle = slider.hurtAngle;
        ((EnhancedSliderModel) this.mainModel).hurtAngleX = slider.hurtAngleX;
        ((EnhancedSliderModel) this.mainModel).hurtAngleZ = slider.hurtAngleZ;
    }

    protected void renderLivingAt(EntityEnhancedSlider slider, double x, double y, double z) {
        super.renderLivingAt(slider, x, y, z);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
    	EntityEnhancedSlider slider = (EntityEnhancedSlider) entity;

        if (slider.isAwake()) {
            return slider.criticalCondition() ? TEXTURE_AWAKE_RED : TEXTURE_AWAKE;
        }

        return slider.criticalCondition() ? TEXTURE_SLEEP_RED : TEXTURE_SLEEP;
    }

}
