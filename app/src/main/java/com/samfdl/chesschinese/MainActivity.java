package com.samfdl.chesschinese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.samfdl.chesschinese.pojo.Position;
import com.samfdl.chesschinese.pojo.QiZi;
import com.samfdl.chesschinese.pojo.Zu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int chessManSize;

    private RelativeLayout chessBoard;

    // 棋子列表
    private ArrayList<QiZi> qiZiList = new ArrayList();

    // 下一步走子位置提示列表
    private ArrayList<View> nestViewList = new ArrayList();

    private int[][] map = new int[9][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chessBoard = findViewById(R.id.chess_board);

        chessManSize = (int) getResources().getDimension(R.dimen.layout_margin2);

        for (int i = 1; i < 6; i++) {
            QiZi qiZi = new Zu(this, i, new Position(2 * i - 2, 3), map);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chessManSize, chessManSize);
            params.setMargins(qiZi.position.x * chessManSize, qiZi.position.y * chessManSize, 0, 0);
            qiZi.setOnClickListener(this);
            chessBoard.addView(qiZi, params);

            qiZiList.add(qiZi);
            // 此位置已有棋子
            map[qiZi.position.x][qiZi.position.y] = 1;
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
                        // 地图更新
                        map[qiZi.position.x][qiZi.position.y] = 0;
                        qiZi.position = position;
                        map[qiZi.position.x][qiZi.position.y] = 1;

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