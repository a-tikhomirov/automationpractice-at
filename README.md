# Проект автоматизации тестирования сайта automationpractice.com

Проект был сделан в рамках курса "Java для тестировщиков".  
Тесты написаны для сайта http://automationpractice.com/

## Сводка
  
  - [Требования](#Требования)
    - [Установка JDK](#Установка-JDK)
    - [Установка Maven](#Установка-Maven)
  - [Скачивание и запуск проекта](#Скачивание-и-запуск-проекта)
  - [Информация о проекте](#Информация-о-проекте)
    - [Информация по тестам](#Информация-по-тестам)
    - [Используемые библиотеки](#Используемые-библиотеки)
    - [Используемые плагины](#Используемые-плагины)
  - [Автор](#Автор)

## Требования

Для запуска проекта без среды разработки IDEA понадобится:

1. JDK 1.8
2. Maven 3.6.3
3. Установленный браузер Google Chrome / Устновленный браузер Mozilla Firefox

### Установка JDK

- Для Windows:

    Скачать Open JDK: [x32 jdk8u252-b09.1](https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u252-b09.1/OpenJDK8U-jdk_x86-32_windows_hotspot_8u252b09.msi) / [x64 jdk8u252-b09.1](https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u252-b09.1/OpenJDK8U-jdk_x64_windows_hotspot_8u252b09.msi)

    Запустить скачанный `.msi` файл.
    
    В процессе установки отметить пункты:  
      - Add to PATH  
      - Set JAVA_HOME variable  
    
    Проверить установку:
    
    ```cmd
    C:\>java -version
    openjdk version "1.8.0_252"
    OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_252-b09)
    OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.252-b09, mixed mode)
    
    C:\>echo %PATH%
    C:\Program Files\AdoptOpenJDK\jdk-8.0.252.09-hotspot\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Windows\System32\OpenSSH\;C:\Users\Nameless\AppData\Local\Microsoft\WindowsApps;
    
    C:\>echo %JAVA_HOME%
    C:\Program Files\AdoptOpenJDK\jdk-8.0.252.09-hotspot\
    ```

- Для Linux:
    
    ```ShellSession
    $ sudo apt install openjdk-8-jdk
    ```

    Добавить переменную окружения `JAVA_HOME`:
    
    ```ShellSession
    $ sudo update-alternatives --config java
    There is only one alternative in link group java (providing /usr/bin/java): /usr/lib/jvm/java-8-openjdk-arm64/jre/bin/java
    Nothing to configure.
    $ sudo nano /etc/environment
  
    PATH=...
    JAVA_HOME="/usr/lib/jvm/java-8-openjdk-arm64/jre/bin/java"
    ```

    Проверить успешное добавление переменной окружения:
    
    ```ShellSeesion
    $ source /etc/environment
    $ echo $JAVA_HOME
    /usr/lib/jvm/java-8-openjdk-arm64/jre/bin/java
    ```

- Для macOS:

    Скачать Open JDK: [jdk8u252-b09.1](https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/download/jdk8u252-b09.1/OpenJDK8U-jdk_x64_mac_hotspot_8u252b09.pkg)

    Запустить скачанный `.pkg` файл.
    
    Проверить установку:
    
    ```
    $ java -version
    openjdk version "1.8.0_252"
    OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_252-b09)
    OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.252-b09, mixed mode)
    ```
  
    Добавить переменную окружения `JAVA_HOME`:
    
    ```
    $ nano ~/.bash_profile    
  
    export JAVA_HOME=$(/usr/libexec/java_home)
    ```
  
    Проверить успешное добавление переменной окружения:
        
    ```ShellSeesion
    $ source ~/.bash_profile
    $ echo $JAVA_HOME
    /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Content/Home
    ```
        
### Установка Maven

Скачать Maven: [apache-maven-3.6.3-bin.zip](https://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)

Распаковать скачанный архив в желаемую директорию.

- Для Windows:
    
    Добавить в переменную окружения `PATH` путь до папки `bin`:
    
    ```cmd
    C:\>setx path "%path%;C:\Library\apache-maven-3.6.3\bin"
    ```  
    
    Перезапустить командную строку и проверить установку:
    
    ```cmd
    C:\>mvn -v
    C:\
    Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
    Maven home: C:\Library\apache-maven-3.6.3\bin\..
    Java version: 1.8.0_252, vendor: AdoptOpenJDK, runtime: C:\Program Files\AdoptOpenJDK\jdk-8.0.252.09-hotspot\jre
    Default locale: ru_RU, platform encoding: Cp1251
    OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
    ```

- Для Linux и macOS:
    
    Добавить в переменную окружения `PATH` путь до папки `bin`:
    
    ```ShellSession
    $ export PATH=path_to_apache/apache-maven-3.6.3/bin:$PATH
    ```
  
    Проверить установку:
    
    ```ShellSession
    $ mvn -v
    Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
    Maven home: /opt/apache-maven-3.6.3
    Java version: 1.8.0_252, vendor: Private Build, runtime: /usr/lib/jvm/java-8-openjdk-amd64/jre
    Default locale: ru_RU, platform encoding: UTF-8
    OS name: "linux", version: "5.3.0-51-generic", arch: "amd64", family: "unix"
    ```

## Скачивание и запуск проекта

Возможно скачать архив или клонировать проект при наличии git.

[Ссылка на скачивание архива](https://github.com/a-tikhomirov/automationpractice-at/archive/testng_based_fluent_pom.zip)

Команда для клонирования проекта:

```
$ git clone https://github.com/a-tikhomirov/automationpractice-at.git
$ cd automationpractice-at/
$ git checkout testng_based_fluent_pom
```

Для запуска тестового набора с браузером Chrome необходимо в командной строке перейти в директорию проекта и выполнить команду:

```
mvn clean test allure:serve
```

Для запуска тестового набора с браузером Firefox необходимо в командной строке перейти в директорию проекта и выполнить команду:

```
mvn clean test -Dbrowser="firefox" allure:serve
```

В результаты выполнения данной команды:
- При необходимости будут скачаны зависимости проекта;
- В параллельном режиме будут запущены 4 теста;
- По окончании тестов будет открыт браузер с отчетом по выполненным тестам.

## Информация о проекте

### Информация по тестам

#### Проверка перехода на страницу авторизации

1. Перейти на страницу http://automationpractice.com/
2. Нажать кнопку Sign in
3. Проверить что отобразилась страница "Sign in" 
---
#### Проверка регистрации пользователей

##### Проверка регистрации пользователей с валидными данными

Тестовые данные (генерируется несколько наборов данных перед тестом):
- Account.email
- Account.fields

1. Перейти на страницу http://automationpractice.com/
2. Нажать кнопку Sign in
3. Ввести {Account.email} из тестовых данных в форму Create an account
4. Нажать кнопку Registration
5. Заполнить все обязательные поля формы регистрации тестовыми данными {Account.fields}
6. Нажать кнопку Register
7. Проверить что отобразилась страница "My account"

##### Проверка отображения ошибки регистрации при пропуске обязательных полей

Тестовые данные (генерируется несколько наборов данных перед тестом):
- Account.email
- Account.fields
- Account.emptyFields
- Error.text

1. Перейти на страницу http://automationpractice.com/
2. Нажать кнопку Sign in
3. Ввести {Account.email} из тестовых данных в форму Create an account
4. Нажать кнопку Registration
5. Заполнить обязательные поля формы регистрации валидными данными {Account.fields} с пропуском полей {Account.emptyFields}
6. Нажать кнопку Register
7. Проверить что отобразилось предупреждение об ошибке
8. Проверить, что текст предупреждения об ошибке содержит {Error.text} из тестовых данных
---
#### Проверка добавления товаров в корзину

Тестовые данные (генерируется несколько наборов данных перед тестом):
- Account.email
- Account.password
- Product.name

1. Перейти на страницу http://automationpractice.com/
2. Нажать кнопку Sign in
3. Ввести {Account.email} и {Account.password} в форме Already registered
4. Нажать кнопку Sign in в форме Already registered
5. Нажать на категорию WOMEN
6. Навести курсор на товар {Product.name} и нажать кнопку Add to cart
7. Нажать на кпопку Continue shopping
8. Нажать на кнопку Cart
9. Проверить что товар {Product.name} есть в корзине
10. Проверить что количсетво товара {Product.name} равно 1
---
#### Проверка оформления заказа

Тестовые данные (генерируется несколько наборов данных перед тестом):
- Account.email
- Account.fields
- Product.names

1. Перейти на страницу http://automationpractice.com/
2. Нажать кнопку Sign in
3. Ввести {Account.email} из тестовых данных в форму Create an account
4. Нажать кнопку Registration
5. Заполнить все обязательные поля формы регистрации тестовыми данными {Account.fields}
6. Нажать кнопку Register
7. Нажать на категорию WOMEN
8. Добавить товары {Product.names} в корзину
9. Нажать на кнопку Cart
10. Проверить что текущая стадится оформления заказа: SUMMARY
11. Нажать кнопку Proceed to checkout
12. Проверить что текущая стадится оформления заказа: ADDRESS
13. Нажать кнопку Proceed to checkout
14. Проверить что текущая стадится оформления заказа: SHIPPING
15. Отметить чекбокс Agree terms of service
16. Нажать кнопку Proceed to checkout
17. Проверить что текущая стадится оформления заказа: PAYMENT
18. Нажать на кнопку выбора метода оплаты Pay by check
19. Проверить что отображается корректный текст по выбранному методу оплаты: CHECK PAYMENT
20. Нажать кнопку Confirm Order
21. Проверить что отобразилось сообщение об успешном оформлении заказа

### Используемые библиотеки

- testng
- selenium-java
- webdrivermanager
- allure-testng
- hamcrest
- name-machine
- javafaker

### Используемые плагины

- maven-surefire-plugin
- allure-maven

## Автор

  - **Андрей Тихомиров** - <andrey.tikhomirov.88@gmail.com>
