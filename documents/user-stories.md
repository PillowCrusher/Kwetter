# User stories
Als ... wil ... zodat ...
---
## Functional requirements  

### Profile  
- Als gebruiker wil ik mijn gebruikersnaam kunnen wijzigen en zien of deze beschikbaar is zodat ik weet of mijn naam beschikbaar is.
- Als gebruiker wil ik een bio, locatie en website kunnen invullen zodat ik in het kort kan omschrijven wie ik ben.
- Als gebruiker wil ik mijn laatste 10 tweets kunnen zien zodat ik een overzicht voor heb van mijn tweets.
- Als gebruiker wil ik een overzicht hebben van al mijn followers zodat ik weet wie mijn followers zijn.
- Als gebruiker wil ik een overzicht hebben van alle mensen die ik follow zodat ik weet welke mensen ik follow.
- **Optioneel** als gebruiker wil ik tweets van andere gebruikers een hartje kunnen geven zodat ik waardering kan aangeven.  

### Home  
- Als gebruiker wil ik kunnen zoeken op alle tweets zodat ik alle tweets terug kan vinden.
- Als gebruiker wil ik een tweet kunnen posten zodat ik mijn mening kan delen.
- Als gebruiker wil ik tweets van mezelf en mensen die ik volg zien zodat ik het laatste nieuws kan lezen.
- Als gebruiker wil ik kunnen uitloggen zodat ik mijn gebruikers sessie kan eindigen.
- Als gebruiker wil ik kunnen inloggen zodat ik kan tweetten en mijn nieuws kan lezen.
- **Optioneel** als gebruiker wil ik andere mensen kunnen mentionen door de @ Handler te gebruiken zodat ik andere mensen kan betrekken.
- **Optioneel** als gebruiker wil ik de laatste trendy onderwerpen kunnen zien zodat ik een overzicht heb van de meest populaire onderwerpen.  

### Register/Login   
- Als gebruiker wil ik een gepersonaliseerde start pagina zodat ik mezelf betrokken voel.
- Als gebruiker wil ik authentiseren voordat ik mijn persoonlijke gegevens kan wijzigen zodat alleen de juiste gebruiker bij de gegevens kan.
- **Optioneel** Als gebruiker wil ik mezelf kunnen registreren.

### Manage
- Als moderator wil ik een lijst van tweets opkunnen vragen en zo nodig tweets verwijderen zodat we tweets kunnen filteren.
- Als moderator wil ik een lijst van gebruikers op kunnen vragen zodat ik de gebruikers kan inzien.
- **Optioneel** als administrator wil ik in de lijst van gebruikers rollen kunnen toewijzen zodat er verschillende gebruikers mogelijk  zijn.  

---
## Non-functional requirements  

## Constraints  

### Profile  
- Een bio van een gebruiker mag maar 160 characters lang zijn.
- **Optioneel** een gebruiker mag maar maximaal 1 hartje geven per tweet.  

### Home  
- Het zoeken van tweets wordt gedaan met behulp van AJAX.
- Een tweet mag maar 140 characters lang zijn.
- Het in- en uitloggen wordt gedaan met JAAS.  

### Register/Login  
- Het systeem moet instaat zijn om meerdere gebruikers bij te houden.  

### Manage
- Mogelijkheid voor een moderator rol.
- Bij het opzetten van moderator functionaliteit moet opgelet worden op het toevoegen van andere rollen.  

### Multi language  
- De gebruiker kan minimaal kiezen tussen Nederlands en Engels.  
