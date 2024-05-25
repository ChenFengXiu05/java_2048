package interFace;
import user.user;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {
    ArrayList<user> users=new ArrayList<>();

    JButton login=new JButton();
    JButton register=new JButton();
    public static JTextField passwordtext=new JTextField();
    public static JTextField usertext=new JTextField();
    JTextField code=new JTextField();

    JLabel rightCode=new JLabel();
    JLabel forgetcode=new JLabel();
    JLabel modifycode=new JLabel();



    public LoginJFrame() throws IOException {
        readuserinformation();
        initJFrame();
        initview();
        this.setVisible(true);
    }

    private void readuserinformation() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader("userinformation.txt"));
        String line;
        while((line=reader.readLine())!=null){
            String[] str=line.split("&");
            users.add(new user(str[0],str[1]));
        }
        reader.close();
    }

    public void initJFrame(){
        this.setSize(485, 472);//设置宽高
        this.setTitle("登陆界面");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initview(){
        JLabel userlabel=new JLabel(new ImageIcon("images/login/用户名.png"));
        userlabel.setBounds(128, 158, 51, 19);
        this.getContentPane().add(userlabel);


        usertext.setBounds(195, 158, 200, 30);
        this.getContentPane().add(usertext);

        JLabel passwordlabel=new JLabel(new ImageIcon("images/login/密码.png"));
        passwordlabel.setBounds(130, 210, 35, 18);
        this.getContentPane().add(passwordlabel);


        passwordtext.setBounds(195, 210, 200, 30);
        this.getContentPane().add(passwordtext);


        JLabel codeText = new JLabel(new ImageIcon("images/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        String codeStr = getcode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        forgetcode.setText("忘记密码");
        forgetcode.addMouseListener(this);
        forgetcode.setBounds(360, 246, 50, 30);
        this.getContentPane().add(forgetcode);

        modifycode.setText("修改密码");
        modifycode.addMouseListener(this);
        modifycode.setBounds(360, 266, 50, 30);
        this.getContentPane().add(modifycode);


        //验证码的输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        //5.添加登录按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("images/login/登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.添加注册按钮

        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("images/login/注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);

        JLabel background = new JLabel(new ImageIcon("images/register/beijing.png"));
        background.setBounds(0, 0, 470, 430);
        this.getContentPane().add(background);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==rightCode){
            System.out.println("更换验证码");
            String code =getcode();
            rightCode.setText(code);
        }
        else if(e.getSource()==login){
            String usernameinput=usertext.getText();
            String passwordinput=passwordtext.getText();

            user userinfor=new user(usernameinput,passwordinput);

            String codeinput=code.getText();
            if (usernameinput.isEmpty()||passwordinput.isEmpty()){
                System.out.println("用户名或者密码不能为空");
                showJDialog("用户名或者密码不能为空");
            }

            else if(codeinput.isEmpty()){
                System.out.println("验证码不能为空");
                showJDialog("验证码不能为空");
            }
            else if(!codeinput.equalsIgnoreCase(rightCode.getText())){
                System.out.println("验证码不正确");
                showJDialog("验证码不正确");
            }
            else if(!checkuser(userinfor)){
                System.out.println("用户名或者密码错误");
                showJDialog("用户名或者密码错误");
            }
            else if(checkuser(userinfor)){
                System.out.println("登陆成功");
                System.out.println("登录成功，欢迎来到主页面");
                this.setVisible(false);
                new IndexJFrame();
            }
        } else if (e.getSource()==register) {
            this.setVisible(false);
            new RegisterJFrame(users);
        } else if (e.getSource()==forgetcode) {
            System.out.println("忘记密码");
            this.setVisible(false);
            try {
                new  ForgetJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if(e.getSource()==modifycode){
            System.out.println("修改密码");
            this.setVisible(false);
            try {
                new ModifyJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==login){
            login.setIcon(new ImageIcon("images/login/登录按下.png"));
        }
        else if (e.getSource()==register){
            register.setIcon(new ImageIcon("images/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==login){
            login.setIcon(new ImageIcon("images/login/登录按钮.png"));
        }
        else if (e.getSource()==register){
            register.setIcon(new ImageIcon("images/login/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public String getcode(){
        //1.创建一个集合
        char[] array=new char[52];
        //2.添加字母 a - z  A - Z
        for (int i = 0; i < 26; i++) {
            array[i]=(char)('a' + i);
        }
        int index=0;
        for (int i = 26; i < 52; i++) {
            array[i]=(char)('A' + index);
            index++;
        }

        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            //获取随机索引
            int randomIndex = r.nextInt(array.length);
            char c = array[randomIndex];
            result = result + c;
        }

        int number = r.nextInt(10);

        result = result + number;

        char[] chars = result.toCharArray();


        index = r.nextInt(chars.length);


        char temp = chars[4];
        chars[4] = chars[index];
        chars[index] = temp;
        //10.把字符数组再变回字符串
        String code = new String(chars);
        //System.out.println(code);
        return code;
    }
    public boolean checkuser(user user){
        for (int i = 0; i < users.size(); i++) {
            user user1=users.get(i);
            if(user1.getName().equals(user.getName())&&user1.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
    public void showJDialog(String content) {
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
}
