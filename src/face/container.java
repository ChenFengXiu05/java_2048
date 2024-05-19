package face;

import java.util.ArrayList;
import java.util.Random;

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
        int count = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(i==0 && j==0 || i==4 && j==0 || i==0 && j==4 || i==4 && j==4)
                    continue;
                //初始将所有格子的坐标都添加到NullIndex中
                NullIndex.add(new int[]{i, j});
                System.out.println(NullIndex.get(count)[0]);
                //初始化所有的方块
                blocks[i][j] = new block(0, i, j);
                count++;
            }
        }
    }

    //容器内所有方格滑动
    public void slide(){
        System.out.println("sd");

    }

    //将容器内滑动后方格进行合并，达到消除的效果
    //合并后产生的数字如果大于score，则更新score
    public void merge(){

    }



    //返回容器是否全都被非0方块填满
    public boolean Isfull(){
        if(getNullIndex().isEmpty())
            return true;
        else
            return false;
    }

    //在所有空的位置里随机找到一个位置作为新出现的方格，最后将坐标返回
    public void RandomGene(){
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
        System.out.println("new_x" + new_x + " new_y" + new_y);
        int x = blocks[new_x][new_y].getX_index();
        int y = blocks[new_x][new_y].getY_index();
        System.out.println("x" + x + " y" + y);
        blocks[new_x][new_y] = new block(16, x, y);
        NullIndex.remove(random);
    }

}
