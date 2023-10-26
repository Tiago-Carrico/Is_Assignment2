# Is_Assignment2
 
### Setting up Server
- Build Docker Image using "Dockerfile" (if using VsCode, with Docker extension, right click and click "Build")
- Docker Compose Up using docker-compose.yml (if using VsCode, with Docker extension, right click and click "Compose Up")


### Fixes for possible issues
- If dependencies are not working, try to open VsCode command pallete (Ctrl + Shift + P) and run the command "Java: Clean the Java language Workspace" by "Restart and Delete" when given the prompt
- Log in the postgres terminal in Docker:
  - su - postgres
  - psql
  - \c ownersandpets    (importante senao nao se ve as relações/base de dados)



### Class questions
- init_db.sql doesn't really gets used even if it triggers an error if not present?