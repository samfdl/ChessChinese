package com.samfdl.chesschinese;

import com.samfdl.chesschinese.pojo.Position;

import java.util.ArrayList;

/**
 * Created by samfdl on 2017/8/5.
 * 棋子
 */
public class ChessMan {
    // 将、帅，一军之主也。故不得出中军帐，九宫格也。
    public final static int KING = 1;
    // 士、仕，贴身侍卫也。不离主将左右，防刺客。主将死，三军皆斩。故亦不敢出中军帐，九宫格也。且斜向巡逻，穿梭不离主将左右。
    public final static int SHI = 2;
    // 相、象，军师，文官，押运粮草也。故不得出己方阵营，且斜向照应己方粮草。
    public final static int ADVISER = 3;
    // 马、馬、㐷，骑兵也。斜向冲杀，威猛异常之奇兵。但脚力有限，走不远。
    public final static int CAVALRY = 4;
    // 车、車、俥，战车也。横冲直撞，杀伤力极远。但因太快，无法拐弯。
    public final static int TANK = 5;
    // 炮、砲，投石车，弓箭手，弩机手也。因发射时易受攻击，故需前面有盾牌兵掩护。无掩护则不能开火。
    public final static int CANNON = 6;
    // 卒、兵，步兵，民兵也。因装备训练成本低，故移动缓慢，且只能向前冲，不得后退。冲入敌方阵营后方可横行。故有五支，开局压住阵脚，以张军威。
    public final static int ARMY = 7;

    // ID
    private int id;
    // 类型
    private int type;
    // 阵营
    private boolean isRed;
    // 棋子图片
    private int image;
    // 位置
    private Position position;
    // 走子规则
    private ArrayList<Position> nextPosition;
    // 吃子规则
    private ArrayList<Position> eatPosition;
    // 是否已过河
    private boolean isCrossed;
    // 是否已被选中
    private boolean isSelected;
    // 是否正在叫吃
    private boolean isDangerous;

    public ChessMan(int id) {
        this.id = id;
        setType();
        setRed();
        setImage();
        setPosition();
    }

    private void setType() {
        int remainder = id % 16;
        switch (remainder) {
            case 1:
                type = KING;
                break;
            case 2:
            case 3:
                type = SHI;
                break;
            case 4:
            case 5:
                type = ADVISER;
                break;
            case 6:
            case 7:
                type = CAVALRY;
                break;
            case 8:
            case 9:
                type = TANK;
                break;
            case 10:
            case 11:
                type = CANNON;
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 0:
                type = ARMY;
                break;
        }
    }

    private void setRed() {
        if (id <= 16) {
            isRed = true;
        } else {
            isRed = false;
        }
    }

    private void setImage() {
        switch (id) {
            case 1:
                image = R.mipmap.red_king;
                break;
            case 2:
            case 3:
                image = R.mipmap.red_shi;
                break;
            case 4:
            case 5:
                image = R.mipmap.red_adviser;
                break;
            case 6:
            case 7:
                image = R.mipmap.red_cavalry;
                break;
            case 8:
            case 9:
                image = R.mipmap.red_tank;
                break;
            case 10:
            case 11:
                image = R.mipmap.red_cannon;
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                image = R.mipmap.red_army;
                break;
            case 17:
                image = R.mipmap.black_king;
                break;
            case 18:
            case 19:
                image = R.mipmap.black_shi;
                break;
            case 20:
            case 21:
                image = R.mipmap.black_adviser;
                break;
            case 22:
            case 23:
                image = R.mipmap.black_cavalry;
                break;
            case 24:
            case 25:
                image = R.mipmap.black_tank;
                break;
            case 26:
            case 27:
                image = R.mipmap.black_cannon;
                break;
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                image = R.mipmap.black_army;
                break;
        }
    }

