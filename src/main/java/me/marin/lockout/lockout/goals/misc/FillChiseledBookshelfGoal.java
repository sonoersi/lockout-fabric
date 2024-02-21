package me.marin.lockout.lockout.goals.misc;

import me.marin.lockout.Constants;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.texture.TextureProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FillChiseledBookshelfGoal extends Goal implements TextureProvider {

    public FillChiseledBookshelfGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Fill a Chiseled Bookshelf";
    }

    @Override
    public ItemStack getTextureItemStack() {
        return null;
    }

    private static final Identifier TEXTURE = new Identifier(Constants.NAMESPACE, "textures/custom/fill_chiseled_bookshelf.png");
    @Override
    public Identifier getTextureIdentifier() {
        return TEXTURE;
    }

}
