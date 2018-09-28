package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 黑方马
 * 马、馬、㐷，骑兵也。
 * 斜向冲杀，威猛异常之奇兵。
 * 但脚力有限，走不远。
 */
public class Horse extends QiZi {
    public Horse(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        image = R.mipmap.black_cavalry;
        setImageResource(image);
        type = HORSE;
        isRed = false;
    }

    private int[][] next = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};
    private int[][] bieNext = {{-1, 0}, {-1, 0}, {1, 0}, {1, 0}, {0, -1}, {0, -1}, {0, 1}, {0, 1}};

    // 马的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        for (int i = 0; i < 8; i++) {
            if (y + next[i][0] >= 0 && y + next[i][0] <= 9
                    && x + next[i][1] >= 0 && x + next[i][1] <= 8
                    && map[y + next[i][0]][x + next[i][1]] < 17
                    // 无别马腿
                    && map[y + bieNext[i][0]][x + bieNext[i][1]] == 0) {
                nextPosition.add(new Position(x + next[i][1], y + next[i][0]));
            }
        }
        // 上左
//        if (y - 2 >= 0 && x - 1 >= 0 && map[y - 2][x - 1] < 17 && map[y - 1][x] == 0) {
//            nextPosition.add(new Position(x - 1, y - 2));
//        }

//        // 上右
//        if (y - 2 >= 0 && x + 1 <= 8 && map[y - 2][x + 1] < 17 && map[y - 1][x] == 0) {
//            nextPosition.add(new Position(x + 1, y - 2));
//        }
//
//        // 下左
//        if (y + 2 <= 9 && x - 1 >= 0 && map[y + 2][x - 1] < 17 && map[y + 1][x] == 0) {
//            nextPosition.add(new Position(x - 1, y + 2));
//        }
//
//        // 下右
//        if (y + 2 <= 9 && x + 1 <= 8 && map[y + 2][x + 1] < 17 && map[y + 1][x] == 0) {
//            nextPosition.add(new Position(x + 1, y + 2));
//        }
//
//        // 左上
//        if (y - 1 >= 0 && x - 2 >= 0 && map[y - 1][x - 2] < 17 && map[y][x - 1] == 0) {
//            nextPosition.add(new Position(x - 2, y - 1));
//        }
//
//        // 左下
//        if (y + 1 <= 9 && x - 2 >= 0 && map[y + 1][x - 2] < 17 && map[y][x - 1] == 0) {
//            nextPosition.add(new Position(x - 2, y + 1));
//        }
//
//        // 右上
//        if (y - 1 >= 0 && x + 2 <= 8 && map[y - 1][x + 2] < 17 && map[y][x + 1] == 0) {
//            nextPosition.add(new Position(x + 2, y - 1));
//        }
//
//        // 右下
//        if (y + 1 <= 9 && x + 2 <= 8 && map[y + 1][x + 2] < 17 && map[y][x + 1] == 0) {
//            nextPosition.add(new Position(x + 2, y + 1));
//        }
    }
}