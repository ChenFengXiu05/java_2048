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
        this.getContentPane().removeAll();
        initImage();
        this.getContentPane().repaint();
        initJFrame();
        initJMenuBar();
        this.setVisible(true);

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

        //将每一个选项下面的条目添加到选项当中去
        functionJmenu.add(replayItem);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJmenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    public void InitJFmeau(){

    }
    public void initImage() {

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

        //刷新界面
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
                System.out.println("无法移动！！！");
                System.out.println(code);
                return;
            }
            //判断游戏结束条件
            //如果方格都被填满且移动无法消除方块，游戏则结束
            if(!Cont.isContinue()) {
                System.out.println("！！游戏结束！！");
                this.getContentPane().removeAll();
                ImageIcon icon = new ImageIcon("D:\\JAVA_codes\\java_2048\\images\\victory.png");
                JLabel jLabel = new JLabel(icon);
                jLabel.setBounds(0, 400, 746, 181);
                this.add(jLabel);
                initImage();
                this.getContentPane().repaint();
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
