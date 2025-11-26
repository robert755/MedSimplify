package MedSimplify.app.Controller;

import MedSimplify.app.Service.AiService;
import MedSimplify.app.Service.PdfService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/analize")
public class AnalysisController {
    private final PdfService pdfService;
    private final AiService aiService;

    public AnalysisController(PdfService pdfService, AiService aiService){
        this.pdfService= pdfService;
        this.aiService = aiService;
    }
    @PostMapping("/upload")
    public  String uploadAnalysis (@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "Eroare: ai uitat sa selectezi fisierul";

        }
        String textExtras = pdfService.extractTextFromPdf(file);
        String explicatie = aiService.getSimplification(textExtras);
        return explicatie;
    }
}
