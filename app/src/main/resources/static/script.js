
const dropArea = document.getElementById('drop-area');
const fileInput = document.getElementById('fileInput');
const fileNameDisplay = document.getElementById('fileName');
const analyzeBtn = document.getElementById('analyzeBtn');
const resultSection = document.getElementById('resultSection');
const resultText = document.getElementById('resultText');
const loadingSection = document.getElementById('loadingSection');

let selectedFile = null;

dropArea.addEventListener('click', () => {
    fileInput.click();
});

fileInput.addEventListener('change', (e) => {
    if (e.target.files.length > 0) {
        selectedFile = e.target.files[0];

        fileNameDisplay.textContent = "✅ " + selectedFile.name;
        fileNameDisplay.style.fontWeight = "bold";
        fileNameDisplay.style.color = "#059669"; // Verde
        analyzeBtn.classList.add('active');
        analyzeBtn.disabled = false;
        resultSection.classList.add('hidden');
    }
});
analyzeBtn.addEventListener('click', async () => {
    if (!selectedFile) return;
    analyzeBtn.disabled = true;
    analyzeBtn.innerText = "Se procesează...";
    loadingSection.classList.remove('hidden');
    resultSection.classList.add('hidden');
    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
        const response = await fetch('/api/analize/upload', {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const text = await response.text();
            resultText.innerText = text;
            resultSection.classList.remove('hidden');
        } else {
            resultText.innerText = "Eroare: Ceva nu a mers bine la procesarea fișierului.";
            resultSection.classList.remove('hidden');
        }

    } catch (error) {
        console.error("Eroare:", error);
        resultText.innerText = "Eroare de conexiune. Verifică dacă serverul Java este pornit.";
        resultSection.classList.remove('hidden');
    } finally {
        loadingSection.classList.add('hidden');
        analyzeBtn.innerText = "Analizează Documentul";
        analyzeBtn.disabled = false;
    }
});