package face;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.PasswordAuthentication;

public class GameInterFace extends JFrame implements KeyListener {
    private int score = 2;
    private container Cont = new container();

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
        Cont.RandomGenerate();
        Cont.RandomGenerate();
        initImage();
        initJFrame();
        initJMenuBar();
        this.setVisible(true);
        JButton btn = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击按钮");
            }
        });
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
        JMenu about = new JMenu("关于我们");
        //创建选项下面的条目对象

        JMenuItem replayItem = new JMenuItem("重新游戏");
        replayItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了重新游戏按钮");
            }
        });


        //将每一个选项下面的条目添加到选项当中去
        functionJmenu.add(replayItem);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJmenu);
        jMenuBar.add(about);

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
        //添加背景图片
        ImageIcon bg = new ImageIcon("D:/JAVA_codes/java_2048/images/bg1.png");
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


    public void VictoryJF(){
        JDialog victory = new JDialog();
        //        设置页面的宽高
        victory.setSize(602, 600);
//        设置页面置顶
        victory.setAlwaysOnTop(true);
//        设置页面居中
        victory.setLocationRelativeTo(null);
        //设置JFrame中的布局对象
//        取消默认的居中放置
        victory.setLayout(null);
        //胜利图片
        JLabel jLabel = new JLabel(new ImageIcon("D:\\JAVA_codes\\java_2048\\images\\v.png"));
        jLabel.setBounds(0, 0, 602, 600);
        //score图片
        JLabel scorebg = new JLabel(new ImageIcon("images/score.png"));
        scorebg.setBounds(120,260,367,101);
        //按钮-继续游戏
        JButton continueGame = new JButton("继续新游戏");
        Font newfont = new Font("songti", Font.BOLD, 16);
        continueGame.setFont(newfont);
        continueGame.setBounds(240,380,120,40);
        Color color = new Color(Color.orange.getRGB());
        continueGame.setBackground(color);
        //文本说明-分数-关闭-历史记录
        String s = "您的分数是：" + Cont.getNowScore();
        JLabel Score = new JLabel(s);
        Score.setBounds(230,270,140,20);
        Score.setFont(new Font("songti", Font.BOLD, 16));
        JLabel exegesis = new JLabel("点击关闭即可回到主菜单");
        exegesis.setBounds(230,450,140,34);
        JLabel record = new JLabel("恭喜您！打破了您的最高记录");
        record.setFont(new Font("songti", Font.BOLD, 20));
        record.setBounds(160,300,280,20);

        //添加组件
        victory.add(record);
        victory.add(Score);
        victory.add(scorebg);
        victory.add(exegesis);
        victory.add(jLabel);
        victory.add(continueGame);

        //设置页面不可随便关闭
        victory.setModal(true);
        //显示界面
        victory.setVisible(true);
    }
    //重写键盘监听方法实现对应的逻辑
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //判断是否为移动方向键
        if (!(code == 81 || code == 88 || code == 69 || code == 90 || code == 68 || code == 65 || code == 87 || code == 83)) {
            System.out.println("请输入正确的指令");
        } else{
//            判断是否可以继续往某个方向移动
            if(!Cont.isSlide(code) && !Cont.isMerge(code)) {
                System.out.println("------此方向无法移动！！！");
                System.out.println(code);
                return;
            }
            //判断游戏结束条件
            //如果方格都被填满且移动无法消除方块，游戏则结束
            if(!Cont.isContinue()) {
                System.out.println("------游戏结束    Game over！！！");
                VictoryJF();
                return;
            }
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
