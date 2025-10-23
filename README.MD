## Project 1: Contact Book

**Description:**  
A simple **Java console application** that connects to a **MySQL database** to manage personal contacts.  
Supports creating, viewing, searching, and deleting.

**Tech Stack:**
- ☕ Java (JDK 24)
- 🗄️ MySQL (via JDBC)
- ⚙️ IntelliJ IDEA

**Key Features:**
- 1. Add new contacts (name, phone, email)
- 2. View all saved contacts
- 3. Search contacts by ID or name
- 4. Delete contacts by ID or name
- 5. Uses `PreparedStatement` for SQL safety
- 6. Database credentials handled through environment variables

**Database Schema:**
```bash
CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(50)
);
```
`
### ⚙️ How to Run

1. Set up the MySQL database **`contact_book`**.
2. Configure these environment variables in IntelliJ or your system:
   ```bash
   DB_URL=jdbc:mysql://localhost:3306/contact_book
   DB_USER=root
   DB_PASSWORD=your_password`
