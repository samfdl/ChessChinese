package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 红方相
 * 相、象，军师，文官，押运粮草也。
 * 故不得出己方阵营，且斜向照应己方粮草。
 */
public class RedXiang extends QiZi {
    public RedXiang(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        setForeground(context.getDrawable(R.mipmap.red_xiang0));
    }

    // 四个方向
    private int[][] next = {{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};
    // 四个方向塞象眼
    private int[][] bieNext = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    // 象的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        for (int i = 0; i < 4; i++) {
            if (y + next[i][0] >= 5 && y + next[i][0] <= 9
                    && x + next[i][1] >= 0 && x + next[i][1] <= 8
                    // 无棋子或有黑方棋子
                    && (map[y + next[i][0]][x + next[i][1]] > 16 || map[y + next[i][0]][x + next[i][1]] == 0)
                    // 无塞象眼
                    && map[y + bieNext[i][0]][x + bieNext[i][1]] == 0) {
                nextPosition.add(new Position(x + next[i][1], y + next[i][0]));
            }
        }
    }
}