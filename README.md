# Wykres Liniowy
## 1. Wizja
### 1.1 Założenia
Komponent ma za zadanie wyśiwetlać dane pochodzące z wielu źródeł na jednym wykresie. Każda linia na wykresie będzie przedstawiać wartość danej pochodzącej z jendgo źródła w stosunku do upływającego czasu. Źródła będą dostarczać pakiety danych wejściowych, na podstawie których uaktualniany będzie wykres.

### 1.2 Inicjalizacja wykresu
Przy tworzeniu wykresu wymagane będzie podanie jednostki danych przedstawianych na osi wartości. Każde nowe źródło danych, przed rozpoczęciem przesyłania wartości, będzie musiało zarejestrować nową linię na wykresie, podając przy tym tekst, który będzie wyświetlony na legendzie wykresu. 

### 1.3 Format danych wejściowych
Pakiet danych wejściowych będzie zawierał:
- identyfikator linii
- wartość danej
- czas pomiaru

### 1.4 Inne funkcjonalności
- długość historii - konfigurowalny zakres czasu, z którego będą przedstawiane dane,
- skalowanie wartości - dynamiczne skalowanie wartości danych,
- kolory linii - każda kolejna zarejestrowana linia na wykresie będzie miała inny kolor i wpis w legendzie,
- obsługa danych przychodzących nie w kolejności,
- obsługa przestojów w przesyłaniu danych.

## 2. Projekt
### 2.1 API
Z punktu widzenia użytkownika modułu, dostępna będzie fabryka tworząca wykresy.

|HistoryChartFactory|
|:---|
|public HistoryChart createNew(String name, String xAxisLabel, TimeUnit timeUnit, String yAxisLabel, String yAxisUnit)|

|IHistoryChart|
|:-----|
|public int registerNewLine(String label)|
|public void addNewEntry(int lineId, double value, Date time)|

### 2.2 Wykorzystane biblioteki
Do generowania i wyświetlania wykresu użyta zostanie biblioteka JFreeChart.

### 2.3 Diagram klas
![Diagram](http://i.imgur.com/kUbZgQ3.png)

## 3. Przykład użycia
### 3.1 Import biblioteki
Aby zacząć używać wykresu, należy do projektu zaimportować bibliotekę HistoryChart-1.0.0.jar. Zbudowaną bibliotekę wraz z wymaganymi zależnościami można znaleźć w folderze "Source/out/artifacts/HistoryChart_jar/".
### 3.2 Tworzenie nowego wykresu
Biblioteka HistoryChart dostarcza klasę fabryki do tworzenia obiektów wykresu. Aby stworzyć nowy wykres należy użyć klasy HistoryChartFactory.
```java
HistoryChart chart = 
    HistoryChartFactory.createNew("History Chart Example", "Time", TimeUnit.Second, "Speed", "m/s");
```

### 3.3 Osadzanie wykresu w oknie
Aby osadzić stworzony wykres w oknie aplikacji, należy dodać stworzoną instancję do dowolnym miejscu drzewa komponentów aplikacji.
```java
JFrame frame = new JFrame("History Chart Example");
frame.add(chart);
```
### 3.4 Dodawanie nowej serii dancyh
Stworzony wykres jest z początku pusty i nie ma jeszcze możliwości dodawania do niego danych. 
![Pusty wykres](http://i.imgur.com/AJuPGFW.png)

Aby to osiągnąć, należy najpiew dodać piewszą serię danych do wykresu.
```java
int lineId = chart.registerNewLine("Series 1");
```
Wynik wywołania fuknkcji registerNewLine jest identyfikatorem nowo stworzonej serii i będzie potrzebny do dodawania dancyh do tej serii.
### 3.5 Dodawanie dancyh do serii
Po stworzeniu pierwszej serii danych można zacząć dodawać do niej wartości. Wartości nie muszą być dodawane w chronologicznie, ale każda wartość musi posiadać skojarzony a nią obiekt klasy Date reprezentujący punkt na osi czasu w której ma się pojawić na wykresie.
```java
chart.addNewEntry(lineId, value, new Date());
```
![Wykres z nową serią](http://i.imgur.com/zYpdr4x.png)
### 3.6 Zarządanie zakresem wyświetlanych danych
Wykres posiada skojarzoną kontrolkę do zarządzania zakresem czasu, z którego dane mają być wyświetlane. Zatwierdzenie zmiany wyswietlanego zakresu danych odbywa się przez wcisnięcie przycisku "Apply".
![Zarządzanie historią](http://i.imgur.com/p74Gwpc.png)
![Zmieniony zakres](http://i.imgur.com/bi0IDNi.png)
