package Test;

import javax.swing.*;

import Game.MainProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class COPY{
    private CardLayout cardLayout; // 화면 전환을 위한 CardLayout
    private JPanel cardPanel; // 여러 화면을 담을 패널

    public COPY() {

        // CardLayout과 cardPanel 초기화
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 메인 화면과 선택 화면 추가
        cardPanel.setPreferredSize(new Dimension(800, 600));
        cardPanel.add(createMainPanel(), "MainPanel");
        cardPanel.add(createChoicePanel(), "ChoicePanel");
    }
    
    public JPanel GetPanel() {
    	return cardPanel;
    }

    // 메인 화면 생성
    private JPanel createMainPanel() {
        // 배경 이미지 설정
        ImageIcon background = new ImageIcon("imgs/Title.png");

        // 커스텀 JPanel 생성
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(null); // 자유 배치를 위해 null 레이아웃 사용

        // 버튼 이미지 설정
        ImageIcon buttonImage = new ImageIcon("imgs/Button.png");
        JButton nextButton = new JButton(buttonImage);
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setBounds(200, -100, 400, 400);

        // 버튼 클릭 이벤트 처리
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 화면 전환
                cardLayout.show(cardPanel, "ChoicePanel");
            }
        });

        // 버튼에 커서 변경 기능 추가
        addCustomCursorToButton(nextButton, "imgs/cursor.png", "imgs/defaultCursor.png");

        // "close" 버튼 설정
        JButton outButton = new JButton("close");
        outButton.setBounds(300, 500, 200, 50); // 위치 및 크기 설정
        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainPanel, "close");
                System.exit(0);
            }
        });

        // "close" 버튼에 커서 변경 기능 추가
        addCustomCursorToButton(outButton, "imgs/cursor.png", "imgs/defaultCursor.png");

        // 버튼 추가
        mainPanel.add(nextButton);
        mainPanel.add(outButton);
        return mainPanel;
    }

    // 선택 화면 생성
    private JPanel createChoicePanel() {
        // 배경 이미지 추가
        JPanel choicePanel = new BackgroundPanel("imgs/Main3.png");
        choicePanel.setLayout(null);

        // 버튼 추가
        JButton option1 = createImageButton("Image/PlaneGray.png", 200, 150, "plane1");
        option1.setBounds(70, 250, 200, 150); // 위치 및 크기 설정
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainProgram.PlayerInt = 0;
            	MainProgram.StartGame();
            }
        });

        // 버튼에 커서 변경 기능 추가
        addCustomCursorToButton(option1, "imgs/cursor.png", "imgs/defaultCursor.png");

        JButton option2 = createImageButton("Image/PlaneRedAndWhite.png", 200, 150, "plane2");
        option2.setBounds(505, 250, 200, 150); // 위치 및 크기 설정
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainProgram.PlayerInt = 1;
            	MainProgram.StartGame();
            }
        });

        // 버튼에 커서 변경 기능 추가
        addCustomCursorToButton(option2, "imgs/cursor.png", "imgs/defaultCursor.png");

        JButton option3 = createImageButton("Image/PlaneGreen.png", 200, 150, "plane3");
        option3.setBounds(290, 60, 200, 150); // 위치 및 크기 설정
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainProgram.PlayerInt = 2;
            	MainProgram.StartGame();
            }
        });

        // 버튼에 커서 변경 기능 추가
        addCustomCursorToButton(option3, "imgs/cursor.png", "imgs/defaultCursor.png");

        // "Back to Title" 버튼 추가
        JButton option4 = new JButton("Back to Title");
        option4.setBounds(300, 400, 200, 50); // 위치 및 크기 설정
        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(choicePanel, "Back to Title");
                cardLayout.show(cardPanel, "MainPanel"); // 메인 화면으로 돌아감
            }
        });

        // "Back to Title" 버튼에 커서 변경 기능 추가
        addCustomCursorToButton(option4, "imgs/cursor.png", "imgs/defaultCursor.png");

        choicePanel.add(option1);
        choicePanel.add(option2);
        choicePanel.add(option3);
        choicePanel.add(option4);

        return choicePanel;
    }

    // 버튼에 커서 변경 기능을 추가하는 메서드
    private void addCustomCursorToButton(JButton button, String hoverCursorPath, String defaultCursorPath) {
        Cursor hoverCursor = createCustomCursor(hoverCursorPath, 70, 70);
        Cursor defaultCursor = createCustomCursor(defaultCursorPath, 50, 50);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(hoverCursor); // Hover 상태에서 커서를 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(defaultCursor); // 기본 커서로 복원
            }
        });
    }

    // 커서 이미지를 생성하는 메서드
    private Cursor createCustomCursor(String imagePath, int width, int height) {
        ImageIcon cursorIcon = new ImageIcon(imagePath); // 이미지 경로
        Image cursorImage = cursorIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH); // 크기 조정
        return Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "Custom Cursor");
    }

    // 이미지를 넣은 버튼을 생성하는 메서드
    private JButton createImageButton(String imagePath, int width, int height, String actionCommand) {
        // 이미지 아이콘 생성
        ImageIcon icon = new ImageIcon(imagePath);

        // 이미지 크기 조정
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // 버튼 생성 및 설정
        JButton button = new JButton(scaledIcon);
        button.setActionCommand(actionCommand);
        button.setPreferredSize(new Dimension(width, height));
        button.setContentAreaFilled(false); // 버튼 배경 제거
        button.setBorderPainted(false); // 버튼 테두리 제거
        button.setFocusPainted(false); // 버튼 포커스 제거

        return button;
    }

    // 배경 이미지를 그리는 JPanel 클래스
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            // 이미지 로딩
            ImageIcon icon = new ImageIcon(imagePath);
            backgroundImage = icon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 패널 크기만큼 배경 이미지를 그립니다.
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new COPY();
    }
}
