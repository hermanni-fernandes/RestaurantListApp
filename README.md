# Ravintolasovellus

Tämä on yksinkertainen Android-sovellus, joka näyttää listan ravintoloista ja mahdollistaa asiakasarvioiden lisäämisen Jetpack Compose -kirjaston avulla.

## Ominaisuudet

- Näyttää listan esimerkkiravintoloista
- Ravintolakortti sisältää:
  - Nimi
  - Arvosana (tähtinä ja numerona)
  - Arvostelujen määrä
  - Ravintolan tyyppi
  - Hintaluokka
  - Osoite
  - Avoinna / Sulkeutumassa -teksti väreillä
  - Kuvana hampurilaiskuva (`burger.png`)
- Klikattavat kortit, jotka vievät ravintolan kommenttisivulle
- Kommenttinäkymä sisältää:
  - Ravintolan tiedot
  - Lista asiakkaiden kommenteista (tähtiarvio, teksti, päiväys)
  - Kommentin poistaminen
  - Plus-painike, josta voi lisätä uuden kommentin
    - Tähtiarvion valinta (1–5)
    - Kommentin kirjoittaminen

## Navigointi

- Skaalautuva `NavHost`-pohjainen navigaatiorakenne
- Navigointi toimii ravintolan nimen perusteella
- Jaettu ViewModel (`RestaurantViewModel`) mahdollistaa kommenttidatan hallinnan molemmissa näkymissä

## 🛠Käytetyt teknologiat

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material 3](https://m3.material.io/)
- [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Coil (valmiina mutta ei käytössä)](https://coil-kt.github.io/coil/)

## Käyttöönotto

1. Avaa projekti Android Studiossa
2. Lisää `burger.png` `res/drawable/`-kansioon
3. Aja sovellus emulaattorissa tai fyysisellä laitteella

## Kehitysideoita

- Kommenttien tallennus pysyvästi (Room, Firebase, tms.)
- Hakutoiminto ja suodatus ravintolalistaan
- Ravintolakuvan lataaminen verkosta (Coil)
- Lisää ravintolan tietoja (aukioloajat, arvostelut, tms.)
- Navigaation eriyttäminen omaksi `restaurantFeature`-paketikseen

## Lisenssi

Tämä projekti on vapaasti käytettävissä opiskelukäyttöön ja omiin kokeiluihin.

---

> Projekti toteutettu oppimisharjoituksena Jetpack Composeen ja moderniin Android-kehitykseen tutustumiseksi.
