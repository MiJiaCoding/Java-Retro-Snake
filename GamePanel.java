package com.kuang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//游戏界面应该放在面板上面
public class GamePanel extends JPanel implements KeyListener, ActionListener {


    //定义蛇的数据结构
    int length;//蛇的长度
    int[] snakeX=new int[600];//蛇的X坐标 25*25
    int[] snakeY=new int[500];//蛇的Y坐标 25*25
    String fx;//方向

    //food的坐标
    int foodx,foody;
    int foodx2,foody2;
    Random random=new Random();
    //yingtao mangguo 坐标
    int ytx,yty;
    int mgx,mgy;

    Random randomyt=new Random();
    Random randommg=new Random();

    //成绩 积分系统
    int score;

    //变色对应的积分数组
    int[] change=new int[20];

    //游戏当前的状态:开始,停止
    boolean isStart=false;//默认停止

    boolean isFail=false;//游戏失败状态

    //定时器 以ms为为单位 100ms=1s
    Timer timer=new Timer(100,this);//100毫秒刷新一次


    //构造方法
    public GamePanel(){
        init();
        //获得焦点和键盘事件
        this.setFocusable(true);//获得焦点事件
        this.addKeyListener(this);//这里写this是因为我自己implement KeyListener ,不然就应该new一个监听器
        timer.start();//游戏一开始定时器开启！
    }

    //初始化方法
    public void init(){
        length=3;
        snakeX[0]=100;snakeY[0]=100;//脑袋坐标
        snakeX[1]=75;snakeY[1]=100;//第一个身体的坐标
        snakeX[2]=50;snakeY[2]=100;//第二个身体的坐标
        fx="R"; //初始方向向右

//      nextInt()该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
        foodx=25+25*random.nextInt(34); // 850/25=34
        foody=75+25*random.nextInt(24); // 600/25=24

        foodx2=25+25*random.nextInt(34); // 850/25=34
        foody2=75+25*random.nextInt(24); // 600/25=24


        ytx=25+25*random.nextInt(34); // 850/25=34
        yty=75+25*random.nextInt(24); // 600/25=24

        mgx=25+25*random.nextInt(34); // 850/25=34
        mgy=75+25*random.nextInt(24); // 600/25=24
        //初始化积分
        score=0;

        change[0]=10;
        for (int i=1;i<10;i++){
            change[i]=change[i-1]+20;
        }

        timer.setDelay(100);//速度重置



    }


    //绘制面板，游戏中的所有东西都是用这个画笔来画
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.gray);
        Data.header.paintIcon(this,g,25,2);//头部栏
        Data.background.paintIcon(this,g,25,75);
//        g.fillRect(25,75,850,600);

        if(score==1)



        //画积分
        g.setColor(Color.cyan);
        g.setFont(new Font("微软雅黑",Font.BOLD,20));//设置字体
        g.drawString("长度 "+length,750,35);
        g.drawString("分数 "+score,750,55);




        //先画食物，再画身体，这样吃掉食物的时候身体头部是覆盖在食物上面的，更美观一点
        //画食物
        Data.food.paintIcon(this,g,foodx,foody);
        Data.food.paintIcon(this,g,foodx2,foody2);

        Data.mangguo.paintIcon(this,g,mgx,mgy);
        Data.yingtao.paintIcon(this,g,ytx,yty);

        //画小蛇
        if(fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for (int i=1;i<length;i++)
        {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }





        //游戏状态
        if(isStart==false){
            g.setColor(Color.CYAN);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("按下空格化身为蛇蛇",300,300);
        }
        if (isFail){
            g.setColor(Color.cyan);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("世界线收束失效！",300,300);
            g.drawString("是否进入下一世界线？(space)",220,350);
        }

        switch(score)
        {
            case -20:
                g.setColor(Color.cyan);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("你还贪吃！！",250,30);
                g.drawString("只剩一个头了吧=、=。",250,65);

                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }

                break;




            case -10:
                g.setColor(Color.cyan);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("你贪吃了什么不该垂涎的东西！",250,30);
                g.drawString("你快只剩一个头了。",250,65);

                break;


            case 10:
                g.setColor(Color.cyan);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("暗影龙",250,30);
                g.drawString("你现在只是一个灵体，飘荡在虚无中。",250,65);



                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }

                break;

            case 20:
                g.setColor(Color.green);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("青绿龙",250,30);
                g.drawString("你还有一个更常用的名字，毛毛虫。",250,65);



                break;
            case 30:
                timer.setDelay(90);
                g.setColor(Color.CYAN);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("你走位更蛇皮了！",300,400);

                g.setColor(Color.CYAN);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("唤刺龙",250,30);
                g.drawString("你只会在水池里反扑拍打着水花。",250,65);



                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }
                break;
            case 40:
                g.setColor(Color.red);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("加仓炎龙  你的行动是以一个",250,30);
                g.drawString("球状物体命中一张卡牌作为你的开始。",250,65);
                break;
            case 50:
                timer.setDelay(80);
                g.setColor(Color.CYAN);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("你走位更蛇皮了！",300,400);

                g.setColor(Color.green);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("剧毒龙   你的体内蕴含着大量",250,30);
                g.drawString("的毒素，准确的说，你是一条毒蛇。",250,65);

                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }
                break;
            case 60:
                timer.setDelay(70);
                g.setColor(Color.CYAN);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("你走位更蛇皮了！",300,400);


                g.setColor(Color.MAGENTA);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("韩金龙   你的走位",250,30);
                g.drawString("稳中带皮，似上飞上，四行不慌。",250,65);

                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }
                break;
            case 70:
                g.setColor(Color.BLUE);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("符文龙",250,30);
                g.drawString("你开始注意到自己身上奇异的颜色了。",250,65);

                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }
                break;
            case 80:
                g.setColor(Color.PINK);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("彩虹龙",250,30);
                g.drawString("你身上的颜色越发艳丽。",250,65);

                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }
                break;
            case 90:
                g.setColor(Color.cyan);
                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
                g.drawString("天空龙   你省略了漫长的进化",250,30);
                g.drawString("步骤，你现在在天空中自由的翱翔。",250,65);

                if(fx.equals("R")){
                    Data.fenlongright.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("L")){
                    Data.fenlongleft.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("U")){
                    Data.fenlongup.paintIcon(this,g,snakeX[0],snakeY[0]);
                }else if(fx.equals("D")){
                    Data.fenlong.paintIcon(this,g,snakeX[0],snakeY[0]);
                }
                for (int i=1;i<length;i++)
                {
                    Data.fenbody.paintIcon(this,g,snakeX[i],snakeY[i]);
                }
