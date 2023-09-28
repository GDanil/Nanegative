package Reviews;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class AllReviews
{
    private ArrayList<Reviews> allReviews;

    public AllReviews()
    {
        this.allReviews = new ArrayList<>();
    }

    public ArrayList<Reviews> getAllReviews()
    {
        return allReviews;
    }
    public void addReviews(int grade, String minus, String plus, String comment)
    {
        allReviews.add(new Reviews(grade,minus,plus,comment));
    }
}
