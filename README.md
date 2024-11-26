# Hill Top Apartments Management System

## Project Overview
The **Hill Top Apartments Management System** is a software solution designed to streamline the management of apartment services. This project includes features such as task scheduling, client and aide management, billing, and payment processing.

---

## Features
1. **Client Management**
   - Register and manage client details.
   - Assign tasks and maintain task history.
   - Track total spending on services.

2. **Aide Management**
   - Assign tasks to aides and track their progress.
   - Mark tasks as completed.

3. **Task Scheduling**
   - Schedule tasks for clients and aides.
   - Prevent duplicate scheduling.
   - View a list of all scheduled tasks.

4. **Billing System**
   - Generate detailed invoices for clients.
   - Apply discounts and calculate total costs.
   - Maintain a history of all invoices.

5. **Testing**
   - Comprehensive unit tests to ensure reliability.
   - Test cases for all critical features and edge scenarios.

---

## Project Structure
Hill-Top-Apartments/
├── src/
│   ├──-BillingSystem.java
│   ├──-Aide.java
│   │---Client.java
│   │---Task.java
|   |---TaskTest.java
│   ├---Scheduler.java
│   └── BillingSystemTest.java
│   ├── AideTest.java
│   ├── ClientTest.java
│   └── SchedulerTest.java
└── README.md

- **`billing/`**: Contains the billing system implementation.
- **`models/`**: Includes core models such as `Aide`, `Client`, and `Task`.
- **`scheduling/`**: Handles task scheduling logic.
- **`tests/`**: Contains JUnit test cases for all key components.

---

## How to Run
### Prerequisites
- **Java Development Kit (JDK)** 8 or higher.
- **JUnit** for running tests.

### Steps to Compile and Run


1. Clone the repository:
   ```bash
   git clone https://github.com/khalifa3isa/Hill-Top-Apartments-Group-5-.git
   
   
2. Navigate to the project directory:
   cd hill-top-apartments
   
3.Compile the project:
    javac src/**/*.java
              
4.Run the program:
   java src.Main
   
   
Future Enhancements

    1.    Integrate a payment gateway for online transactions.
    2.    Add user authentication for enhanced security.
    3.    Implement a graphical user interface (GUI) for easier interaction.


Contributors

    •    khalifa3isa - GitHub Profile
    •    Team Members:
    •    Khalifa Alblooshi 23001587
    •    Khalifa kamal     23003598
    •    Baha Mohammed     23001498
    •    Ahmed Abu Alrub   23001599


