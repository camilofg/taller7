# MyMonkey
-  [Introducción](#introducción)
-  [Instalación](#instalación)
-  [Uso Basico](#uso_basico)
-  [Uso Avanzado](#uso_avanzado)
    -   [Parametros](#parametros)
-  [Autor](#autor)

## Introducción
Programa para generar Eventos aleatorios en dispositivos Android. Los eventos se ejecutan Con ADB Shell input y Telnet. Para la ejecucion de los programas se debe tener un emulador en ejecución.

## Instalación
Se debe validar que java se encuentre instalado con el siguiente comando en la terminal o en el cmdÑ

        java -version
    
En caso de no tener java instalado, dirigirse a la siguiente pagina y seguir las instrucciones para su instalación:

    https://www.java.com/es/download/help/windows_manual_download.xml

El siguiente paso sera abrir la consola en la ubicacion donde se encuentra ubicado el archivo MyMonkey.java y ejecutar la siguiente instrucción:

        javac MyMonkey.java

## Uso Basico
El programa puede ser llamado sin asignarle parametros de ejecución, en esta forma ejecutara por efecto 10 eventos, ejecutara todos los eventos (Tap, Text, Swipe, Rotate, Keyevent, Network Speed, Sensor Set). Todos estos eventos se ejecutaran en el dispositivo sin ejecutarse sobre alguna aplicación especifica. El programa se podra ejecutar con la siguiente instrucciónÑ

        java MyMonkey

## Uso Avanzado
El programa tiene parametros de configuración que permiten determinar que eventos van a ser ejecutados, la cantidad de eventos a ejecutar, la aplicacion sobre la cual se ejecutaran y la distribucion de probabilidad de ejecucion de los eventos seleccionados. Con el siguiente ejemplo se ejecuta el programa configurando todos los parametros
dme
        java MyMonkey distribution=3,3,4 selectedEvents=tap,swipe,text events=20 package=me.kuhele.carreport installer=C:\Users\rcfor\OneDrive\Documents\GitHub\MyMonkey\Moneky_runner_adhoc\me.kuehle.carreport_62.apk

### Parametros
A continuacion se describen los parametros de configuración, cabe resaltar que los eventos son opcionales, los unicos dependientes son package e installer:

- distribution: Configura la distribucion de probabilidades de ejecución de los eventos, la sumatoria de estos valores no puede ser superior a 10.

- selectedEvents: Configura los eventos a ejecutarse.

- package & installer: Estos dos parametros sirven para seleccionar la aplicacion sobre la cual se van a ejecutar los eventos.
 
 - events: Configura la cantidad de eventos a ejecutar.


## Author
* **Camilo Forero**