#  валюта Aplikacja Walutowa

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform: Android">
  <img src="https://img.shields.io/badge/Language-Java-blue.svg" alt="Language: Java">
  <img src="https://img.shields.io/badge/API-24%2B-orange.svg" alt="Min API Level: 24">
  <img src="https://img.shields.io/badge/License-MIT-lightgrey.svg" alt="License: MIT">
</p>

Aplikacja na platformę Android do sprawdzania aktualnych kursów walut, przeliczania kwot oraz przeglądania danych historycznych na interaktywnym wykresie. Projekt został zrealizowany w języku **Java** z wykorzystaniem architektury **MVVM** i klasycznego systemu widoków (XML).

---

## 📱 Podgląd Aplikacji

| Logowanie | Rejestracja | Ekran Główny |
| :---: |:---:|:---:|
| <img src="https://github.com/woy2202/AplikacjaWalutowa/raw/master/app/screenshots/Logowanie.png" alt="Ekran Logowania" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Rejestracja.png" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Strona%20g%C5%82%C3%B3wna.png" alt="Ekran Główny z listą walut" width="200"/> |

| Przelicznik Walut | Historia Kursu | Baza Danych Firebase |
| :---: |:---:|:---:|
| <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Przelicznik.png" alt="Ekran Przelicznika Walut" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Historia%20kursu.png" alt="Ekran Historii Kursu" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/firebase.png" alt="Potwierdzenie użytkownika w Firebase" width="200"/> |

<br>

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
