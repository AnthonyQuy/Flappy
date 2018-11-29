package com.anthony.antflappy.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        this.states = new Stack<State>();
    }

    public void push(State state) {
        this.states.push(state);
    }

    private void pop() {
        this.states.pop().dispose();
    }

    void set(State state) {
        this.pop();
        this.push(state);
    }

    public void update(float dt) {
        this.states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        this.states.peek().render(sb);
    }


}
