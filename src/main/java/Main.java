import Reviews.AllReviews;
import WriteData.WriteData;
import com.itextpdf.text.DocumentException;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args) throws DocumentException {

        System.out.println("предоставьте ссылку на страницу с отзывами:");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(path);
        WebElement paginator = null;
        try
        {
            paginator = webDriver.findElement(By.className("pagination-holder")); // проверяю есть ли блок со страницами, если нет значит страница 1
        }
        catch (Exception ignored)
        {
        }

        int numberOfPages = 1;
        if (paginator != null) numberOfPages = getNumberAllPages(paginator);  //храню количество страниц

        

        AllReviews MYreviews = new AllReviews();

        for (int i = 1; i <= numberOfPages; i++)
        {
            if (i != 1)
            {
                webDriver.get(path + "?page=" + i);
            }
            List<WebElement> reviewers = webDriver.findElements(By.className("reviewers-box")); // все boxы
            for (WebElement revie : reviewers)
            {

                int grade = Integer.parseInt(revie.findElement(By.cssSelector("[itemprop='ratingValue']")).getText());
                String plus = revie.findElement(By.cssSelector("[itemprop='pro']")).getText();
                String minus = revie.findElement(By.cssSelector("[itemprop='contra']")).getText();
                String comment = revie.findElement(By.cssSelector("[itemprop='reviewBody']")).getText();
                MYreviews.addReviews(grade, minus,plus, comment);

            }
        }
        String title = (webDriver.findElement(By.cssSelector("[class='ib']")).getText());
        WriteData writer = new WriteData(MYreviews.getAllReviews(),title);
        writer.saveReviewsToPdf("reviews.pdf");
    }


    private static int getNumberAllPages(WebElement paginator)
    {
        String pag = paginator.findElement(By.className("all-pages")).getText();
        int lastIndex = pag.lastIndexOf(" ");

        return Integer.parseInt(pag.substring(lastIndex + 1));
    }

}