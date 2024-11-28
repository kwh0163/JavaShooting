package uiTitle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Enemyplane extends JPanel implements ActionListener {
    private Timer timer; // 게임 루프를 위한 타이머
    private Timer enemyIncreaseTimer; // 적 비행기 증가 타이머
    private ArrayList<Enemy> enemies; // 적 비행기 리스트
    private ArrayList<Bullet> enemyBullets; // 적 비행기 총탄 리스트
    private int maxEnemies = 5; // 최대 적 비행기 수

    public Enemyplane() {
        // 패널 기본 설정
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);

        // 초기화
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        // 게임 루프 타이머
        timer = new Timer(16, this); // 60 FPS
        timer.start();

        // 적 비행기 수 증가 타이머 (20초마다 증가)
        enemyIncreaseTimer = new Timer(20000, e -> { // 간격 변경: 20000ms = 20초
            maxEnemies++; // 최대 적 비행기 수 증가
        });
        enemyIncreaseTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 적 비행기 그리기
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // 적 비행기 총탄 그리기
        for (Bullet bullet : enemyBullets) {
            bullet.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 적 비행기 수 유지
        if (enemies.size() < maxEnemies) {
            Enemy enemy = new Enemy((int) (Math.random() * (760 - 50)), 0, enemyBullets, enemies); // 50은 비행기 너비
            enemies.add(enemy);
        }

        // 적 비행기 업데이트
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update();

            // 화면 밖으로 나가면 적 비행기 제거
            if (enemy.getY() > 600) {
                enemyIterator.remove();
            }
        }

        // 적 비행기 총탄 업데이트
        Iterator<Bullet> bulletIterator = enemyBullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();

            // 화면 밖으로 나가면 총탄 제거
            if (bullet.getY() > 600) {
                bulletIterator.remove();
            }
        }

        // 화면 업데이트
        repaint();
    }

    public static void mains(String[] args) {
        JFrame frame = new JFrame("Enemy Planes Shooting Game");
        Enemyplane game = new Enemyplane();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// 적 비행기 클래스
class Enemy {
    private int x, y, width, height, speedY;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Enemy> enemies;
    private Timer shootTimer, reloadTimer;
    private int bulletCount; // 현재 발사 가능 총알 수
    private final int MAX_BULLETS = 10; // 각 비행기가 발사할 수 있는 최대 총알 수

    public Enemy(int x, int y, ArrayList<Bullet> enemyBullets, ArrayList<Enemy> enemies) {
        this.x = x;
        this.y = y;
        this.width = 50; // 비행기 너비
        this.height = 50; // 비행기 높이
        this.speedY = 2 + (int) (Math.random() * 2); // Y축 이동 속도 (2 ~ 3 랜덤)

        this.enemyBullets = enemyBullets;
        this.enemies = enemies;
        this.bulletCount = MAX_BULLETS;

        shootTimer = new Timer(500, e -> fireBullet());
        reloadTimer = new Timer(2000, e -> reloadBullets());
        shootTimer.start(); // 총알 발사 시작
    }

    private void reloadBullets() {
        if (bulletCount == 0) {
            bulletCount = MAX_BULLETS; // 총알 재장전
        }
    }

    public void update() {
        y += speedY; // 아래로 이동

        // 비행기끼리 겹치지 않도록 충돌 회피
        for (Enemy otherEnemy : enemies) {
            if (otherEnemy != this && this.getBounds().intersects(otherEnemy.getBounds())) {
                // 겹칠 경우 X좌표를 조금씩 이동하여 회피
                if (this.x < otherEnemy.x) {
                    this.x -= 5; // 다른 비행기보다 오른쪽에 있으면 왼쪽으로
                } else {
                    this.x += 5; // 다른 비행기보다 왼쪽에 있으면 오른쪽으로
                }
                break; // 한 번만 회피
            }
        }
    }

    public void draw(Graphics g) {
        // 비행기를 다양한 도형으로 구성하여 그리기
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{x + 25, x, x + 50}, new int[]{y, y + 50, y + 50}, 3); // 삼각형 (앞부분)
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x + 10, y + 20, 30, 30); // 몸체
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x - 10, y + 30, 70, 10); // 날개
        g.fillRect(x + 20, y + 50, 10, 10); // 꼬리 부분
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    private void fireBullet() {
        if (bulletCount > 0) {
            int bulletX = x + width / 2 - 2; // 비행기 중심에서 발사
            int bulletY = y + height;
            enemyBullets.add(new Bullet(bulletX, bulletY, 5, Color.YELLOW));
            bulletCount--; // 총알 수 감소

            // 총알이 0개가 되면 재장전 타이머 시작
            if (bulletCount == 0) {
                reloadTimer.start();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

// 총탄 클래스
class Bullet {
    private int x, y, speed;
    private Color color;

    public Bullet(int x, int y, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
    }

    public void update() {
        y += speed; // 아래로 이동
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 5, 10); // 총탄 모양
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 10);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
