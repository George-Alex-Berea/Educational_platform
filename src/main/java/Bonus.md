## Clasa Admin
Scopul principal este modelarea unui rol privilegiat ce poate modifica orice notă.\
În principiu ar conține o colecție de examene ```HashSet<Exam>``` la care are dreptul să facă modificări și o metodă ```setGrade(Student student, Exam exam, double newGrade)```.\
Într-o implementare concretă s-ar primi din linia de comandă numele administratorului, examenului și studentului și nota nouă.
Din clasa Platform (ce ar conține acum și un HashSet de admini) s-ar rezolva găsirea după nume a celor 3 obiecte. Metoda concretă ar fi:
```
setGrade(Student student, Exam exam, double newGrade) {
    if (!administeredExams.contains(exam))
        throw new IllegalActionException()
    // modificarea notei in obiectele
    // corespunzatoare examenului și studentului
}
```