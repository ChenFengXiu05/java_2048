package interFace;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import user.user;
public class RegisterJFrame extends JFrame implements MouseListener {

    ArrayList<user> users;
    //提升三个输入框的变量的作用范围，让这三个变量可以在本类中所有方法里面可以使用。
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField rePassword = new JTextField();

    //提升两个按钮变量的作用范围，让这两个变量可以在本类中所有方法里面可以使用。
    JButton submit = new JButton();
    JButton reset = new JButton();

    JButton Return=new JButton();

    //JDialog jDialog = new JDialog();
    public RegisterJFrame(ArrayList<user> users){
        this.users=users;
        initJFrame();
        initview();
        this.setVisible(true);
    }

    public void initview() {//添加注册用户名的文本
        JLabel usernameText = new JLabel(new ImageIcon("images/register/注册用户名.png"));
        usernameText.setBounds(85, 158, 80, 20);

        //添加注册用户名的输入框
        username.setBounds(195, 158, 200, 30);

        //添加注册密码的文本
        JLabel passwordText = new JLabel(new ImageIcon("images/register/注册密码.png"));
        passwordText.setBounds(97, 210, 70, 20);

        //添加密码输入框
        password.setBounds(195, 210, 200, 30);

        //添加再次输入密码的文本
        JLabel rePasswordText = new JLabel(new ImageIcon("images/register/再次输入密码.png"));
        rePasswordText.setBounds(64, 260, 95, 20);

        //添加再次输入密码的输入框
        rePassword.setBounds(195, 260, 200, 30);

        //注册的按钮
        submit.setIcon(new ImageIcon("images/register/注册按钮.png"));
        submit.setBounds(123, 310, 128, 47);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.addMouseListener(this);


        reset.setIcon(new ImageIcon("images/register/重置按钮.png"));
        reset.setBounds(256, 310, 128, 47);
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.addMouseListener(this);

        Return.setBounds(10, 10, 80, 20);
        Return.setText("返回");
        //去除按钮的边框
        Return.setBorderPainted(false);
        //去除按钮的背景
        Return.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        Return.addMouseListener(this);
        this.getContentPane().add(Return);


        JLabel background = new JLabel(new ImageIcon("images/register/beijing.png"));
        background.setBounds(-5, -20, 485, 472);


        this.getContentPane().add(usernameText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(rePasswordText);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(rePassword);
        this.getContentPane().add(submit);
        this.getContentPane().add(reset);
        this.getContentPane().add(background);
    }

    public void initJFrame() {
        this.setSize(485, 472);//设置宽高
        this.setTitle("注册界面");//设置标题
        this.setDefaultCloseOperation(1);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == reset){
            username.setText("");
            password.setText("");
            rePassword.setText("");
        } else if (e.getSource()==submit) {
            if(username.getText().length()==0||password.getText().length()==0||rePassword.getText().length()==0){
                System.out.println("用户名或者密码不能为空");
                return;
            }
            if(!username.getText().matches("[a-zA-Z0-9]{4,16}")){
                System.out.println("用户名不符合规则,请输入4-16位字母或数字");
                showDialog("用户名不符合规则,请输入4-16位字母或数字");
                return;
            }
            if(!password.getText().matches("[a-zA-Z0-9]{4,16}")){
                System.out.println("密码不符合规则,请输入4-16位字母或数字");
                showDialog("密码不符合规则,请输入4-16位字母或数字");
                return;
            }
            if(containsusername(username.getText())){
                System.out.println("用户名已存在,请重新输入");
                showDialog("用户名已存在,请重新输入");
                return;
            }
            if (!(password.getText().equals(rePassword.getText()))) {
                System.out.println("两次输入密码不一致");
                showDialog("两次输入密码不一致");
                return;
            }
            user us=new user(username.getText(),password.getText());
            users.add(us);
            showDialog("注册成功");
            try {
                writeuserinformation(us);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //关闭注册界面，打开登录界面
            this.setVisible(false);

            try {
                new LoginJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        else if (e.getSource() == Return) {
            this.setVisible(false);
            try {
                new LoginJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void writeuserinformation(user use) throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter("userinformation.txt",true));
        String str=use.toString();
        writer.write(str);
        System.out.println(use);
        writer.newLine();
        writer.close();
    }

    //因为展示弹框的代码，会被运行多次
    //所以，我们把展示弹框的代码，抽取到一个方法中。以后用到的时候，就不需要写了
    //直接调用就可以了。
    public void showDialog(String content){
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    public boolean containsusername(String text) {
        for (user user : users) {
            if(text.equals(user.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("images/register/注册按下.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("images/register/重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("images/register/注册按钮.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("images/register/重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
