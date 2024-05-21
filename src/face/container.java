package face;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

public class container {
    private int nowScore;
    private static int size = 5;
    private block[][] blocks = new block[5][5];
    private ArrayList<int[]> NullIndex = new ArrayList<>();
    //    保存所有的没有位置的区域坐标-需要经常进行增删改

    public void setNullIndex(ArrayList<int[]> nullIndex) {
        NullIndex = nullIndex;
    }
    public ArrayList<int[]> getNullIndex() {
        return NullIndex;
    }


    public int getNowScore() {
        return nowScore;
    }

    public void setNowScore(int nowScore) {
        this.nowScore = nowScore;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        container.size = size;
    }

    public block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(block[][] blocks) {
        this.blocks = blocks;
    }

    //初始化容器
    public container() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!filter(i, j))
                    continue;
                //初始将所有格子的坐标都添加到NullIndex中
                NullIndex.add(new int[]{i, j});
                //初始化所有的方块
                blocks[i][j] = new block(0, i, j);
            }
        }
    }

    public void initNullIndex(){
        NullIndex.clear();
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (!filter(i, j))
                    continue;
                //初始将所有格子的坐标都添加到NullIndex中
                if(!blocks[i][j].getStu())
                    NullIndex.add(new int[]{i, j});
            }
        }
    }

    public boolean filter(int i, int j){
        if(i==0 && j==0 || i==4 && j==0 || i==0 && j==4 || i==4 && j==4)
            return false;
        else return true;
    }
    //容器内所有方格滑动
    public void slide(int input){
        int levelStart=0;
        int levelEnd=0;
        int verStart=0;
        int verEnd=0;
        int levelStep=0;
        int verStep=0;
        //初始化四个值
        //向左移动
        if(input == 48){
            for(int i=0;i<size;i++){
                int empty = 0;
                for(int j=0;j<size;j++) {
                    if (!filter(i, j)) {
                        empty = 1;
                        continue;
                    }
                    if(blocks[i][j].getStu()){
                        int id = blocks[i][empty].getId();
                        blocks[i][empty].setId(blocks[i][j].getId());
                        blocks[i][j].setId(id);
                        empty++;
                    }
                }
            }
        }





        if(input == 12){
            for(int j=0;j<5;j++){
                verStart+=verStep;
                int start = levelStart;
                int notNullIndex=-1;
                for(int i = 0;i< 5;i++) {
                    //为true代表该方格不是空方格
                    if(blocks[verStart][start].getStu()){
                        //判断前面是否有空方格,如果有的话与第一个空方格交换，并将表示第一个空方格的下标+levelStep
                        if(notNullIndex+levelStep<=start){
                            int x1,y1,x2,y2;
                            //交换两个方格

                            //加上步长
                            notNullIndex+=levelStep;
                        }
                    }
                    else {
                        if(notNullIndex==-1)
                            notNullIndex = start;
                    }


                    //遍历数组


                    start+=levelStep;
                }

            }
        }
        show_blocks();
        System.out.println("左移完成");

    }

    public void show_blocks(){
        System.out.println("---------------------");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(!filter(i, j))
                    continue;
                System.out.print(blocks[i][j].getId() + " ");
                System.out.print(blocks[i][j].getX_index() + " " + blocks[i][j].getY_index() + "    ");
            }
            System.out.println();
        }
        System.out.println("_________________");
    }
    //将容器内滑动后方格进行合并，达到消除的效果
    //合并后产生的数字如果大于score，则更新score
    public boolean merge(){
        return false;
    }


    //是否还能继续游戏
    public boolean isContinue(){
        if(Isfull() && merge())
            return false;
        else return true;
    }
    //返回容器是否全都被非0方块填满
    public boolean Isfull(){
        if(getNullIndex().isEmpty())
            return true;
        else
            return false;
    }

    //交换两个方块，用于方块滑动和合并
    //交换两个方块，但是不改变原来的x和y坐标
    public void Swapblock(block a, block b){
        block temp = a;
        a = b;
        b = temp;
        int a_x = a.getX_index();
        int a_y = a.getY_index();
        a.setX_index(b.getX_index());
        a.setY_index(b.getY_index());
        b.setX_index(a_x);
        b.setY_index(a_y);
    }

    //随机功能还未能实现
    //在所有空的位置里随机找到一个位置作为新出现的方格，最后将坐标返回
    public void RandomGenerate(){
        if(Isfull())
            return;
        int id = 2;
        for (int i = 0; i < getNullIndex().size(); i++) {
            System.out.println("x  " + getNullIndex().get(i)[0] + "  y   " + getNullIndex().get(i)[1]);
        }
        System.out.println(getNullIndex().size());
        int length = NullIndex.size();
        Random r = new Random();
        int random = r.nextInt(0, length);
        int new_x = NullIndex.get(random)[0];
        int new_y = NullIndex.get(random)[1];
        blocks[new_x][new_y].setId(32);
        NullIndex.remove(random);
    }

}
