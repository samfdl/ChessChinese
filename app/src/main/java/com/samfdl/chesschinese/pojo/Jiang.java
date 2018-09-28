package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 黑方将
 * 将、帅，一军之主也。
 * 故不得出中军帐，九宫格也。
 */
public class Jiang extends QiZi {
    public Jiang(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        image = R.mipmap.black_king;
        setImageResource(image);
    }

    // 四个方向
    private int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 将的下一步走子位置
    @Override
    public void setNextPosition(int x, int y) {
        nextPosition.clear();

        for (int i = 0; i < 4; i++) {
            if (y + next[i][0] >= 0 && y + next[i][0] <= 2
                    && x + next[i][1] >= 3 && x + next[i][1] <= 5
                    && map[y + next[i][0]][x + next[i][1]] < 17) {
                nextPosition.add(new Position(x + next[i][1], y + next[i][0]));
            }
        }
    }
}