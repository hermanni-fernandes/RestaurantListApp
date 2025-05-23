# Ravintolasovellus (Tehtävät 2, 3 ja 4 valmiit – Tehtävä 5 kesken virheiden vuoksi)

Tämä on yksinkertainen Android-sovellus, joka näyttää listan ravintoloista ja mahdollistaa asiakasarvioiden tarkastelun ja lisäämisen. Sovellus on toteutettu [Jetpack Compose](https://developer.android.com/jetpack/compose) -kirjastolla ja se toimii nykyaikaisella ViewModel- ja navigaatiorakenteella.

## Tehdyt osat

### Tehtävä 2: Ravintolalistauksen toteutus
- Lista esimerkkiravintoloista kortteina
- Kortissa näkyy nimi, arvosana, arvioiden määrä, ravintolan tyyppi, hintaluokka, osoite ja aukiolotilanne väreillä
- Ravintolakuvan esitys (`burger.png`)

### Tehtävä 3: Navigaatiorakenne
- Navigointi ravintolakortista yksittäiseen ravintolanäkymään
- Käytössä `NavHost` ja ravintolan nimi siirtyy navigoinnissa mukana

### Tehtävä 4: Kommenttien näyttö ja lisääminen
- Ravintolanäkymässä näkyy lista asiakasarvioista (tähtiarvio, teksti, päiväys)
- Arvioita voi lisätä ja poistaa
- Lomakekomponentit manuaaliselle tähtiarvion valinnalle ja tekstin syötteelle

## Käytetyt teknologiat

- **Kieli:** Kotlin
- **UI:** Jetpack Compose + Material 3
- **Tilanhallinta:** ViewModel + `mutableStateOf`
- **Navigointi:** Jetpack Navigation Compose
- **Kuva:** Staattinen kuva `burger.png` drawable-kansiossa
- **Arkkitehtuuri:** MVVM-rakenne

##  Nykyiset ongelmat (Tehtävä 5 ei valmistunut)

Projektin viimeisessä vaiheessa (Hiltin lisääminen) ilmeni seuraavat kriittiset virheet:

- **Hilt-prosessointi epäonnistuu:**  
  `Unsupported metadata version. Check that your Kotlin version is >= 1.0`  
  → Tämä liittyy Kotlinin, Hiltin ja niiden välisen metadatan yhteensopimattomuuteen

- **Android Gradle Plugin -ongelmat:**  
  Käytetyt riippuvuudet (esim. `androidx.core:core-ktx:1.16.0`) vaativat vähintään Android Gradle Pluginin version `8.6.0`, mutta sen käyttö johtaa muihin vakaviin virheisiin eikä lataudu oikein

- **Ratkaisuyrityksiä tehty monta päivää:**  
  Useita `gradlew clean`, `--refresh-dependencies`, versioiden vaihtamisia ja uudelleenasennuksia on kokeiltu – tuloksetta.

## Miten sovellus toimii

1. Sovellus avautuu listaan esimerkkiravintoloista
2. Käyttäjä voi klikata ravintolakorttia ja siirtyä yksityiskohtaiseen näkymään
3. Näkymässä näkyvät:
   - Ravintolan tiedot
   - Lista asiakkaiden kommenteista
   - Mahdollisuus lisätä uusi kommentti (tähti + teksti)
4. Kommentit tallennetaan tilapäisesti ViewModeliin (ei pysyvää tallennusta)

## Sovelluksen käyttö

1. Avaa projekti Android Studiossa
2. Varmista, että `burger.png` on lisätty `res/drawable/`-kansioon
3. Aja sovellus emulaattorissa tai fyysisellä Android-laitteella

> Huom! Projektia ei voi tällä hetkellä kääntää valmiiksi Hiltin virheiden vuoksi, vaikka toiminnallisuus on käytännössä tehty.

## Kehitysideoita jatkoon

- Kommenttien tallennus pysyvästi (Room, Firebase, tms.)
- Ravintolahaku ja -suodatus
- Kuvien lataus verkosta (esim. Coil)
- Aukioloaikojen tarkempi näyttö
- Navigaation ja tilanhallinnan refaktorointi erillisiin paketteihin

## Lisenssi

Tämä projekti on tehty opiskelutarkoitukseen ja vapaasti käytettävissä oppimisharjoituksena.

---

> Projekti toteutettu Jetpack Compose -harjoituksena. Tehtävät 2–4 on valmiit ja toiminnallisuus kommenttien lisäämiseen asti on tehty. Tehtävä 5 jäi teknisten esteiden vuoksi kesken (Hilt + Gradle + Kotlin -yhteensopivuusongelmat).

