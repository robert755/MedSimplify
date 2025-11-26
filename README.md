# MediSimplify

> **Analize medicale, traduse pe înțelesul tuturor.**

MediSimplify este o aplicație backend care ajută pacienții să înțeleagă rezultatele analizelor de laborator. Încarci PDF-ul, iar aplicația folosește **AI** pentru a explica termenii medicali simplu și clar.

---

###  Tech Stack
Proiect construit cu tehnologii moderne, standard în industrie (2025):

**Java 21** - Limbajul principal.
**Spring Boot 3** - Framework pentru REST API.
**Apache PDFBox** - Pentru procesarea și citirea fișierelor PDF.
**Gemini API** - Integrare LLM pentru simplificarea textului.
**Postman** - Pentru testarea endpoint-urilor.

---

### Cum funcționează?

1.  **Upload:** Utilizatorul trimite un fișier PDF prin endpoint-ul `/api/analize/upload`.
2.  **Procesare:** Aplicația extrage textul brut din document.
3.  **Inteligență:** Textul este trimis către GPT cu un prompt specializat ("Explică ca pentru un pacient").
4.  **Răspuns:** Primești înapoi explicația clară, fără termeni complicați.

---

### Ce am învățat din acest proiect
* Cum să construiesc un **REST API** robust cu Spring Boot.
* Manipularea fișierelor (**MultipartFile**) și extragerea de date.
* Cum să integrez un serviciu extern (**AI API**) într-o aplicație Java.
* Principii de **Clean Code** și separarea logicii (Controller vs Service).


