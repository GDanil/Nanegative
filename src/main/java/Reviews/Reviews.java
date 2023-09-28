package Reviews;

public class Reviews
{
    private String title;
    private int grade;
    private String minus;
    private String plus;
    private String comment;

    public Reviews(int grade, String minus, String plus, String comment)
    {
        this.grade = grade;
        this.minus = minus;
        this.plus = plus;
        this.comment = comment;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }

    public String getPlus()
    {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
