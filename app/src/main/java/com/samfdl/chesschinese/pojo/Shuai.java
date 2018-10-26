package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 红方帅
 * 将、帅，一军之主也。
 * 故不得出中军帐，九宫格也。
 */
public class Shuai extends QiZi {
    public Shuai(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        setForeground(context.getDrawable(R.mipmap.red_shuai0));
    }

    // 四个方向
    private int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 帅的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        for (int i = 0; i < 4; i++) {
            if (y + next[i][0] >= 7 && y + next[i][0] <= 9
                    && x + next[i][1] >= 3 && x + next[i][1] <= 5
                    // 无棋子或有黑方棋子
                    && (map[y + next[i][0]][x + next[i][1]] > 16 || map[y + next[i][0]][x + next[i][1]] == 0)) {
                nextPosition.add(new Position(x + next[i][1], y + next[i][0]));
            }
        }
    }
}