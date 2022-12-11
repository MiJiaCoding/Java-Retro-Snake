package com.kuang.snake;

import javax.swing.*;
import java.net.URL;

//数据中心
public class Data {

    //绝对对路径 / 相当于当前的项目



    public static URL headerURL = Data.class.getResource("statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    public static URL upURL = Data.class.getResource("statics/up.png");
    public static URL downURL = Data.class.getResource("statics/down.png");
    public static URL leftURL = Data.class.getResource("statics/left.png");
    public static URL rightURL = Data.class.getResource("statics/right.png");

    public static ImageIcon up=new ImageIcon(upURL);
    public static ImageIcon down=new ImageIcon(downURL);
    public static ImageIcon left=new ImageIcon(leftURL);
    public static ImageIcon right=new ImageIcon(rightURL);

    public static URL bodyURL = Data.class.getResource("statics/body.png");
    public static ImageIcon body=new ImageIcon(bodyURL);

    public static URL foodURL = Data.class.getResource("statics/food.png");
    public static ImageIcon food=new ImageIcon(foodURL);

    public static URL backgroundURL=Data.class.getResource("statics/background.png");
    public static ImageIcon background=new ImageIcon(backgroundURL);

    public static URL fenlongupURL=Data.class.getResource("statics/fenlongup.png");
    public static ImageIcon fenlongup=new ImageIcon(fenlongupURL);
    public static URL fenlongURL=Data.class.getResource("statics/fenlong.png");
    public static ImageIcon fenlong=new ImageIcon(fenlongURL);
    public static URL fenlongleftURL=Data.class.getResource("statics/fenlongleft.png");
    public static ImageIcon fenlongleft=new ImageIcon(fenlongleftURL);
    public static URL fenlongrigthURL=Data.class.getResource("statics/fenlongright.png");
    public static ImageIcon fenlongright=new ImageIcon(fenlongrigthURL);
    public static URL fenbodyURL=Data.class.getResource("statics/fenbody.png");
    public static ImageIcon fenbody=new ImageIcon(fenbodyURL);

    //yingtao mangguo
    public static URL yingtaoURL=Data.class.getResource("statics/yingtao.png");
    public static ImageIcon yingtao=new ImageIcon(yingtaoURL);
    public static URL mangguoURL=Data.class.getResource("statics/mangguo.png");
    public static ImageIcon mangguo=new ImageIcon(mangguoURL);




}