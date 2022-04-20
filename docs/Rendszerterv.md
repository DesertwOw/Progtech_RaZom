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