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

    private ArrayList<QiZi> qiZiList = new ArrayList();

    private ArrayList<View> nestViewList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chessBoard = findViewById(R.id.chess_board);

        chessManSize = (int) getResources().getDimension(R.dimen.layout_margin2);

        QiZi qiZi = new Zu(this, 1, new Position(0, 3));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chessManSize, chessManSize);
        params.setMargins(qiZi.position.x * chessManSize, qiZi.position.y * chessManSize, 0, 0);
        qiZi.setOnClickListener(this);
        chessBoard.addView(qiZi, params);
    }

    @Override
    public void onClick(View view) {
        final QiZi qiZi = (QiZi) view;
        if (qiZi.isSelected) {
            // 取消已选中棋子
            qiZi.isSelected = false;
            qiZi.setBackgroundColor(getResources().getColor(R.color.transparent));

            // 取消下一步的走子位置提示
            for (View chessNextView : nestViewList) {
                chessBoard.removeView(chessNextView);
            }
        } else {
            // 选中的棋子背景色标红
            qiZi.isSelected = true;
            qiZi.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            // 标出下一步的走子位置提示
            for (final Position position : qiZi.nextPosition) {
                // 设置走子位置图片
                View chessNextView = new View(this);
                chessNextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                chessNextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        qiZi.setPosition(position.x, position.y);

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