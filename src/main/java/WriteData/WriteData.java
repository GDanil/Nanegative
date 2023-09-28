package WriteData;

import Reviews.Reviews;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteData
{
    private String title;
    private ArrayList<Reviews> reviews;

    private final String FONT = "Times New Roman.ttf";

    public WriteData(ArrayList<Reviews> reviews,String title)
    {
        this.reviews = reviews;
        this.title = title;
    }

    public void saveReviewsToPdf(String path) throws DocumentException {
        Document document = new Document();

        try
        {
            PdfWriter.getInstance(document, new FileOutputStream(path));
        }
        catch (DocumentException | FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        document.open();

        BaseFont baseFont;
        try
        {
            baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        catch (DocumentException | IOException e)
        {
            throw new RuntimeException(e);
        }
        Font font = new Font(baseFont, 14, Font.NORMAL);

        document.add(new Paragraph("Сегодняшняя тема для обсуждения: " + title, font));

        for (int i = 0; i < reviews.size(); i++)
        {
            Reviews review = reviews.get(i);


            try
            {
                document.add(new Paragraph((i + 1) + ") Оценка: " + review.getGrade(), font));
                document.add(new Paragraph("    Плюсы: " + review.getPlus(), font));
                document.add(new Paragraph("    Минусы: " + review.getMinus(), font));
                document.add(new Paragraph("    Комментарий: " + review.getComment(), font));
            }
            catch (DocumentException e)
            {
                throw new RuntimeException(e);
            }
        }

        document.close();
    }
}

