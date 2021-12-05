# L04

Ćwiczenie 3 + 4 - cz. II
A. Przygotować aplikację z ekranem głównym i ekranami dodatkowymi o następujących cechach i zawartości (patrz rys.):

1. Aplikacja składa się z głównej aktywności i fragmentów. Fragmenty są elementami wypełniającymi zawartość ekranu aktywności. Nawigacja realizowana jest frameworkiem Navigation.

2. Ekran aktywności oprócz fragmentu wypełniającego cały ekran zawiera na dole element nawigacji BottomNavigation, którym przełącza się do poszczególnych ekranów (fragmentów).

3. Dolny pasek pozwala wyświetlać co najmniej 4 różne ekrany/fragmenty
- Ekran główny zawierający tekst przywitania, obrazek i poniżej dane jakieś informacyjne (np. dane o autorze). Obrazek i teksty są wybierane/ustawiane w innych ekranach.
- Drugi ekran jest fragmentem z zakładkami zawierające kilka opcji do ustawień.
- Trzeci ekran wyświetla aktualnie wybrany obrazek oraz obrazki do wyboru (patrz opis niżej).
- Czwarty ekran jest dowolnie do zdefiniowania przez autora aplikacji.

4. Drugi fragment/ekran powinien zawierać w zakładkach różne opcje do ustawień.
- Przykładowa zawartość zakładek::
  - Jedna zakładka zawiera różne dane personalne (imię, nazwisko, nr telefonu, itp.).
  - Jedna zakładka zawiera ustawienia graficzne - np. kolor tła ekranu głównego, kolor czcionki, itp.
  - Trzecia zakładka zawiera dowolne elementy wymyślone przez autora aplikacji.
- Te ustawienia powinny być zapamiętane w pamięci SharedPreferences po naciśnięciu przycisku Save.
- Bez zapamiętania ale po przejściu/powrocie do głównego ekranu te ustawienia są zastosowane - zastosować przekazywanie danych miedzy fragmentami poprzez słuchacze dla zwracanych rezultatów/danych.

5. Trzeci ekran pozwala ustawić jako aktualny (lub zaniechać tego) obrazek wybierany z kilku - patrz rys.
  Może być zrealizowany na kilka sposobów:
  
  a)
  jako aktywność z obrazkiem i fragmentem z view pagerem
  
  kliknięcie obrazka zmienia aktualny na ten kliknięty
  
  b)
  jako aktywność składająca się z dwóch fragmentów
  
  górny fragment zawiera wybrany/aktualny obrazek, a dolny zawiera view pager, którym można przeglądać i wybierać obrazki
  
  kliknięcie obrazka zmienia aktualny na ten kliknięty
  
  c)
  jako aktywność z fragmentem zawierającym fragment potomny (child)
  
  funkcjonalność taka jak powyżej
  
  d)
  jako fragment (podobnie jak poprzednie) wywoływany wewn. aktywności głównej - czyli pasek nawigacji pozostaje
  
  realizacja wewnątrz z jednym fragmentem lub dwoma
  
  funkcjonalność taka jak powyżej
  
  Uwaga: opcja a) i b) będą trochę niżej punktowane.

6. Wybrany obrazek jest wyświetlany w ekranie głównym/startowym - wystarczy przekazać nr obrazka i użyć przy tworzeniu widoku

7. Nawigacja (wyświetlanie/przechodzenie do poszczególnych ekranów) jest zrobiona z użyciem frameworka Navigation
- zdefiniować graf nawigacji (navigation graph)
- zdefiniować destynacje i akcje
- użyć komponentów implementujących navigation host
- użyć nawigation controller do wywoływania kolejnych ekranów/destynacji

8. Aplikacja korzysta z pamięci SharedPreferences
- przy starcie czyta z niej ustawienia,
- na żądanie zapisuje ustawienia.
