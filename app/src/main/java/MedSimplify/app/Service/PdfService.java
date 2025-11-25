package MedSimplify.app.Service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@Service
public class PdfService {
    public String extractTextFromPdf (MultipartFile file){

        try{
            PDDocument document=PDDocument.load(file.getInputStream());
            PDFTextStripper stripper=new PDFTextStripper();
            String textExtras= stripper.getText(document);
            document.close();
            return textExtras;

        }catch (IOException e){
            e.printStackTrace();
            return "Eroare: Nu se poate citi acest pdf.";
        }

    }
}
