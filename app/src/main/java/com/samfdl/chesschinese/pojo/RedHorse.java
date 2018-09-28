package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 红方马
 * 马、馬、㐷，骑兵也。
 * 斜向冲杀，威猛异常之奇兵。
 * 但脚力有限，走不远。
 */
public class RedHorse extends QiZi {
    public RedHorse(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        image = R.mipmap.red_cavalry;
        setImageResource(image);
    }

    // 八个方向
    private int[][] next = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};
    // 四个方向别马腿
    private int[][] bieNext = {{-1, 0}, {-1, 0}, {1, 0}, {1, 0}, {0, -1}, {0, -1}, {0, 1}, {0, 1}};

    // 马的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        for (int i = 0; i < 8; i++) {
            if (y + next[i][0] >= 0 && y + next[i][0] <= 9
                    && x + next[i][1] >= 0 && x + next[i][1] <= 8
                    // 无棋子或有黑方棋子
                    && (map[y + next[i][0]][x + next[i][1]] > 16 || map[y + next[i][0]][x + next[i][1]] == 0)
                    // 无别马腿
                    && map[y + bieNext[i][0]][x + bieNext[i][1]] == 0) {
                nextPosition.add(new Position(x + next[i][1], y + next[i][0]));
            }
        }
    }
}