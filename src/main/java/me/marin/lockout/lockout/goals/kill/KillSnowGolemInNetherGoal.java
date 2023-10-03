package me.marin.lockout.lockout.goals.kill;

import me.marin.lockout.Constants;
import net.minecraft.util.Identifier;

public class KillSnowGolemInNetherGoal extends KillSnowGolemGoal {

    public KillSnowGolemInNetherGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Kill a Snow Golem in The Nether";
    }

    private static final Identifier TEXTURE = new Identifier(Constants.NAMESPACE, "textures/custom/kill/kill_snowman_nether.png");
    @Override
    public Identifier getTextureIdentifier() {
        return TEXTURE;
    }

}
