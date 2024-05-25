package Game;

import interFace.IndexJFrame;
import tools.SoundPool;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameInterFace extends JFrame implements KeyListener {
    private int score = 2;
    private boolean stu = true;

    private container Cont = new container();

    //音频部分
    private Clip startAudioClip;
    private Clip actionAudioClip;
    public boolean isStu() {
        return stu;
    }

    public void setStu(boolean stu) {
        this.stu = stu;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public container getCont() {
        return Cont;
    }

    public void setCont(container cont) {
        Cont = cont;
    }

    public GameInterFace(){
        InitGame();
        SoundPool sound = new SoundPool("D:\\JAVA_codes\\java_2048\\music\\45秒背景音乐_耳聆网_[声音ID：39516].wav");
        sound.start();
    }

    private void InitGame() {
        Cont.RandomGenerate();
        Cont.RandomGenerate();
        initImage();
        initJFrame();
        initJMenuBar();
        this.setVisible(true);
        System.out.println("初始完毕");
    }

    public void initJFrame(){
//        设置页面的宽高
        this.setSize(700, 900);
//         设置页面的标题
        this.setTitle("java新版2048");
//        设置页面置顶
        this.setAlwaysOnTop(true);
//        设置页面居中
        this.setLocationRelativeTo(null);
//        设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置JFrame中的布局对象
//        取消默认的居中放置
        this.setLayout(null);
        //给整个页面添加键盘监听事件
        this.addKeyListener(this);
    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项的对象
        JMenu functionJmenu = new JMenu("说明");
        //创建选项下面的条目对象

        JMenuItem replayItem = new JMenuItem("游戏说明介绍");
        replayItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了游戏说明介绍按钮");
                JDialog inview = new JDialog();
                inview.setSize(550, 450);
//        设置页面置顶
                inview.setAlwaysOnTop(true);
//        设置页面居中
                inview.setLocationRelativeTo(null);
                //设置JFrame中的布局对象
//        取消默认的居中放置
                inview.setLayout(null);
                JLabel Inview = new JLabel(new ImageIcon("images/img.png"));
                Inview.setBounds(0,0,529,412);
                inview.add(Inview);
                inview.setVisible(true);
            }
        });

        //将每一个选项下面的条目添加到选项当中去
        functionJmenu.add(replayItem);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJmenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    public void initImage() {
        //移除所有元素
        this.getContentPane().removeAll();
        //新建图片和容器变量
        ImageIcon icon;
        JLabel jLabel;
        //设置偏移量
        int y_offset = 20;
        int x_offset = 20;
        //初始化界面
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++) {
                if (i == 0 && j == 0 || i == 4 && j == 0 || i == 0 && j == 4 || i == 4 && j == 4)
                    continue;
                icon = new ImageIcon(Cont.getBlocks()[i][j].getImagePath());
                //将图片赋值JLabel对象(管理容器)
                jLabel = new JLabel(icon);
                //根据block内部的x_index和y_index设置出现位置的的坐标
                int x = 114 * Cont.getBlocks()[i][j].getX_index();
                int y = 114 * Cont.getBlocks()[i][j].getY_index();
                //指定图片位置
                jLabel.setBounds(x + 39 + x_offset, y + 200 + y_offset, 114, 114);
                //将容器添加为组件
                this.add(jLabel);
            }
        }
        //添加计步器
        String st = "步数：" + Cont.getStep();
        JLabel step = new JLabel(st);
        step.setBounds(40,150,120,30);
        step.setFont(new Font("songti",Font.BOLD,20));
        this.getContentPane().add(step);

        //添加背景图片
        ImageIcon bg = new ImageIcon("images/Gamebg.png");
        JLabel background = new JLabel(bg);
        background.setBounds(x_offset, y_offset, 628, 798);
        this.getContentPane().add(background);


        //刷新、重新绘制整个界面
        this.getContentPane().repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    public void finishGame(){
        JDialog finishgame = new JDialog();
        //对关闭添加事件捕获
        //WindowAdapter
        //点击关闭跳转至主界面
        finishgame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("返回主页面");
                //将游戏页面关闭
                GameInterFace.super.setVisible(false);
                //显示主菜单页面
                new IndexJFrame();
            }
        });
        // 设置页面的宽高
        finishgame.setSize(602, 600);
