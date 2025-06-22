Aplikacja na platformę Android do sprawdzania aktualnych kursów walut, przeliczania kwot oraz przeglądania danych historycznych na interaktywnym wykresie. Projekt został zrealizowany w języku **Java** z wykorzystaniem architektury **MVVM** i klasycznego systemu widoków (XML).


## 📱 Podgląd Aplikacji

| Logowanie | Rejestracja | Ekran Główny |
| :---: |:---:|:---:|
| <img src="link_do_screena_logowanie.png" alt="Ekran Logowania" width="200"/> | <img src="link_do_screena_rejestracja.png" alt="Ekran Rejestracji" width="200"/> | <img src="link_do_screena_lista.png" alt="Ekran Główny z listą walut" width="200"/> |

| Przelicznik Walut | Historia Kursu | Baza Danych Firebase |


## ✨ Główne Funkcje

* 🔑 **Uwierzytelnianie Użytkownika:** Pełny, niestandardowy przepływ logowania i rejestracji przy użyciu e-maila i hasła, oparty na **Firebase Authentication**.
* 📈 **Lista Kursów Walut:** Aktualna tabela kursów walut (Tabela A) pobierana z oficjalnego **NBP Web API**. Lista wspiera odświeżanie gestem "pull-to-refresh".
* 💸 **Przelicznik Walut:** Ekran umożliwiający szybkie przeliczanie kwot pomiędzy dowolnie wybranymi walutami z listy.
* 📊 **Historia Notowań:** Interaktywny i ulepszony wizualnie wykres liniowy prezentujący historię kursu wybranej waluty z ostatnich 30 dni.
* 💾 **Lokalny Cache:** Aplikacja wykorzystuje bazę danych **Room** do przechowywania ostatnio pobranych kursów, co umożliwia dostęp do danych w trybie offline i przyspiesza start aplikacji.

---

## 🛠️ Stos Technologiczny

* **Język:** Java
* **Architektura:** MVVM (Model-View-ViewModel)
* **Android Jetpack:**
    * **UI:** View Binding, AppCompat, Material Design 3 Components
    * **Architektura:** ViewModel, LiveData
    * **Dane:** Room Persistence Library
* **Komunikacja Sieciowa:**
    * Retrofit 2 - do obsługi zapytań REST API
    * Gson - do mapowania obiektów JSON
* **Baza Danych:**
    * Firebase Authentication - do zarządzania użytkownikami
* **Wykresy:**
    * MPAndroidChart - do tworzenia interaktywnych wykresów

---

## 🏗️ Architektura Projektu

Aplikacja została zbudowana w oparciu o zalecany przez Google wzorzec MVVM, oddzielając warstwę danych od logiki interfejsu użytkownika.

<details>
  <summary>Zobacz strukturę folderów</summary>
