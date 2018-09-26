package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/26.
 * 黑方卒
 * 步兵，民兵也。
 * 因装备训练成本低，故移动缓慢，且只能向前冲，不得后退。
 * 冲入敌方阵营后方可横行。
 * 故有五支，开局压住阵脚，以张军威。
 */
public class Zu extends QiZi {
    public Zu(Context context, int id, Position position) {
        super(context, id, position);
        image = R.mipmap.black_army;
        setImageResource(image);
        type = ZU;
        isRed = false;
        setNextPosition(position.x, position.y);
    }

    public void setPosition(int x, int y) {
        position = new Position(x, y);
        setNextPosition(x, y);
    }

    // 卒的下一步走子位置
    public void setNextPosition(int x, int y) {
        nextPosition.clear();
        if (y + 1 <= 9) {
            nextPosition.add(new Position(x, y + 1));
        }
        if (isCrossed) {
            if (x - 1 >= 0) {
                nextPosition.add(new Position(x - 1, y));
            }
            if (x + 1 <= 8) {
                nextPosition.add(new Position(x + 1, y));
            }
        }
    }
}