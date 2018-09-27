package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/26.
 * 红兵
 * 步兵，民兵也。
 * 因装备训练成本低，故移动缓慢，且只能向前冲，不得后退。
 * 冲入敌方阵营后方可横行。
 * 故有五支，开局压住阵脚，以张军威。
 */
public class Bing extends QiZi {
    public Bing(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        image = R.mipmap.red_army;
        setImageResource(image);
        type = ZU;
        isRed = true;
    }

    // 兵的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();
        if (y - 1 >= 0 && (map[y - 1][x] > 16 || map[y - 1][x] == 0)) {
            nextPosition.add(new Position(x, y - 1));
        }
        if (y < 5) {
            if (x - 1 >= 0 && (map[y][x - 1] > 16 || map[y][x - 1] == 0)) {
                nextPosition.add(new Position(x - 1, y));
            }
            if (x + 1 <= 8 && (map[y][x + 1] > 16 || map[y][x + 1] == 0)) {
                nextPosition.add(new Position(x + 1, y));
            }
        }
    }
}