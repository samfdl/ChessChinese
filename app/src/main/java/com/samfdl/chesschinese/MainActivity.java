package com.samfdl.chesschinese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int chessManSize;
    private int nextChessManSize;

    private FrameLayout chessBoard;

    private ArrayList<ImageView> chessViewList = new ArrayList<ImageView>();
    private ArrayList<ChessMan> chessManList = new ArrayList<ChessMan>();

    private ImageView selectedChessView;
    private ArrayList<View> selectedChessNestViewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chessBoard = (FrameLayout) findViewById(R.id.chess_board);

        chessManSize = (int) getResources().getDimension(R.dimen.layout_margin2);
        nextChessManSize = (int) getResources().getDimension(R.dimen.next_chessman_size);

        for (int i = 1; i <= 32; i++) {
            ChessMan chessMan = new ChessMan(i);
            chessManList.add(chessMan);

            // 设置棋子图片
            ImageView chessView = new ImageView(this);
            chessView.setImageResource(chessMan.getImage());
            // 加载监听和标志
            chessView.setOnClickListener(this);
            chessView.setTag(chessMan);

            // 设置棋子大小、位置
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(chessManSize, chessManSize);
            params.setMargins(chessMan.getPosition().getX() * chessManSize, chessMan.getPosition().getY() * (chessManSize + 2), 0, 0);

            // 加载到棋盘
            chessBoard.addView(chessView, params);

            chessViewList.add(chessView);
        }
    }

    @Override
    public void onClick(View view) {
        ChessMan chessMan = (ChessMan) view.getTag();
        if (chessMan.isSelected() == false) {
            // 取消其他已选中棋子
            if (selectedChessView != null) {
                selectedChessView.setBackgroundColor(getResources().getColor(R.color.transparent));
                ((ChessMan) selectedChessView.getTag()).setSelected(false);
                selectedChessView = null;

                // 取消下一步的走子位置提示
                for (int i = selectedChessNestViewList.size() - 1; i >= 0; i--) {
                    View chessNextView = selectedChessNestViewList.get(i);
                    chessBoard.removeView(chessNextView);
                    selectedChessNestViewList.remove(chessNextView);
                }
            }

            // 选中的棋子背景色标红
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            chessMan.setSelected(true);
            selectedChessView = (ImageView) view;

            chessMan.setNextPosition();
            // 标出下一步的走子位置提示
            for (Position position : chessMan.getNextPosition()) {
                // 设置棋子图片
                View chessNextView = new View(this);
                chessNextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                // 加载监听和标志
                chessNextView.setOnClickListener(this);

                // 设置棋子大小、位置
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(nextChessManSize, nextChessManSize);
                params.setMargins((int) ((position.getX() + 0.4) * chessManSize), (int) ((position.getY() + 0.4) * (chessManSize + 2)), 0, 0);

                // 加载到棋盘
                chessBoard.addView(chessNextView, params);
                selectedChessNestViewList.add(chessNextView);
            }
        } else {
            // 取消已选中棋子
            view.setBackgroundColor(getResources().getColor(R.color.transparent));
            chessMan.setSelected(false);
            selectedChessView = null;

            // 取消下一步的走子位置提示
            for (int i = selectedChessNestViewList.size() - 1; i >= 0; i--) {
                View chessNextView = selectedChessNestViewList.get(i);
                chessBoard.removeView(chessNextView);
                selectedChessNestViewList.remove(chessNextView);
            }
        }
    }
}