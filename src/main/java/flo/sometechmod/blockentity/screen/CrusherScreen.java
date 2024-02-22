package flo.sometechmod.blockentity.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import flo.sometechmod.SomeTechMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CrusherScreen extends HandledScreen<CrusherScreenHandler>
{
    private static final Identifier TEXTURE = new Identifier(SomeTechMod.MOD_ID, "textures/gui/crusher_gui.png");

    public CrusherScreen(CrusherScreenHandler handler, PlayerInventory inventory, Text title) { super(handler, inventory, title); }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderArrow(context, x, y);
    }

    private void renderArrow(DrawContext context, int x, int y)
    {
        if (handler.isCrafting())
        {
            context.drawTexture(TEXTURE, x + 80, y + 38, 176, 0, handler.getArrowProgress(), 23);
        }
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta)
    {
        renderBackground(drawContext);
        super.render(drawContext, mouseX, mouseY, delta);
        drawMouseoverTooltip(drawContext, mouseX, mouseY);
    }

    @Override
    protected void init()
    {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
