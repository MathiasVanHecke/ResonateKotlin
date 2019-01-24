# Resonate Kotlin
## Inleiding
Dit is de applicatie die geschreven is in Kotlin. Men kan dit uitvoeren op een Android toestel die minimaal een api level heeft van 28 en het besturingssysteem Oreo 9.

## Opstart process
Om het project te starten moeten we enkele dingen uitvoeren om het project goed te krijgen.

### Nodige installaties
Zorg ervoor dat volgende programma's geïnstalleerd zijn:
- Android Studio
- Een Android emulator of fysiek toestel met api level 28 en Oreo 9

### Opstarten
- In elk bestaand die verwijst naar de Resonate API verander deze naar waar uw eigen api is.
- Nadat men het project lokaal heeft opgeslagen, zou men instaat moeten zijn het zonder problemen op te starten.

### Flow van het project & duiding
Dit is de flow die de gebruiker moet volgen als ze de applicatie gebruiken.

|Nummer|Pagina|Duiding|
|--|--|--|
| 1 |Lauch Activity  | Scherm als men de eerste keer de applicatie opstart.|
|2|Login To Spotify Activity| De gebruiker krijgt een webbrowser te zien om Resonate Toegang te geven tot hun Spotify Data|
|3|Register Activity|De gebruiker kan de opgehaalde data aanpassen naar zijn huidige instellingen. Deze data wordt opgeslagen op de Resonate Database na op register knop de duwen.|
|4|Discover Activity| De gebruiker ziet alle artiesten & genres die in de database aanwezig zijn.|
|5|Swipe Activity| Alle gebruikers die een potentiële matches zijn, worden hier getoond. Men kan door middel van een swipe beweging aantonen of ze de getoonde gebruiker al dan niet leuk vinden.|
|6|Swipe Profile Activity| Als men op de vorige pagina op de foto klikt kan men alle informatie van de potentiële match zien.|


## Mogelijke uitbreidingen
- Een pagina voor alle effectieve matches.
- Een duidelijkere navigatie tussen pagina's. (header navigatie)
- Berichten sturen naar effectieve matches, met werking van real time data.
- Een instelling pagina voorzien.
- Een pagina voor huidige instellingen te veranderen van de gebruiker.




