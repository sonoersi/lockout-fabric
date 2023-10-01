package me.marin.lockout;

import me.marin.lockout.client.LockoutClient;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.texture.CustomTextureRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import static me.marin.lockout.Constants.*;
import static me.marin.lockout.Constants.GUI_ITEM_SLOT_SIZE;

public class Utility {

    public static void drawBingoBoard(DrawContext context, int x, int y) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        context.drawTexture(Constants.GUI_IDENTIFIER, x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT, GUI_WIDTH, GUI_HEIGHT);

        x += GUI_FIRST_ITEM_OFFSET;
        y += GUI_FIRST_ITEM_OFFSET;
        final int startX = x;

        Lockout lockout = Lockout.getInstance();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Goal goal = lockout.getBoard().getGoals().get(j + 5 * i);
                if (goal != null) {
                    if (goal.isCompleted()) {
                        context.fill(x, y, x + 16, y + 16, goal.getCompletedTeam().getColor().getColorValue());
                    }

                    boolean success = false;
                    if (goal instanceof CustomTextureRenderer customTextureRenderer) {
                        success = customTextureRenderer.renderTexture(context, x, y, LockoutClient.CURRENT_TICK);
                    }

                    if (!success) {
                        context.drawItem(goal.getTextureItemStack(), x, y);
                        context.drawItemInSlot(textRenderer, goal.getTextureItemStack(), x, y);
                    }

                }
                x += GUI_ITEM_SLOT_SIZE;
            }
            y += GUI_ITEM_SLOT_SIZE;
            x = startX;
        }
        x += 2;
        y += 1;
        List<String> pointsList = new ArrayList<>();
        for (LockoutTeam team : lockout.getTeams()) {
            pointsList.add(team.getColor() + "" + team.getPoints() + "" + Formatting.RESET);
        }

        context.drawText(textRenderer, String.join(Formatting.RESET + "" + Formatting.GRAY + ":", pointsList), x, y, 0, true);
        if (lockout.hasStarted()) {
            long duration = System.currentTimeMillis() - lockout.getStartTime();
            long second = (duration / 1000) % 60;
            long minute = (duration / (1000 * 60)) % 60;
            long hour = (duration / (1000 * 60 * 60)) % 24;

            String time;
            if (hour > 0) {
                time = String.format("%02d:%02d:%02d", hour, minute, second);
            } else {
                time = String.format("%02d:%02d", minute, second);
            }

            context.drawText(textRenderer, Formatting.WHITE + time, context.getScaledWindowWidth() - textRenderer.getWidth(time) - 3, y, 0, true);
        }
    }

    public static void drawCenterBingoBoard(DrawContext context, int mouseX, int mouseY) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        int x = width / 2 - Constants.GUI_WIDTH / 2;
        int y = height / 2 - Constants.GUI_HEIGHT / 2;

        context.drawTexture(GUI_CENTER_IDENTIFIER, x, y, 0, 0, GUI_CENTER_WIDTH, GUI_CENTER_HEIGHT, GUI_CENTER_WIDTH, GUI_CENTER_HEIGHT);

        x += GUI_CENTER_FIRST_ITEM_OFFSET_X;
        y += GUI_CENTER_FIRST_ITEM_OFFSET_Y;
        final int startX = x;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Goal goal = Lockout.getInstance().getBoard().getGoals().get(j + 5 * i);
                if (goal != null) {
                    if (goal.isCompleted()) {
                        context.fill(x, y, x + 16, y + 16, goal.getCompletedTeam().getColor().getColorValue());
                    }

                    boolean success = false;
                    if (goal instanceof CustomTextureRenderer customTextureRenderer) {
                        success = customTextureRenderer.renderTexture(context, x, y, LockoutClient.CURRENT_TICK);
                    }

                    if (!success) {
                        context.drawItem(goal.getTextureItemStack(), x, y);
                        context.drawItemInSlot(textRenderer, goal.getTextureItemStack(), x, y);
                    }

                    if (goal == getCenterHoveredGoal(context, mouseX, mouseY)) {
                        context.fill(x, y, x + 16, y + 16, 400, -2130706433);
                    }
                }
                x += GUI_CENTER_ITEM_SLOT_SIZE;
            }
            y += GUI_CENTER_ITEM_SLOT_SIZE;
            x = startX;
        }
    }

    public static Goal getCenterHoveredGoal(DrawContext context, int mouseX, int mouseY) {
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        int x = width / 2 - Constants.GUI_WIDTH / 2 + GUI_CENTER_FIRST_ITEM_OFFSET_X;
        int y = height / 2 - Constants.GUI_HEIGHT / 2 + GUI_CENTER_FIRST_ITEM_OFFSET_Y;
        final int startX = x;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mouseX >= x-1 && mouseX < x+18 && mouseY >= y-1 && mouseY < y+18) {
                    return Lockout.getInstance().getBoard().getGoals().get(j + i * 5);
                }
                x += GUI_CENTER_ITEM_SLOT_SIZE;
            }
            y += GUI_CENTER_ITEM_SLOT_SIZE;
            x = startX;
        }

        return null;
    }

}
