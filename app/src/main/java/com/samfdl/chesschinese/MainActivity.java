package com.samfdl.chesschinese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.samfdl.chesschinese.pojo.Bing;
import com.samfdl.chesschinese.pojo.Pao;
import com.samfdl.chesschinese.pojo.Position;
import com.samfdl.chesschinese.pojo.QiZi;
import com.samfdl.chesschinese.pojo.Zu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int chessManSize;

    private RelativeLayout chessBoard;

    // 棋子列表
    private ArrayList<QiZi> qiZiList = new ArrayList();
    // 棋子map
    private Map<Integer, QiZi> qiZiMap = new HashMap();

    // 下一步走子位置提示列表
    private ArrayList<View> nestViewList = new ArrayList();

    private int[][] map = new int[][]{ // 棋盘 10行 9列 ， 初始各位置数字
            {24, 22, 20, 18, 17, 19, 21, 23, 25},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 26, 0, 0, 0, 0, 0, 27, 0},
            {28, 0, 29, 0, 30, 0, 31, 0, 32},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {12, 0, 13, 0, 14, 0, 15, 0, 16},
            {0, 10, 0, 0, 0, 0, 0, 11, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {8, 6, 4, 2, 1, 3, 5, 7, 9}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chessBoard = findViewById(R.id.chess_board);

        chessManSize = (int) getResources().getDimension(R.dimen.layout_margin2);

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 9; i++) {
                if (map[j][i] > 0) {
                    QiZi qiZi;
                    switch (map[j][i]) {
                        case 1:
//                            break;
                        case 2:
                        case 3:

//                            break;
                        case 4:
                        case 5:

//                            break;
                        case 6:
                        case 7:

//                            break;
                        case 8:
                        case 9:

//                            break;
                        case 10:
                        case 11:

//                            break;
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            qiZi = new Bing(this, map[j][i], new Position(i, j), map);
                            break;
                        case 17:

//                            break;
                        case 18:
                        case 19:

//                            break;
                        case 20:
                        case 21:

//                            break;
                        case 22:
                        case 23:

//                            break;
                        case 24:
                        case 25:

//                            break;
                        case 26:
                        case 27:
                            qiZi = new Pao(this, map[j][i], new Position(i, j), map);
                            break;
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                            qiZi = new Zu(this, map[j][i], new Position(i, j), map);
                            break;
                        default:
                            qiZi = new Zu(this, map[j][i], new Position(i, j), map);
                            break;
                    }
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chessManSize, chessManSize);
                    params.setMargins(i * chessManSize, j * chessManSize, 0, 0);
                    qiZi.setOnClickListener(this);

                    chessBoard.addView(qiZi, params);
                    qiZiList.add(qiZi);
                    qiZiMap.put(map[j][i], qiZi);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        // 取消其他已选中棋子
        for (QiZi qiZi : qiZiList) {
            if (qiZi.equals(view)) {
                continue;
            }
            qiZi.isSelected = false;
            qiZi.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        // 取消下一步的走子位置提示
        for (View chessNextView : nestViewList) {
            chessBoard.removeView(chessNextView);
        }

        final QiZi qiZi = (QiZi) view;
        if (qiZi.isSelected) {
            // 取消已选中棋子
            qiZi.isSelected = false;
            qiZi.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            // 选中的棋子背景色标红
            qiZi.isSelected = true;
            qiZi.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            // 设置下一步走子位置
            qiZi.setNextPosition(qiZi.position.x, qiZi.position.y);

            // 标出下一步的走子位置提示
            for (final Position position : qiZi.nextPosition) {
                // 设置走子位置图片
                View chessNextView = new View(this);
                chessNextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                chessNextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 如果原来有对方棋子，则吃掉
                        if (map[position.y][position.x] > 0) {
                            QiZi qiZiDie = qiZiMap.get(map[position.y][position.x]);
                            qiZiMap.remove(qiZiDie);
                            qiZiList.remove(qiZiDie);
                            chessBoard.removeView(qiZiDie);
                        }

                        // 地图更新
                        map[qiZi.position.y][qiZi.position.x] = 0;
                        qiZi.position = position;
                        map[position.y][position.x] = qiZi.id;

                        // 取消已选中棋子
                        qiZi.isSelected = false;
                        qiZi.setBackgroundColor(getResources().getColor(R.color.transparent));

                        // 移动棋子到下一步的位置
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) qiZi.getLayoutParams();
                        params.setMargins(qiZi.position.x * chessManSize, qiZi.position.y * chessManSize, 0, 0);
                        qiZi.setLayoutParams(params);

                        // 取消下一步的走子位置提示
                        for (View chessNextView : nestViewList) {
                            chessBoard.removeView(chessNextView);
                        }
                    }
                });

                // 走子位置提示加载到棋盘
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chessManSize, chessManSize);
                params.setMargins(position.x * chessManSize, position.y * chessManSize, 0, 0);
                chessBoard.addView(chessNextView, params);

                nestViewList.add(chessNextView);
            }
        }
    }
}