package interFace;

import Game.GameInterFace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IndexJFrame extends  JFrame {
    int width=600;
    int height=600;
    public IndexJFrame(){
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("新2048");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //添加背景图
        JLabel bg = new JLabel(new ImageIcon("images/Indexbg.png"));
        //添加文本信息
        JLabel begin = new JLabel("点击任意处开始游戏···");
        begin.setBounds(175,500,500,23);
        //设置begin样式
        begin.setFont(new Font("仿宋",Font.BOLD,20));
        begin.setForeground(Color.YELLOW);
        //设置鼠标监听
        bg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击开始游戏");
                //关闭页面
                IndexJFrame.super.setVisible(false);
                //取消鼠标监听
                IndexJFrame.super.removeMouseListener(this);
                //跳转游戏界面准备开始游戏
                new GameInterFace();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.add(begin);
        this.add(bg);

        this.setVisible(true);
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){
                    repaint();
                }

            }
        });

    }
}
