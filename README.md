# 🎓 Course Registration System

A Java-based system for managing university course registrations with custom data structures. This project includes a **custom hash table**, **queue-based waitlisting**, and a **singly linked list** to handle enrolled students, providing an efficient and reliable registration workflow.

---

## 🚀 Features

- 🧠 Custom **Hash Table** for fast course lookup and management
- 🔄 **Queue for waitlisting** students when a course is full
- 🧾 **Singly Linked List** to manage enrolled students per course
- ✅ Add and drop course functionality with robust error handling
- 💾 **Object serialization** for data persistence between sessions
- 📈 Improved registration workflows with prioritized waitlisting logic

---

## 🛠️ Technologies Used

| Language | Concepts/Techniques        |
|----------|----------------------------|
| Java     | OOP, Data Structures, I/O  |
| Java I/O | Object Serialization       |
| DSA      | Hash Table, Queue, Linked List |

---


## ⚙️ How It Works
1. Students attempt to register for a course.

2. If the course has seats available, the student is added to the enrolled list.

3. If full, the student is added to the waitlist queue.

4. When a student drops a course:

 - They’re removed from the enrolled list.

- The first student in the waitlist (FIFO) is enrolled.
---

## 💡 Example Usage
```
RegistrationSystem system = new RegistrationSystem();
system.addCourse("CMPS101", 30);
system.registerStudent("Yazan", "CMPS101");
system.dropStudent("Yazan", "CMPS101");
```

--- 

## 🔐 Error Handling & Data Persistence
- Handles edge cases like:

  - Dropping non-enrolled students

  - Duplicate enrollments

  - Invalid course IDs

- Saves all registration data using Java serialization ```(ObjectOutputStream)``` and restores it on startup.
---

## 🧑‍💻 Author
- Yazan Alsaleh
    - GitHub: @Yazan-ys202110319
---

## 📄 License
This project is available under the MIT License.