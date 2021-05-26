# BITTE LESEN: Backend für den localstorefinder

Updated am 26.05.21

Aktuelle Version: 1.1.0

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

Die Dokumentation der API und zum Testen der Schnittstellen müsst die Swagger-UI öffnen:
http://localhost:8080/swagger-ui.html