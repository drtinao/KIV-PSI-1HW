# KIV-PSI-1HW
First HW assigned in KIV/PSI
1) Obsah repozitáře

Repozitář obsahuje ve složce "multicast_client" zdrojové kódy klienta a celý projekt z IntelliJ IDEA. Obsah složky "multicast_server" analogicky tvoří zdrojové kódy testovacího serveru a odpovídající projekt. V rootu složky se rovněž nachází soubor "multicast_client.jar" a "multicast_server.jar". Uvedené soubory slouží pro uživatelsky přívětivé spuštění klienta, resp. serveru.

2) Algoritmus použitý v programu

Před programováním bylo zapotřebí zopakovat si principy multicastu v rámci sítě. Multicast slouží k odesílání dat v poměru 1:N. Tedy jeden server odesílá data N klientům. K zasílání / přijímání dat pomocí multicastu je potřeba znát zejména IP adresu multicastu a port.

2.a) IP adresa multicastu

Multicastová adresa je v podstatě IP adresa, na kterou jsou zasílány serverem data, která mají obdržet klienti. Taková data pak může obdržet jakýkoliv uzel v síti, který se k jejich odběru v rámci sítě přihlásí. Příjemci dat jsou sdružováni v multicastových skupinách, ke kterým se uzel může v případě zájmu připojit či z nich odejít.

V IPv4 se multicastové IP adresy nachází v rozsahu 224.0.0.0 - 239.255.255.255.

3) Konstanty umožňující změnu chování programu - klient

Změna níže uvedených konstant, jež se nachází ve zdrojovém kódu klienta, povede ke změně chování programu odpovídajícím způsobem.

3.a) Změna multicast adresy

Adresa multicastu je definována konstantou "MULTICAST_ADDR", jež se nachází ve třídě "Logic". Pro správnou funkčnost je samozřejmě nutné adresu změnit i na straně serveru a zároveň se držet v rozmezí multicastových adres.

3.b) Změna multicast portu

Port je definován konstantou "MULTICAST_PORT", třída "Logic". Samozřejmě je žádoucí změnit i v serveru.

3.c) Změna velikosti bufferu, maximální délky zprávy

Velikost bufferu pro přijímané zprávy je definována konstantou "MAX_MES_BYTES", třída "Logic". V případě použití dodaného serveru je žádoucí mít velikost bufferu shodnou se serverem.

3.d) Změna klíčového slova pro ukončení provozu

Klíčové slovo, které po přijetí ukončí činnost klienta, je definováno konstantou "STOP_MULTICAST_WORD" - třída "Logic".

4) Konstanty umožňující změnu chování programu - server

4.a) Změna multicast adresy

Popis viz sekce s klientem. 

4.b) Změna multicast portu

Popis viz sekce s klientem.

4.c) Změna velikosti bufferu, maximální délky zprávy

Velikost bufferu pro přijímané zprávy je definována konstantou "MAX_MES_BYTES", třída "User".

4.d) Změna defaultní zprávy

Defaultní zpráva, jež je odeslána, pokud uživatel nevyplní jím požadovanou, je definována konstantou "DEFAULT_MESSAGE", třída "User".

5) Spuštění aplikace, návod k použití

Ke spuštění je potřeba mít nainstalovanou Javu. Testování probíhalo na počítači s MS Windows 10 Pro, OpenJDK v11.0.12.

Pro vyzkoušení aplikace provedeme spuštění klientů na více strojích, které se nacházejí na stejném segmentu sítě. Spuštění klienta provedeme příkazem "java -jar multicast_client.jar".

Po spuštění klientů na libovolném stroji v rámci segmentu zapneme server příkazem "java -jar multicast_server.jar" a po vyzvání zadáme zprávu, jež chceme odeslat prostřednictvím multicastu. Případně stiskneme enter pro použití defaultní zprávy. Server byl vytvořen pouze pro účely testování a umožňuje pouze jednorázové odeslání zprávy - pro odeslání více zpráv je tedy nutné server pustit znovu. Klient běží do doby než obdrží slovo definové konstantou "STOP_MULTICAST_WORD".
