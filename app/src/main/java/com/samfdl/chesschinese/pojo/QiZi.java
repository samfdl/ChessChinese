package com.samfdl.chesschinese.pojo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by samfdl on 2018/9/26.
 * 棋子
 */
@SuppressLint("AppCompatCustomView")
public abstract class QiZi extends ImageView {
    // ID
    public int id;
    // 棋子图片
    public int image;
    // 位置
    public Position position;
    // 下一步走子位置提示列表
    public ArrayList<Position> nextPosition = new ArrayList();
    // 是否已被选中
    public boolean isSelected = false;
    // 可走位置地图
    public int[][] map;

    public QiZi(Context context, int id, Position position, int[][] map) {
        super(context);
        this.id = id;
        this.position = position;
        this.map = map;
    }

    // 棋子的下一步走子位置
    public abstract void setNextPosition(int x, int y);
}