//        设置页面置顶
        finishgame.setAlwaysOnTop(true);
//        设置页面居中
        finishgame.setLocationRelativeTo(null);
        //设置JFrame中的布局对象
//        取消默认的居中放置
        finishgame.setLayout(null);
        //胜利图片
        JLabel jLabel = new JLabel(new ImageIcon("images\\v.png"));
        jLabel.setBounds(0, 0, 602, 600);
        //score图片
        JLabel scorebg = new JLabel(new ImageIcon("images/score.png"));
        scorebg.setBounds(120,260,367,101);
        //按钮-继续新游戏
        JButton continueGame = new JButton("继续新游戏");
        continueGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //关闭游戏结束界面
                finishgame.setVisible(false);
                System.out.println("游戏界面关闭，开始新一轮游戏");
                //清除页面的组件
                GameInterFace.super.getContentPane().removeAll();
                //将上次的页面不显示
                GameInterFace.super.setVisible(false);
                //将容器初始化，方块初始化
                Cont.InitCont();
                //取消对不用页面的监听
                GameInterFace.super.removeKeyListener(GameInterFace.this);
                //重新初始化一个新的Game界面
                InitGame();
            }
        });
        //继续新游戏按钮
        continueGame.setFont(new Font("songti", Font.BOLD, 16));
        continueGame.setBounds(240,380,120,40);
        continueGame.setBackground(new Color(Color.orange.getRGB()));
        //文本说明-分数-关闭-历史记录-游戏获胜
        String s = "您的分数是：" + Cont.getNowScore();
        JLabel Score = new JLabel(s);
        Score.setBounds(230,270,140,20);
        Score.setFont(new Font("songti", Font.BOLD, 16));
        JLabel exegesis = new JLabel("点击关闭即可回到主菜单");
        exegesis.setBounds(230,450,140,34);
        JLabel record = new JLabel("恭喜您！打破了您的最高记录");
        record.setFont(new Font("songti", Font.BOLD, 20));
        record.setBounds(160,300,280,20);
        JLabel victory = new JLabel("恭喜您，游戏通关啦！！！");
        victory.setFont(new Font("songti", Font.BOLD, 22));
        victory.setBounds(160,330,280,20);

        //添加组件
        if (Cont.getNowScore()==2048){
                    System.out.println("你已经通关了！！！");
                    finishgame.add(victory);
                } else if (Cont.getNowScore()>2) {
                    System.out.println("你打破了你的记录！！！");
                    finishgame.add(record);
                }else {
                    System.out.println("哥们，怎么比上次还捞了？？？");
                }
        finishgame.add(Score);
        finishgame.add(scorebg);
        finishgame.add(exegesis);
        finishgame.add(jLabel);
        finishgame.add(continueGame);

        System.out.println(Cont.getNowScore() + "----------------------------");

        //设置页面不可随便关闭
        finishgame.setModal(true);
        //显示界面
        finishgame.setVisible(true);
    }

    //重写键盘监听方法实现对应的逻辑
    @Override
    public void keyReleased(KeyEvent e) {
        if(Cont.getNowScore()>=2048){
            finishGame();
        }
        int code = e.getKeyCode();
        //判断是否为移动方向键
        if (!(code == 81 || code == 88 || code == 69 || code == 90 || code == 68 || code == 65 || code == 87 || code == 83)) {
            System.out.println("请输入正确的指令");
        } else{
//            判断是否可以继续往某个方向移动
            if(!Cont.isSlide(code) && !Cont.isMerge(code)) {
                System.out.println("此方向无法移动！！！");
                System.out.println(code);
                if(!Cont.isContinue()) {
                    System.out.println("游戏结束 Game over！！！");
                    finishGame();
                }
                return;
            }
            //判断游戏结束条件
            //如果方格都被填满且移动无法消除方块，游戏则结束

            Cont.setStep(Cont.getStep()+1);
            Cont.slide(code);
            Cont.merge(code);
            //重新initNullIndex，以便实现下次随机加载新方块
            Cont.initNullIndex();
            //随机从空白位置加载新方块
            Cont.RandomGenerate();
            //每次移动过后需要重新加载Image
            this.getContentPane().removeAll();
            initImage();
            this.getContentPane().repaint();
        }
    }
}
