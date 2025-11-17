## Bin Bank App - тестовое задание
Мобильное приложение для Android, предназначенное для получения информации о банковских картах по BIN-номеру. BIN (Bank Identification Number) - это первые 6 цифр номера банковской карты, которые идентифицируют банк-эмитент.

## Функциональность

- Поиск информации о карте - ввод BIN-номера и получение детальной информации о банковской карте
- История поиска - сохранение и отображение предыдущих запросов
- Очистка истории - возможность полной очистки истории поиска

## Технологический стек

- **Язык**: Kotlin
- **Архитектура**: MVVM + Clean Architecture
- **UI**: Jetpack Compose
- **DI**: Koin
- **Сеть**: Retrofit 2 + OkHttp3
- **Локальная база данных**: Room
- **Асинхронность**: Kotlin Coroutines + Flow

## Особенности реализации

Приложение реализует паттерн MVVM (Model-View-ViewModel) и соответствует принципам чистой архитектуры. Пользовательский интерфейс построен с использованием Jetpack Compose. Для хранения истории поиска используется локальная база данных Room. Сетевое взаимодействие с API BIN-номеров построено на основе Retrofit 2, а низкоуровневые сетевые операции выполняются с помощью OkHttp3.

<img width="640" height="1280" alt="image" src="https://github.com/user-attachments/assets/09e365c2-f796-49bc-936c-9aeec746662a" />
<img width="640" height="1280" alt="image" src="https://github.com/user-attachments/assets/1329be55-3a3f-499f-b261-c1fd20039ca3" />
<img width="640" height="1280" alt="image" src="https://github.com/user-attachments/assets/1c68bc72-a00e-478b-8717-bced3e34b9e7" />
<img width="640" height="1280" alt="image" src="https://github.com/user-attachments/assets/cc894547-0594-43f4-a1b7-9ee0267e8f26" />
<img width="640" height="1280" alt="image" src="https://github.com/user-attachments/assets/b15a8035-a0a6-4b53-9e55-43615f26be33" />




