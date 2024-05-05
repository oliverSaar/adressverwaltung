# Adressverwaltung

## Eigenschaften

Das Adressbuch soll Personen mit ihren Adressen und Telefonnummern speichern.
Personen, Adressen und Telefonnummern sollen hinzugefügt, bearbeitet und gelöscht werden können.
Einer Person soll es möglich sein beliebig viele Adressen und Nummern zu besitzen.
Anfangs soll ein Login (bzw. Registrierung bei nicht vorhandenem Account) stattfinden.
Personen können anderen Personen folgen und falls eine dieser Personen Geburtstag hat, wird nach dem Login eine
Nachricht diesbezüglich angezeigt.

## Technische Umsetzung

Modelle der drei Hauptobjekte erstellen:

Person: Vorname, Nachname, Geburtsdatum, Telefonnummern [], Adressen []

Adresse: Hausnummer, Straße, Ort, PLZ

Telefonnummer: Mobil (boolean), Nummer

Database-Object Interfaces für die drei Hauptobjekte und für den Login erstellen und diese dann implementieren,
sodass mit Leichtigkeit eine Datenbank verbunden werden kann.

Services für die Objekte erstellen, damit die gewünschten CRUD-Funktionen umgesetzt werden können.
Außerdem braucht der Personenservice besondere Aufmerksamkeit, da Adressen und Telefonnummern mit Personen verknüpfbar
sein sollen.

Innerhalb des Login-Services soll es eine anmelden bzw. registrieren Methode geben. Außerdem ist es wichtig,
den momentan angemeldeten User zu identifizieren, um Geburtstage der gefolgten Personen anzuzeigen und Berechtigungen zu
checken.

REST-Services erstellen, welche die Schnittstellen zu der (zukünftigen) Oberfläche darstellen.

RestHelperServices erstellen, die zwischen REST-Services und Logik arbeiten. Diese werden dann auch von den Views
angesprochen.

Views werden für jede Modellklasse eine benötigt, also Personen-, Adressen- und Telefonnummernview.
Zusätzlich gibt es eine Mainview, welche den Startpunkt und das Hauptmenü darstellt.

//TODO hier noch anpassen
# Ubiquitous Language
DAO - Data Object
DAO -> DAOImpl -> Service -> RestHelperService -> (RestService) -> View (Frontend)

Attribute von Person:
boolean loggedIn -> wenn dieser User eingeloggt ist, geht der boolean auf true
List<Person> following -> Die Personen, welchen der Nutzer folgt


Entities sind im Model Package



# How to RUN
mvn package
move in: adressverwaltung_rest -> target 
java -jar adressverwaltung_rest-1.0-SNAPSHOT-jar-with-dependencies.jar

Falls das Probleme macht, einfach die Program.java Klasse im adressverwaltung_rest package ausführen