    private int[][] chessPosition = new int[][]{ // 棋盘 10行 9列 ， 初始各位置数字
            {24, 22, 20, 18, 17, 19, 21, 23, 25},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 26, 0, 0, 0, 0, 0, 27, 0},
            {28, 0, 29, 0, 30, 0, 31, 0, 32},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {12, 0, 13, 0, 14, 0, 15, 0, 16},
            {0, 10, 0, 0, 0, 0, 0, 11, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {8, 6, 4, 2, 1, 3, 5, 7, 9}};

    private void setPosition() {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 9; i++) {
                if (chessPosition[j][i] == id) {
                    position = new Position(i, j);
                }
            }
        }
    }

    private int[][] kingNext = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] shiNext = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    private int[][] adviserNext = {{-2, -2}, {2, -2}, {-2, 2}, {2, 2}};
    private int[][] cavalryNext = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};

    public void setNextPosition() {
        nextPosition = new ArrayList<>();
        switch (type) {
            case KING:
                for (int i = 0; i < 4; i++) {
                    setKingNextPosition(position.getX() + kingNext[i][0], position.getY() + kingNext[i][1]);
                }
                break;
            case SHI:
                for (int i = 0; i < 4; i++) {
                    setKingNextPosition(position.getX() + shiNext[i][0], position.getY() + shiNext[i][1]);
                }
                break;
            case ADVISER:
                for (int i = 0; i < 4; i++) {
                    setAdviserNextPosition(position.getX() + adviserNext[i][0], position.getY() + adviserNext[i][1]);
                }
                break;
            case CAVALRY:
                for (int i = 0; i < 8; i++) {
                    setCavalryNextPosition(position.getX() + cavalryNext[i][0], position.getY() + cavalryNext[i][1]);
                }
                break;
            case TANK:
                for (int i = 0; i < 4; i++) {
                    setKingNextPosition(position.getX() + shiNext[i][0], position.getY() + shiNext[i][1]);
                }
                break;
            case CANNON:
                for (int i = 0; i < 4; i++) {
                    setKingNextPosition(position.getX() + shiNext[i][0], position.getY() + shiNext[i][1]);
                }
                break;
            case ARMY:
                for (int i = 0; i < 4; i++) {
                    setArmyNextPosition(position.getX() + kingNext[i][0], position.getY() + kingNext[i][1]);
                }
                break;
        }
    }

    // 将、士的下一步走子位置
    private void setKingNextPosition(int x, int y) {
        // 九宫格走子范围
        int x1 = 3;
        int x2 = x1 + 2;
        int y1 = 0;
        if (isRed) {
            y1 += 7;
        }
        int y2 = y1 + 2;
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            nextPosition.add(new Position(x, y));
        }
    }

    // 相的下一步走子位置
    private void setAdviserNextPosition(int x, int y) {
        int x1 = 0;
        int x2 = x1 + 8;
        int y1 = 0;
        if (isRed) {
            y1 += 5;
        }
        int y2 = y1 + 4;
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            nextPosition.add(new Position(x, y));
        }
    }

    // 马的下一步走子位置
    private void setCavalryNextPosition(int x, int y) {
        int x1 = 0;
        int x2 = x1 + 8;
        int y1 = 0;
        int y2 = y1 + 9;
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            nextPosition.add(new Position(x, y));
        }
    }

    // 卒的下一步走子位置
    private void setArmyNextPosition(int x, int y) {
        int x1 = 0;
        int x2 = x1 + 8;
        int y1 = 0;
        int y2 = y1 + 9;
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            nextPosition.add(new Position(x, y));
        }
    }

    private void setEatPosition() {
        eatPosition.add(new Position(1, 1));
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public boolean isRed() {
        return isRed;
    }

    public int getImage() {
        return image;
    }

    public void setPosition(Position position) {
        // 设置是否已过河
        int lastY = position.getY();
        if (type == ARMY && !isCrossed) {
            if ((position.getY() - lastY) == 2) {
                isCrossed = true;
            }
        }
        this.position = position;

        setNextPosition();
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Position> getNextPosition() {
        return nextPosition;
    }

    public ArrayList<Position> getEatPosition() {
        return eatPosition;
    }

    public boolean isCrossed() {
        return isCrossed;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }
}