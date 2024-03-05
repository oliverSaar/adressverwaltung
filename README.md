# Adressverwaltung



## Eigenschaften
Das Adressbuch soll Personen mit ihren Adressen und Telefonnummern speichern.
Personen, Adressen und Telefonnummern sollen hinzugefügt, bearbeitet und gelöscht werden können.
Einer Person soll es möglich sein beliebig viele Adressen und Nummern zu besitzen.


## Technische Umsetzung

Modelle der drei Hauptobjekte erstellen:

Person: Vorname, Nachname, Geburtsdatum, Telefonnummern [], Adressen []

Adresse: Hausnummer, Straße, Ort, PLZ

Telefonnummer: Mobil (boolean), Nummer

Services für die Objekte erstellen, damit die gewünschten CRUD-Funktionen umgesetzt werden können.


REST-Service erstellen, welcher die Schnittstelle zu der Logik darstellt.



## Ubiquitos Language

