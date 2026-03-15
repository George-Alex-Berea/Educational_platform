# Project Documentation: Platform Architecture

## Platform Class
The implementation utilizes two `HashMap` structures, which facilitates the retrieval of `Exam` and `Student` objects by their names in $O(1)$ time complexity. This class centralizes all name-based lookup logic, allowing the `Exam` and `Student` classes to interact directly with the objects themselves.

---

## Exam Class

### `TreeMap<Student, Double>`
This collection stores the students who participated in the exam and their respective grades.

**Complexities:**
* **Insertion:** $O(\log n)$
* **Deletion:** $O(\log n)$
* **Grade Lookup:** $O(\log n)$

> **Note:** An implementation using a different Map type (such as a `HashMap`) would have made the `generate_report` operation $O(n \log n)$ instead of $O(n)$, even though insertion would be $O(1)$. This specific collection is more efficient if `generate_report` is expected to be used frequently.

### `TreeSet<Questions>`
There are two sets of `Questions` that maintain two different sorting orders to satisfy the requirements of two specific tasks. To optimize memory usage, the objects themselves are not duplicated; only the references are stored across both sets.

---

## Student Class
Contains a `HashSet` that allows for checking the grade of an exam in $O(1)$ time complexity.

---

## Question Class
The response type is implemented as a **generic** at the class level, and the `correctAnswer` is also defined generically. 

**Key Benefits:**
* **Abstraction:** This allows the String generation methods to be written at the abstract class level.
* **Generic Invocation:** The `checkAnswer` method can be called generically using the `checkAnswerFromString` helper method.
* **Clean Code:** This eliminates the need for manual subclass verification (e.g., `instanceof` checks) within the `registerSubmissions` method.