//                Data.tiankonglong.paintIcon(this,g,25,75);
                break;

//        for(int i=0;i<10;i++){
//            if(score==change[i]){
//                g.setColor(Color.cyan);
//                g.setFont(new Font("微软雅黑",Font.BOLD,30));//设置字体
//                g.drawString("暗影龙",250,5);
//                g.drawString("你现在只是一个灵体，飘荡在虚无中。",250,40);
//            }
        }


    }

    //键盘监听事件
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//获得键盘的按键是哪一个
        if(keyCode==KeyEvent.VK_SPACE){
            //如果按下的是空格键
            if (isFail){
                //重新开始
                //初始化
                isFail=false;
                init();
            }else {
                isStart = !isStart; //取反
            }
            repaint();//重新绘制
        }

        //小蛇移动
        if (keyCode==KeyEvent.VK_UP){
            fx="U";
        }else if (keyCode==KeyEvent.VK_DOWN){
            fx="D";
        }else if (keyCode==KeyEvent.VK_LEFT){
            fx="L";
        }else if (keyCode==KeyEvent.VK_RIGHT){
            fx="R";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    //事件监听---需要通过固定
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart &&isFail==false){
            //吃食物
            if (snakeX[0]==foodx & snakeY[0]==foody||snakeX[0]==foodx2&snakeY[0]==foody2){
                //长度+1
                length++;
                //分数加10
                score=score+10;

                //再次随机食物
                foodx=25+25*random.nextInt(34); // 850/25=34
                foody=75+25*random.nextInt(24); // 600/25=24
                foodx2=25+25*random.nextInt(34); // 850/25=34
                foody2=75+25*random.nextInt(24); // 600/25=24

                ytx=25+25*random.nextInt(34); // 850/25=34
                yty=75+25*random.nextInt(24); // 600/25=24

                mgx=25+25*random.nextInt(34); // 850/25=34
                mgy=75+25*random.nextInt(24); // 600/25=24
            }else if(snakeX[0]==mgx&snakeY[0]==mgy){
                length--;
                score=score-10;
                //再次随机食物
                foodx=25+25*random.nextInt(34); // 850/25=34
                foody=75+25*random.nextInt(24); // 600/25=24
                foodx2=25+25*random.nextInt(34); // 850/25=34
                foody2=75+25*random.nextInt(24); // 600/25=24

                ytx=25+25*random.nextInt(34); // 850/25=34
                yty=75+25*random.nextInt(24); // 600/25=24

                mgx=25+25*random.nextInt(34); // 850/25=34
                mgy=75+25*random.nextInt(24); // 600/25=24

            }else if(snakeX[0]==ytx&snakeY[0]==yty){
                isFail=true;
            }


            //如果游戏是开始状态就让小蛇动起来！

            //移动
            for (int i=length-1;i>0;i--){
                //后面一节移动到前面一节的位置！！！
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }
//            snakeX[0]=snakeX[0]+25;

            //走向,边界
            if(fx.equals("R")){
                snakeX[0]=snakeX[0]+25;
                if(snakeX[0]>850){
                    snakeX[0]=25;//碰到边界
                }
            }else if(fx.equals("L")){
                snakeX[0]=snakeX[0]-25;
                if(snakeX[0]<25){
                    snakeX[0]=850;//碰到边界
                }
            }else if(fx.equals("U")){
                snakeY[0]=snakeY[0]-25;
                if(snakeY[0]<75){
                    snakeY[0]=650;//碰到边界
                }
            }else if(fx.equals("D")){
                snakeY[0]=snakeY[0]+25;
                if(snakeY[0]>650){
                    snakeY[0]=75;//碰到边界
                }
            }

            //失败判定条件,碰到自己就算失败
            for (int i =1;i<length;i++){
                if (snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i])
                    isFail=true;

            }


            repaint();//重画页面
        }
        timer.start();//游戏一开始定时器开启！
    }
}
