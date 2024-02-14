package me.marin.lockout.client.gui;

import me.marin.lockout.lockout.Goal;

import java.util.*;

public class BoardBuilderData {

    public static BoardBuilderData INSTANCE;
    static {
        INSTANCE = new BoardBuilderData();
    }

    private final List<Goal> goals = new ArrayList<>();
    private final List<String> goalData = new ArrayList<>();
    private String title = "";
    private Integer editingIdx = null;

    private BoardBuilderData() {
        for (int i = 0; i < 25; i++) {
            goals.add(null);
            goalData.add(null);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEditingIdx() {
        return editingIdx;
    }

    public Goal getEditingGoal() {
        return goals.get(editingIdx);
    }

    public void setEditingIdx(Integer editingIdx) {
        this.editingIdx = editingIdx;
    }

    public List<Goal> getGoals() {
        return Collections.unmodifiableList(goals);
    }

    public void setGoal(Goal goal) {
        goals.set(editingIdx, goal);
    }
    public void setGoalData(String data) {
        goalData.set(editingIdx, data);
    }


}
