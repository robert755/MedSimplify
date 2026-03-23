# MedSimplify

**Laboratory reports, explained in plain language.**

MedSimplify is a Spring Boot application that helps people understand medical lab PDFs. Upload a report, and the app extracts the text and uses **Google Gemini** to rewrite jargon into calm, everyday explanations—without replacing professional medical advice.

---

## Features

- **PDF upload** via a simple web UI (`static` resources) or the REST API
- **Text extraction** with Apache PDFBox
- **Patient-friendly summaries** via Gemini, with prompts tuned for clarity, empathy, and a gentle reminder to follow up with a doctor

---

## Tech stack

| Layer | Choice |
|--------|--------|
| Runtime | Java **17** |
| Framework | **Spring Boot 3.5** (Web, REST) |
| PDF | **Apache PDFBox** 2.0.x |
| AI | **Google Gemini** (`gemini-2.0-flash`) over REST |
| Client | Static **HTML / CSS / JavaScript** (served by Spring Boot) |

---

## How it works

1. **Upload** — The user sends a PDF (browser or `POST /api/analize/upload` with multipart field `file`).
2. **Extract** — `PdfService` reads plain text from the PDF.
3. **Simplify** — `AiService` sends that text to Gemini with a specialized prompt (simple language, non-alarming tone, bullet structure, encourage seeing a physician).
4. **Response** — The API returns the generated explanation as plain text.

---

## Prerequisites

- JDK **17**
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)
- A **Google AI Studio** API key for Gemini ([Google AI Studio](https://aistudio.google.com/))

---

## Configuration

Set your API key as an environment variable (see `application.properties`):

```bash
set GEMINI_API_KEY=your_key_here
```

On macOS/Linux:

```bash
export GEMINI_API_KEY=your_key_here
```

The default model endpoint is configured in `application.properties` as `gemini-2.0-flash:generateContent`.

---

## Run locally

From the `app` directory:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Then open **http://localhost:8080** for the web UI, or call the API below.

---

## API

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/analize/upload` | Multipart form: field name **`file`** (PDF). Returns simplified text or an error message string. |

Example with `curl` (replace paths as needed):

```bash
curl -X POST http://localhost:8080/api/analize/upload -F "file=@/path/to/report.pdf"
```

---

## Project layout

```
app/
├── src/main/java/MedSimplify/app/
│   ├── AppApplication.java
│   ├── Controller/AnalysisController.java
│   └── Service/{PdfService,AiService}.java
├── src/main/resources/
│   ├── application.properties
│   └── static/          # Web UI (index.html, script.js, style.css)
└── pom.xml
```

---

## Disclaimer

This tool is for **education and readability only**. It does not diagnose conditions or replace a qualified healthcare professional. Always discuss lab results with your doctor.

---

## What this project demonstrates

- Building a small **REST API** with Spring Boot
- Handling **multipart file uploads** and PDF parsing
- Integrating an **external LLM API** from Java (`RestTemplate` + JSON)
- Separating **controllers** from **services** for clearer structure
