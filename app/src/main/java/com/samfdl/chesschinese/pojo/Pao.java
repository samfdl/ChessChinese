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
        type = PAO;
        isRed = false;
    }

    // 炮的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();
        for (int i = 0; i < 10; i++) {

        }
        if (y + 1 <= 9 && (map[x][y + 1] > 16 || map[x][y + 1] == 0)) {
            nextPosition.add(new Position(x, y + 1));
        }
        if (y > 4) {
            if (x - 1 >= 0 && (map[x - 1][y] > 16 || map[x - 1][y] == 0)) {
                nextPosition.add(new Position(x - 1, y));
            }
            if (x + 1 <= 8 && (map[x + 1][y] > 16 || map[x + 1][y] == 0)) {
                nextPosition.add(new Position(x + 1, y));
            }
        }
    }
}