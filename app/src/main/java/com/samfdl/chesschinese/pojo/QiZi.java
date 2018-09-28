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

    // 将、帅，一军之主也。故不得出中军帐，九宫格也。
    public final static int KING = 1;
    // 士、仕，贴身侍卫也。不离主将左右，防刺客。主将死，三军皆斩。故亦不敢出中军帐，九宫格也。且斜向巡逻，穿梭不离主将左右。
    public final static int SHI = 2;
    // 相、象，军师，文官，押运粮草也。故不得出己方阵营，且斜向照应己方粮草。
    public final static int XIANG = 3;
    // 马、馬、㐷，骑兵也。斜向冲杀，威猛异常之奇兵。但脚力有限，走不远。
    public final static int HORSE = 4;
    // 车、車、俥，战车也。横冲直撞，杀伤力极远。但因太快，无法拐弯。
    public final static int CAR = 5;
    // 炮、砲，投石车，弓箭手，弩机手也。因发射时易受攻击，故需前面有盾牌兵掩护。无掩护则不能开火。
    public final static int PAO = 6;
    // 卒、兵，步兵，民兵也。因装备训练成本低，故移动缓慢，且只能向前冲，不得后退。冲入敌方阵营后方可横行。故有五支，开局压住阵脚，以张军威。
    public final static int ZU = 7;

    public QiZi(Context context, int id, Position position, int[][] map) {
        super(context);
        this.id = id;
        this.position = position;
        this.map = map;
    }

    // 棋子的下一步走子位置
    public abstract void setNextPosition(int x, int y);
}