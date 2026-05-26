# Custom Java Version Control System (VCS)

A local, lightweight, Git-inspired version control core engine and DevSecOps dashboard built entirely from scratch in pure Java. This project was developed to study data persistence, state transactional safety, and filesystem metadata mapping.

## 🚀 Core Architectural Features
* **State Registration (Staging):** Utilizes file-stream indexing to register files targeted for future version tracking.
* **Cryptographic Snapshotting (Commits):** Generates automated 8-character unique UUID substrings to catalog precise database states without filename collisions.
* **Metadata Tracking:** Maintains a synchronous, persistent transaction ledger (`HEAD`) logging historical state records.
* **Immutable Rollbacks:** Implements file restoration pipelines capable of extracting historical snapshots and reverting broken configurations cleanly.
* **Asynchronous Desktop GUI:** Built via Java Swing to visualize file movements between the local workspace and the secure hidden backup vault (`.mygit/objects`) in real-time.

## 🛠️ Technology Stack & System Concepts
* **Language:** Java Core (JDK 8+)
* **UI Framework:** Java Swing / AWT Event-Queue listeners
* **I/O Management:** Java File Streams, NIO Buffers, Process Buffering
* **Architecture Patterns:** Stateless Static Utility Engines, Structural Encapsulation

## 💻 How to Run the Application
1. Compile all source files via terminal:
   javac src/*.java -d .
   
2. Launch the Desktop Dashboard UI: 
      java VCSGui
   
3. Scroll to the bottom and click **Commit changes**.
