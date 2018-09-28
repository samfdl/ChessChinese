package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/27.
 * 黑方炮
 * 炮、砲，投石车，弓箭手，弩机手也。
 * 因发射时易受攻击，故需前面有盾牌兵掩护。
 * 无掩护则不能开火。
 */
public class Pao extends QiZi {
    public Pao(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        image = R.mipmap.black_cannon;
        setImageResource(image);
    }

    // 炮的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        // 上
        for (int i = 1; i <= 9; i++) {
            if (y - i >= 0) {
                if (map[y - i][x] > 0) {
                    // 隔山打牛
                    for (int j = 1; j <= 9 - i; j++) {
                        if (y - i - j >= 0) {
                            if (map[y - i - j][x] > 0 && map[y - i - j][x] < 17) {
                                nextPosition.add(new Position(x, y - i - j));
                                break;
                            }
                        }
                    }
                    break;
                }
                nextPosition.add(new Position(x, y - i));
            }
        }

        // 下
        for (int i = 1; i <= 9; i++) {
            if (y + i <= 9) {
                if (map[y + i][x] > 0) {
                    // 隔山打牛
                    for (int j = 1; j <= 9 - i; j++) {
                        if (y + i + j <= 9) {
                            if (map[y + i + j][x] > 0 && map[y + i + j][x] < 17) {
                                nextPosition.add(new Position(x, y + i + j));
                                break;
                            }
                        }
                    }
                    break;
                }
                nextPosition.add(new Position(x, y + i));
            }
        }

        // 左
        for (int i = 1; i <= 8; i++) {
            if (x - i >= 0) {
                if (map[y][x - i] > 0) {
                    // 隔山打牛
                    for (int j = 1; j <= 8 - i; j++) {
                        if (x - i - j >= 0) {
                            if (map[y][x - i - j] > 0 && map[y][x - i - j] < 17) {
                                nextPosition.add(new Position(x - i - j, y));
                                break;
                            }
                        }
                    }
                    break;
                }
                nextPosition.add(new Position(x - i, y));
            }
        }

        // 右
        for (int i = 1; i <= 8; i++) {
            if (x + i <= 8) {
                if (map[y][x + i] > 0) {
                    // 隔山打牛
                    for (int j = 1; j <= 8 - i; j++) {
                        if (x + i + j <= 8) {
                            if (map[y][x + i + j] > 0 && map[y][x + i + j] < 17) {
                                nextPosition.add(new Position(x + i + j, y));
                                break;
                            }
                        }
                    }
                    break;
                }
                nextPosition.add(new Position(x + i, y));
            }
        }
    }
}