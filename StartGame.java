package com.kuang.snake;

import javax.swing.*;

//游戏的主启动类
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(10,10,915,750);
        frame.setResizable(false);//设置窗口大小不可改变
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //游戏界面应该放在面板上面
        frame.add(new GamePanel());

    }
}
