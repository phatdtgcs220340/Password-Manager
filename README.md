# Password Manager Project
## Building a second brain
Create a password manager that has a powerful password management system and trustworthy storage.
<be>
![image](https://github.com/phatdtgcs220340/Password-Manager/assets/114555261/49f42a0c-7abc-4ff4-8369-41b50c59c276)

## Installation

### 1. Install Java 20

Make sure you have Java 20 installed on your system. You can download it from the official Oracle website or use an open-source distribution like OpenJDK. Ensure that Java is added to your system's PATH variable.
### 2. Install PostgreSQL

The Password Manager Project requires a PostgreSQL database for storing encrypted passwords securely. Follow these steps to install PostgreSQL:

- **Windows:**
  - Download the PostgreSQL installer from the official website: [PostgreSQL Downloads](https://www.postgresql.org/download/windows/)
  - Run the installer and follow the on-screen instructions to complete the installation.

- **Linux:**
  - Use your package manager to install PostgreSQL. For example, on Debian-based systems, you can use the following command:
    ```bash
    sudo apt-get update
    sudo apt-get install postgresql
    ```

- **Mac:**
  - You can use Homebrew to install PostgreSQL. If you don't have Homebrew installed, follow the instructions on [Homebrew's website](https://brew.sh/), then run:
    ```bash
    brew install postgresql
    ```

### 3. Install Gradle Build Tool

The project uses Gradle as the build tool. Follow these steps to install Gradle:

- **Windows:**
  - Download the latest version of Gradle from the official website: [Gradle Downloads](https://gradle.org/releases/)
  - Extract the downloaded ZIP file to a location on your machine.
  - Add the Gradle `bin` directory to your system's PATH variable.

- **Linux and Mac:**
  - You can use a package manager to install Gradle. For example, on Linux with apt:
    ```bash
    sudo apt-get install gradle
    ```

    On Mac with Homebrew:
    ```bash
    brew install gradle
    ```

### 4. Clone the Repository

Clone the Password Manager Project repository to your local machine:

```bash
git clone https://github.com/your-username/password-manager-project.git
