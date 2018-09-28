package com.samfdl.chesschinese.pojo;

import android.content.Context;

import com.samfdl.chesschinese.R;

/**
 * Created by samfdl on 2018/9/28.
 * 红方士
 * 士、仕，贴身侍卫也。
 * 不离主将左右，防刺客。
 * 主将死，三军皆斩。
 * 故亦不敢出中军帐，九宫格也。
 * 且斜向巡逻，穿梭不离主将左右。
 */
public class RedShi extends QiZi {
    public RedShi(Context context, int id, Position position, int[][] map) {
        super(context, id, position, map);
        image = R.mipmap.red_shi;
        setImageResource(image);
        type = SHI;
        isRed = true;
    }

    // 四个方向
    private int[][] next = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    // 士的下一步走子位置
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