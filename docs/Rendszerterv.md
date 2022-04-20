# Rendszerterv

## 1. A rendszer célja
---

A rendszer célja, hogy egy regisztrált felhasználó egyszerűen és átláthatóán tudja használni az előtte megjelenő programot. 
A filmek listázásával és ésszerű szűrésével megtalálhatja a számára várt eredményt. Megtekintheti az rendezőket illetve a szereplőket,
valamint nem utolsó sorban a film értékelését.

## 2. Projektterv
---

Az átlag felhasználók jogköre megegyezik, mindegyik felhasználónak joga van listázni a filmeket, módosítani/törölni a fiókját, a felhasználók értékelhetik a filmeket.
Ezen kívül az ADMIN felhasználó jogköre kezelni a felhasznalókat és lehetőségük van felvenni új filmeket.

## 3. Üzleti folyamatok modellje
---

![Image]()

## 4. Követelmények
---
### Funckionális Követelmények:
    - Létező adatbázis
    - Belépési lehetőség
    - Felhasználó kezelés
    - DML műveletek a filmekre
    - Értékelési lehetőség

### Nem Funkcionális Követelmények:
    - A programnak felhasználó barátnak kell lennie!
    - Át kell mennie a unit teszteken!
    - Gyorsnak kell lennie!
    - Pontosnak kell lennie!

## 5. Funkcionális terv
---
### Rendszerszereplők:
    - Alapszintű felhasználó
    - Admin jogosultsággal rendelező felhasználó

* Alapszintű felhasználó
    - Be tud regisztrálni
    - Be tud lépni
    - Tudja módosítani az adatait
    - Képes listázni a filmeket
    - Képes értékelést adni egy filmről

* Admin jogosultsággal rendelező felhasználó
    - Be tud lépni
    - Képes regisztrálni
    - Tudja kezelni a felhasználókat
    - Fel tud venni új filmeket az adatbázisba
    - Tudja módosítani a meglévő filmeket
    - Tudja törölni a meglévő filmeket

### Menü-hierarchiák:

* Main menu:
    - Lapozási lehetőség az adataim, értékeléseim és filmek között.
    - Szűrési filterek
    - Listázási lehetőség

## 6. Fizikai környezet
---
### Fejlesztői környezet:
    - InteliJ
    - Git
    - Android Studio

### Futási környezet
    A program futtatásához szükséges egy android rendszerrel rendelkező okostelefonra.

### Specifikáció:
    Android rendszerrel rendelkező okostelefon.

![Image]()

## 7. Architectúrális terv
--
Felhasznált technológiák:
 1. MySQL:
    A MySQL kezeli az adatbázisunkat, itt tároljuk el az adatokat.
    A program "query"-n keresztül kommunikál az adatbázissal, lekérdezéseket, müveleket végez el benne.
    XAMPP-al vezérelt, localhost-on elérhető.
 2. InteliJ
 3. Android Studio
 
## 8. Teleptési terv
--
A program lokálisan működik.
A futtatáshoz szükség van MySQL elérésre, illetve internetkapcsolatra.
A felhasználónak le kell töltenie az alkalmazást a saját telefonjára, biztosítania kell a localhost elérését XAMPP-on keresztül.
 
## 9. Karbantartási terv
--
A rendszer karbantartása időszakos lesz. Hibákat a felhasználó egy hibabejelentő gomb segítségével tudja majd jelezni a fejlesztők felé.
A hibabejelentésnél a felhasználó leírhatja mi a hiba és opcionálisan megadhatja a folyamatot amivel a hiba történt (opcionálisan képernyőkép feltöltése). Az üzenetet csak a fejlesztők látják.
 
## 10. Adatbázis terv
--
![Image]()
 
A program felhasználja a megfelelő, előre létrehozott adatbázist.
 
## 11. Implementációs terv
---
Android Studio: A GUI felület Java nyelven fog készülni. Ezeket a technológiákat amennyire csak lehet külön fájlokba írva készítjük, és úgy fogjuk egymáshoz csatolni a jobb
átláthatóság, könnyebb változtathatóság, és könnyebb bővítés érdekében. A programban megjelenítjük és alkalmazzuk az adatbázis kezelést emellett alkalmazzuk a Java Spring
adott lehetőségeit.
 
## 12. Tesztterv
---
## Tesztelési eljársok:
 
**Alfa teszt:** Az alfa tesztet a fejlesztők végzik. A alfa teszt célja, hogy teszteljük a már meglévő funkciókat, hogy azok az elvártnak megfelelően működnek-e. A teszt akkor tekinthető sikeresnek, ha különböző GUI felületeken minden funkció megfelelően működik.
 
## Tesztelendő funkciók
**Backend Service:** Képesnek kell lennie több klienst egyidőben kiszolgálni. Képesnek kell lennie, az adatbázisban műveleteket végezni (hozzáadni, törölni, módosítani).
 
## Architekturális terv
A kommunikáció a Java fordítóprogramján keresztül történik.
 
## Unit Tesztek
A program minden különálló részének át kell mennie kivétel nélkül az elé tárt Unit Teszteken.
 
### Adatbázis:
A rendszer adatainak tárolásához szükséges egy Adatbázis, a rendszerhez MySql-t alkalmazunk.
