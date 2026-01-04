## Clasa Platform
Se folosesc 2 HashMap-uri ceea ce permite găsirea obiectelor de tip Exam, respectiv Student după nume în O(1). În această clasă se face toată logica de găsire după nume, rămânând ca clasele Exam și Student să lucreze direct cu obiectele propriu-zise.

## Clasa Exam
### TreeMap\<Student, Double>
Conține studenții care au dat examenul și notele obținute. \
Complexități:
* Inserare - O(log(n))
* Ștergere - O(log(n))
* Găsire notă - O(log(n))

O implementare cu alt tip de Map ar fi făcut operația de generate_report O(n log(n)), în loc de O(n), dar inserarea O(1). 
Colecția folosită este mai eficientă, dacă ne așteptăm să se folosească des generate_report.

### TreeSet\<Questions>
Sunt 2 seturi de Questions care mențin 2 sortări diferite, pentru cele 2 task-uri care necesită.
Nu se dublează fiecare obiect, fiind dublate doar referințele.

## Clasa Student
Conține un HashSet care permite aflarea notei la un examen în O(1).

## Clasa Question
Am implementat tipul generic al răspunsului la nivel de clasă și am defenit generic correctAnswer. 
Acest lucru permite scriereametodelor de generare de String-uri la nivelul clasei abstracte. \
Alt beneficiu este posibilitatea apelării în mod generic a metodei checkAnswer cu ajutorul metodei ajutătoare checkAnswerFromString, 
ne mai fiind necesară verificarea subclasei în metoda registerSubmissions.

