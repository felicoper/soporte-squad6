[![Java CI](https://github.com/felicoper/soporte-squad6/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/felicoper/soporte-squad6/actions/workflows/gradle.yml)
![Squad](https://img.shields.io/badge/Squad-6-red)
![Materia e Institución](https://img.shields.io/badge/An%C3%A1lisis%20de%20la%20Informaci%C3%B3n-FIUBA-blue)


# PSA: Módulo de Soporte 

Backend para el Módulo de Soporte del Sistema de Gestión PSA, desarrollado por el **Squad 6** en la materia de **Análisis de la Información (75.09)** dictada en la **Facultad de Ingeniería de la Universidad de Buenos Aires (FIUBA)** durante el segundo cuatrimestre del 2021.

## Requisitos

* Java 11
* Gradle 7.2

## Tests

Para la ejecución de los tests realizados gracias a cucumber para los criterios de aceptación obtenidos mediante las historias de usuario, se debe ejecutar:

    ./gradlew cucumber

Github Action luego de cada PR hacia la rama main ejecuta dichos tests con el comando nombrado.

## Ejecución

Para arrancar el servidor localmente

    ./gradlew bootRun
