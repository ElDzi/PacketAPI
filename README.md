# PacketAPI

- Simple classes to management TabList ! - Coming soon (- full documentation) !
- Proste klasy do zarzadzania TabList'ą ! - Już Wkrótce (- pełna dokumentacja) !

![ScreenShot](http://i.imgur.com/VfRjyl7.png)

# Pakiety na wlasna reke ?

Jesli nie chcesz uzywac pakietow na wlasna reke to zastosowanie jest bardzo proste

PacketSend ps = new PacketSend(PLAYER); //nowy obiekt
ps.sendpacket(OBJECT); //wyslij pakiet

# PacketPlayOutScoreboardTeam ppost = new PacketPlayOutScoreboardTeam(STRING); //nowy obiekt TEAM

ppost.getPacket(ScoreboardTeamType.CREATE); //stworz TEAM

ppost.getPacket(ScoreboardTeamType.DELETE); //usun TEAM

ppost.setPrefix(STRING); // ustaw prefix dla TEAMU
ppost.setSuffix(STRING); // ustaw suffix dla TEAMU
ppost.getPacket(ScoreboardTeamType.UPDATE); //TEAM ulegnie aktualizacji glownie PREFIX i SUFFIX ustawiony powyzej ...

ppost.setPlayers(STRING...); //tworzy liste zawierajaca NAZWY graczy
ppost.getPacket(ScoreboardTeamType.ADD); // Dodaje gracz-a/y z listy ustawionej powyzej do TEAMU

ppost.setPlayers(STRING...); //tworzy liste zawierajaca NAZWY graczy
ppost.getPacket(ScoreboardTeamType.REMOVE); // Usuwa gracz-a/y z listy ustawionej powyzej z TEAMU

# EXAMPLE:
onCommand(...)

Player player = (PLayer) sender;

PacketSend ps = new PacketSend(player);

PacketPlayOutScoreboardTeam ppost = new PacketPlayOutScoreboardTeam("test");
ps.sendPacket(ppost.ppost.getPacket(ScoreboardTeamType.CREATE));

ppost.setPrefix("&2");
ps.sendPacket(ppost.getPacket(ScoreboardTeamType.UPDATE));

ppost.setPlayers(player.getName());
ps.sendPacket(ppost.getPacket(ScoreboardTeamType.ADD));

# PacketPlayOutPlayerInfo ppopi = PacketPlayOutPlayerInfo(STRING); //nowy obiekt PLAYERINFO
ppopi.getPacket(PlayerInfoType.CREATE); //tworzy PLAYERINFO
ppopi.getPacket(PlayerInfoType.DELETE); //usuwa PLAYERINFO

or/lub
PacketPlayOutPlayerInfo ppopi = PacketPlayOutPlayerInfo(STERING, BOOLEAN, INT);
ppopi.getPacket(PlayerInfoType.OTHER); //tworzy badz usuwa precyzyjniejsze PLAYERINFO

# EXAMPLE:

onCommand(...)

Player player = (PLayer) sender;

PacketSend ps = new PacketSend(player);
PacketPlayOutPlayerInfo ppopi = PacketPlayOutPlayerInfo("test");
ps.sendPacket(ppopi.getPacket(PlayerInfoType.CREATE)); //tworzy PLAYERINFO

or/lub
