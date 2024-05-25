package interFace;
import user.user;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ModifyJFrame extends JFrame implements MouseListener {
    ArrayList<user> users=new ArrayList<>();

    JButton login=new JButton();
    JButton register=new JButton();
    JPasswordField beforepasswordtext=new JPasswordField();
    JPasswordField nowpasswordtext=new JPasswordField();
    JTextField usertext=new JTextField();
    JTextField code=new JTextField();

    JLabel rightCode=new JLabel();
    public ModifyJFrame() throws IOException {
        readuserinformation();
        initJFrame();
        initview();
        this.setVisible(true);
    }

    public void readuserinformation() throws IOException {
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
        this.setTitle("修改密码界面");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initview(){
        //用户名图片
        JLabel userlabel=new JLabel(new ImageIcon("images/login/用户名.png"));
        userlabel.setBounds(128, 158, 51, 19);
        this.getContentPane().add(userlabel);

        //用户名输入框
        usertext.setBounds(195, 158, 200, 30);
        this.getContentPane().add(usertext);

        //密码图片
        JLabel passwordlabel=new JLabel(new ImageIcon("images/login/密码.png"));
        passwordlabel.setBounds(145, 194, 35, 18);
        this.getContentPane().add(passwordlabel);

        //密码输入框
        beforepasswordtext.setBounds(195, 194, 200, 30);
        this.getContentPane().add(beforepasswordtext);

        //再次输入密码图片
        JLabel repasswordlabel=new JLabel(new ImageIcon("images/register/再次输入密码.png"));
        repasswordlabel.setBounds(30, 229, 200, 30);
        this.getContentPane().add(repasswordlabel);

        //再次输入密码输入框
        nowpasswordtext.setBounds(195, 229, 200, 30);
        this.getContentPane().add(nowpasswordtext);

        //验证码图片
        JLabel codeText = new JLabel(new ImageIcon("images/login/验证码.png"));
        codeText.setBounds(133, 266, 50, 30);
        this.getContentPane().add(codeText);

        String codeStr = getcode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(300, 266, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);



        //验证码的输入框
        code.setBounds(195, 266, 100, 30);
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
        register.setIcon(new ImageIcon("images/register/注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);

        JLabel background = new JLabel(new ImageIcon("images/register/beijing.png"));
        background.setBounds(0, -20, 480, 475);
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
            String passwordinput=beforepasswordtext.getText();
            String nowpasswordinput=nowpasswordtext.getText();

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
            else if (!checkuser(userinfor)) {
                System.out.println("用户名或原密码错误");
                showJDialog("用户名或原密码错误");
            }
            else if(!codeinput.equalsIgnoreCase(rightCode.getText())){
                System.out.println("验证码不正确");
                showJDialog("验证码不正确");
            } else if(checkuser(userinfor)){
                if(!(nowpasswordinput.matches("[a-zA-Z0-9]{4,16}"))){
                    System.out.println("新密码不符合规则,请输入4-16位字母或数字");
                    showJDialog("新密码不符合规则,请输入4-16位字母或数字");
                }
                else if(passwordinput.equals(nowpasswordinput)){
                    System.out.println("新密码与原密码相同");
                    showJDialog("新密码与原密码相同");
                }
                else{
                    try {
                        writenewpassword(usernameinput,nowpasswordinput);
                        this.setVisible(false);
                        new LoginJFrame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else if (e.getSource()==register) {
            this.setVisible(false);
            new RegisterJFrame(users);
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
            register.setIcon(new ImageIcon("images/register/注册按钮.png"));
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

    private void writenewpassword(String usernameinput,String passwordinput) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("userinformation.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("userinformation.txt"));
        for (String s : list) {
            String name = s.split("&")[0];
            if (!name.equals(usernameinput)) {
                bw.write(s);
                bw.newLine();
            } else {
                bw.write(name + "&" + passwordinput);
                bw.newLine();
            }
        }
        bw.close();
        br.close();
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
