🖥️ Employee Record Management System (Java Swing)


![Uploading Screenshot 2026-02-26 113316.png…]()

A simple Desktop GUI Application built using Java Swing to manage employee records.
The application allows users to Save, View, Update, Delete, Search, and Navigate employee data stored in a text file.

📌 Features

✅ Add new employee record (Name, Age, Salary)

✅ View First record

✅ View Last record

✅ Navigate Next record

✅ Navigate Previous record

✅ Update existing record

✅ Delete record

✅ Search record by Name

✅ File-based data persistence (data.txt)

🛠️ Technologies Used

Java

Swing (GUI)

AWT Event Handling

File Handling (BufferedReader, FileWriter, PrintWriter)

Collections (ArrayList)

🧠 Project Concept

This project demonstrates:

Object-Oriented Programming (OOP)

Event-Driven Programming

GUI Development using Swing

File Handling in Java

Basic CRUD Operations

Navigation logic using ArrayList

Record searching using StringTokenizer

📂 File Structure
MySwingProgram.java
data.txt

MySwingProgram.java → Main application file

data.txt → Stores employee records permanently

🖼️ User Interface

The GUI contains:

3 Input Fields:

Name

Age

Salary

8 Buttons:

Save

First

Last

Next

Previous

Delete

Update

Search

⚙️ How It Works

On startup, the program reads data.txt.

All records are loaded into an ArrayList.

User can perform operations using buttons.

Changes are immediately saved to the file.

Navigation works using an index variable (operator).

▶️ How to Run
Step 1: Compile
javac MySwingProgram.java
Step 2: Run
java MySwingProgram

Make sure:

Java JDK is installed

data.txt is in the same directory

🔍 Example Record Format (data.txt)
John 25 50000
Alice 30 60000

Format:

Name Age Salary
🚀 Learning Outcomes

After completing this project, you will understand:

GUI design using Java Swing

Event handling with ActionListener

File read/write operations

Data management using ArrayList

Basic desktop application structure

🎯 Future Improvements

Add validation for Age & Salary (Numeric only)

Use JTable for better record display

Add confirmation dialogs before delete

Store data using database (MySQL)

Improve UI layout using GridLayout or BorderLayout

👩‍💻 Author

Yashasvi Singh Rajput
AIML Undergraduate | Backend & Java Enthusiast
