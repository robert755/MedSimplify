package MedSimplify.app.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class AiService {
    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    public String getSimplification(String textMedical) {
        String finalUrl = apiUrl + "?key=" + apiKey;
        String prompt = "Ești un asistent medical calm, prietenos și foarte răbdător. " +
                "Misiunea ta este să citești acest buletin de analize și să 'traduci' termenii medicali complicați " +
                "în cuvinte simple, de zi cu zi, pe care le-ar înțelege oricine (chiar și cineva fără studii). " +
                "Scopul tău principal este să liniștești pacientul. Dacă vezi valori modificate (mai mari sau mai mici), " +
                "nu folosi termeni alarmanți. În loc de termeni tehnici, explică procesul din corp. " +
                "Nu oferi diagnostice grave și nu speria utilizatorul. Fii empatic. " +
                "Structurează răspunsul cu liniuțe. " +
                "La final, amintește-i blând să meargă cu rezultatele la medicul lui. " +
                "Textul analizelor este: " + textMedical;

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", prompt)
                        })
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        try {
            String rawResponse = restTemplate.postForObject(finalUrl, entity, String.class);
            JsonNode root = objectMapper.readTree(rawResponse);
            return root.path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return "Eroare: Nu am putut comunica cu AI-ul. Verifică cheia API.";
        }
    }
}
