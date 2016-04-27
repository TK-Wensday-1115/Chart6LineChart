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

|ChartFactory|
|:---|
|public IChart createHistoryChart(String name, String xAxisLabel, String yAxisLabel, String yAxisUnit)|

|IChart|
|:-----|
|public int registerNewLine(String label)|
|public void addNewEntry(int lineId, double value, Date time)|

### 2.2 Wykorzystane biblioteki
Do generowania i wyświetlania wykresu użyta zostanie biblioteka JFreeChart.

### 2.3 Diagram klas
![Diagram](http://i.imgur.com/kUbZgQ3.png)
