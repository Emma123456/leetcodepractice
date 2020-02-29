package homework.sort;

/**
 * 考生
 */
public class Examinee implements CountSortAble<Examinee> {
    //姓名
    private String name;
    //分数
    private int score;
    //排名
    private int orderNo;

    public Examinee(String name, int score){
        this.name = name;
        this.score = score;
    }
    @Override
    public int getValue() {
        return this.score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
