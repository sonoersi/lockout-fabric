package me.marin.lockout.lockout.goals.have_more;

import me.marin.lockout.Constants;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.texture.CustomTextureRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class HaveMoreUniqueCraftsGoal extends Goal implements CustomTextureRenderer {

    private static final ItemStack ITEM_STACK = Items.CRAFTING_TABLE.getDefaultStack();
    public HaveMoreUniqueCraftsGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Craft more unique Items than opponent";
    }

    @Override
    public ItemStack getTextureItemStack() {
        return ITEM_STACK;
    }

    private static final Identifier TEXTURE = new Identifier(Constants.NAMESPACE, "textures/custom/have_more_overlay.png");
    @Override
    public boolean renderTexture(DrawContext context, int x, int y, int tick) {
        context.drawItem(ITEM_STACK, x, y);
        context.drawTexture(TEXTURE, x, y, 400,0, 0, 16, 16, 16, 16);
        return true;
    }

}
