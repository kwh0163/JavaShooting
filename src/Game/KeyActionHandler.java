package Game;

import javax.swing.*;
import Object.Player.PlayerInput;
import Util.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyActionHandler {
    private final Set<String> pressedKeys = new HashSet<>(); // 현재 눌린 키를 추적

    public void Initialize(JPanel _panel) {
        InputMap inputMap = _panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = _panel.getActionMap();

        bindKeys(inputMap, actionMap);
    }

    private void bindKeys(InputMap _inputMap, ActionMap _actionMap) {
        // 키 눌렀을 때
        _inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        _inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        _inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        _inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        // 키 뗐을 때
        _inputMap.put(KeyStroke.getKeyStroke("released UP"), "releaseUp");
        _inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "releaseDown");
        _inputMap.put(KeyStroke.getKeyStroke("released LEFT"), "releaseLeft");
        _inputMap.put(KeyStroke.getKeyStroke("released RIGHT"), "releaseRight");

        // 키 입력 처리
        _actionMap.put("moveUp", new KeyAction("UP", true));
        _actionMap.put("moveDown", new KeyAction("DOWN", true));
        _actionMap.put("moveLeft", new KeyAction("LEFT", true));
        _actionMap.put("moveRight", new KeyAction("RIGHT", true));

        // 키 해제 처리
        _actionMap.put("releaseUp", new KeyAction("UP", false));
        _actionMap.put("releaseDown", new KeyAction("DOWN", false));
        _actionMap.put("releaseLeft", new KeyAction("LEFT", false));
        _actionMap.put("releaseRight", new KeyAction("RIGHT", false));
    }

    private class KeyAction extends AbstractAction {
        private final String key;
        private final boolean isPressed;

        public KeyAction(String key, boolean isPressed) {
            this.key = key;
            this.isPressed = isPressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPressed) {
                pressedKeys.add(key); // 키 눌림 추적
            } else {
                pressedKeys.remove(key); // 키 해제 추적
            }

            // 눌린 키들을 기반으로 방향 계산
            Vector2 combinedDirection = calculateDirection();
            PlayerInput.instance.Move(combinedDirection); // 이동 처리
        }
    }

    // 눌린 키를 기반으로 방향 계산
    private Vector2 calculateDirection() {
        Vector2 direction = Vector2.Zero();

        if (pressedKeys.contains("UP")) {
            direction = Vector2.Add(direction, Vector2.Up());
        }
        if (pressedKeys.contains("DOWN")) {
            direction = Vector2.Add(direction, Vector2.Down());
        }
        if (pressedKeys.contains("LEFT")) {
            direction = Vector2.Add(direction, Vector2.Left());
        }
        if (pressedKeys.contains("RIGHT")) {
            direction = Vector2.Add(direction, Vector2.Right());
        }

        return direction.GetNormalized();
    }
}