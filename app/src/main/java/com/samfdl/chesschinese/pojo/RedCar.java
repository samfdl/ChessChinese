package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 红方车
 * 车、車、俥，战车也。
 * 横冲直撞，杀伤力极远。
 * 但因太快，无法拐弯。
 */
public class RedCar extends QiZi {
    public RedCar(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        setForeground(context.getDrawable(R.mipmap.red_ju0));
    }

    // 车的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        // 上
        for (int i = 1; i <= 9; i++) {
            if (y - i >= 0) {
                if (map[y - i][x] > 0) {
                    if (map[y - i][x] > 16) {
                        nextPosition.add(new Position(x, y - i));
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
                    if (map[y + i][x] > 16) {
                        nextPosition.add(new Position(x, y + i));
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
                    if (map[y][x - i] > 16) {
                        nextPosition.add(new Position(x - i, y));
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
                    if (map[y][x + i] > 16) {
                        nextPosition.add(new Position(x + i, y));
                    }
                    break;
                }
                nextPosition.add(new Position(x + i, y));
            }
        }
    }
}