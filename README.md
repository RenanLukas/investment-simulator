# Simulador de Investimento

Implementação do aplicativo proposto [neste repositório](https://github.com/easynvest/teste-android).

![Alt text](create_simulation.png?raw=true)

## Módulos
**app**: Activity única e navegação.  
**feature_core**:  Componentes e dependências comuns aos módulos de feature.   
**feature_simulator**:  Feature de simulação de investimento.

![Alt text](module_dependency.png?raw=true)

## Principais dependências
* [Kotlin](https://kotlinlang.org/docs/reference/using-gradle.html)
* [Dagger](https://github.com/google/dagger)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Retrofit](https://square.github.io/retrofit/)
* [OkHttp](https://square.github.io/okhttp/)
* [Material Components for Android](https://material.io/develop/android/docs/getting-started/)
* [Barista](https://github.com/AdevintaSpain/Barista)
* [Mockk](https://github.com/mockk/mockk)

##### **TODO**
- Extrair lógica de resultado da simulação (`com.renanlukas.feature.simulator.presentation.overview`) para um módulo separado.

- Extrair Views customizadas (`com.renanlukas.investmentsimulator.presentation`) para um módulo separado.
