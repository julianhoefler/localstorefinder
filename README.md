# BITTE LESEN: Backend für den localstorefinder

von Florian Janietz und Julian Höfler
Updated am 28.05.21, importiert aus GitLab am 17.12.21

Aktuelle Version: 1.2.0

Um die Backend-Abhängigkeiten zu verringern, stellen wir 3 Dockerimages bereit.

1. Anwendung auschecken
2. (falls nötig) Branch zu **master** wechseln
3. Maven **clean install** durchführen
4. folgenden Befehl im Ordner ausführen:
   ``docker-compose up --build``

Danach wird eine vollständige Umgebung mit Datenbank, PhpMyAdmin und der Anwendung hochgefahren

PhpMyAdmin ist unter folgenden Link erreichbar:
http://localhost:1234/

Die Logindaten sind:

* Server: db
* User: root
* Password: notSecureChangeMe

Für die Dokumentation der API und zum Testen der Schnittstellen müsst ihr die Swagger-UI öffnen:
http://localhost:8080/swagger-ui.html
