Aplikacja Walutowa


Aplikacja na platformę Android do sprawdzania aktualnych kursów walut, przeliczania kwot oraz przeglądania danych historycznych na interaktywnym wykresie. Projekt został zrealizowany w języku **Java** z wykorzystaniem architektury **MVVM** i klasycznego systemu widoków (XML).

---

## 📱 Podgląd Aplikacji

| Logowanie | Rejestracja | Ekran Główny |
| :---: |:---:|:---:|
| <img src="https://github.com/woy2202/AplikacjaWalutowa/raw/master/app/screenshots/Logowanie.png" alt="Ekran Logowania" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Rejestracja.png" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Strona%20g%C5%82%C3%B3wna.png" alt="Ekran Główny z listą walut" width="200"/> |

| Przelicznik Walut | Historia Kursu | Baza Danych Firebase |
| :---: |:---:|:---:|
| <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Przelicznik.png" alt="Ekran Przelicznika Walut" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/Historia%20kursu.png" alt="Ekran Historii Kursu" width="200"/> | <img src="https://github.com/woy2202/AplikacjaWalutowa/blob/master/app/screenshots/firebase.png" alt="Potwierdzenie użytkownika w Firebase" width="200"/> |



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
  Jasne, rozumiem. Chcesz inaczej zaprezentować zrzuty ekranu, aby lepiej pokazać cały proces – od interfejsu użytkownika aż po potwierdzenie w backendzie (Firebase). To świetny pomysł, który dobrze świadczy o kompletności projektu!

Przygotowałem nową wersję pliku README.md, która uwzględnia wszystkie sześć zrzutów ekranu w jednej, czytelnej tabeli. Struktura i reszta opisów pozostają dopracowane tak jak poprzednio.

Krok 1: Zastąp zawartość pliku README.md
Proszę, skopiuj cały poniższy kod i wklej go do swojego pliku README.md w Android Studio.

Markdown

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
| <img src="link_do_screena_logowanie.png" alt="Ekran Logowania" width="200"/> | <img src="link_do_screena_rejestracja.png" alt="Ekran Rejestracji" width="200"/> | <img src="link_do_screena_lista.png" alt="Ekran Główny z listą walut" width="200"/> |

| Przelicznik Walut | Historia Kursu | Baza Danych Firebase |
| :---: |:---:|:---:|
| <img src="link_do_screena_przelicznik.png" alt="Ekran Przelicznika Walut" width="200"/> | <img src="link_do_screena_wykres.png" alt="Ekran Historii Kursu" width="200"/> | <img src="link_do_screena_firebase.png" alt="Potwierdzenie użytkownika w Firebase" width="200"/> |

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

## 🔌 Użyte API

Aplikacja do pobierania danych o kursach walut wykorzystuje publicznie dostępne [NBP Web API](https://api.nbp.pl/).

---

## 🚀 Konfiguracja i Uruchomienie

Aby uruchomić projekt lokalnie, postępuj zgodnie z poniższymi krokami:

1.  **Sklonuj repozytorium**
  ```bash
  git clone [https://github.com/TWOJA_NAZWA_UŻYTKOWNIKA/AplikacjaWalutowa.git](https://github.com/TWOJA_NAZWA_UŻYTKOWNIKA/AplikacjaWalutowa.git)
  ```
2.  **Otwórz projekt** w najnowszej stabilnej wersji Android Studio.

3.  **Skonfiguruj Firebase**
  * Projekt używa Firebase Authentication. Plik konfiguracyjny `google-services.json` jest celowo ignorowany przez Git (w pliku `.gitignore`) ze względów bezpieczeństwa.
  * Musisz utworzyć własny projekt w [Konsoli Firebase](https://console.firebase.google.com/).
  * Dodaj do niego aplikację Android, używając nazwy pakietu: `com.example.aplikacjawalutowa`.
  * W sekcji **Authentication** -> **Sign-in method** włącz dostawcę **E-mail/Hasło**.
  * Pobierz wygenerowany plik `google-services.json` i umieść go w folderze `app/` swojego projektu.

4.  **Zbuduj i uruchom aplikację.**

---

## ✍️ Autorzy

Jola Jabłonowska
Wojciech Gochnio

---

## 📄 Licencja

Projekt udostępniony na licencji MIT. Zobacz plik `LICENSE`, aby uzyskać więcej informacji.


