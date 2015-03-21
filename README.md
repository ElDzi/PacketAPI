# PacketAPI

- Simple classes to management TabList ! - Coming soon (- full documentation) !
- Proste klasy do zarzadzania TabList'ą ! - Już Wkrótce (- pełna dokumentacja) !

![ScreenShot](http://i.imgur.com/VfRjyl7.png)

# Pakiety na wlasna reke ?

Jesli nie chcesz uzywac pakietow na wlasna reke to zastosowanie jest bardzo proste

PacketSend ps = new PacketSend(PLAYER); //nowy obiekt
ps.sendpacket(OBJECT); //wyslij pakiet

PacketPlayOutScoreboardTeam ppost = new PacketPlayOutScoreboardTeam(STRING); //nowy obiekt TEAM

ppost.getPacket(ScoreboardTeamType.CREATE); //stworz TEAM

ppost.getPacket(ScoreboardTeamType.DELETE); //usun TEAM

ppost.setPrefix(STRING); // ustaw prefix dla TEAMU
ppost.setSuffix(STRING); // ustaw suffix dla TEAMU
ppost.getPacket(ScoreboardTeamType.UPDATE); //TEAM ulegnie aktualizacji glownie PREFIX i SUFFIX ustawiony powyzej ...

ppost.setPlayers(STRING...); //tworzy liste zawierajaca NAZWY graczy
ppost.getPacket(ScoreboardTeamType.ADD); // Dodaje gracz-a/y z listy ustawionej powyzej do TEAMU

