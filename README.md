# BITTE LESEN: Backend

Um die Backend-Abhängigkeiten zu verringern, stellen wir 3 Dockerimages bereit.

1. Anwendung auschecken
2. Branch zu **dev** wechseln
3. Maven **clean install** durchführen
4. folgenden Befehlt unter /backend ausführen:
   ``docker-compose up --build``
   
Danach wird eine vollständige Umgebung mit Datenbank, PhpMyAdmin und der Anwendung hochgefahren

Dokumentation für die bereitgestellten Schnittstellen findet ihr unter:
http://localhost:8080/swagger-ui.html

PhpMyAdmin ist unter folgenden Link erreichbar:
http://localhost:1234/
   
Die Logindaten sind:
* Server: db 
* User: root
* Password: notSecureChangeMe