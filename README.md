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
* [Barista](https://github.com/AdevintaSpain/Barista) - ver TODO
* [Mockk](https://github.com/mockk/mockk)

##### **TODO**
- Incluir test cases de UI:
    - Botão de simular habilitado depois de todos os campos preenchidos;
    - Considerando botão de simular habilitado: botão de simular desabilitado se algum campo estiver vazio;
    - Considerando sucesso ao chamar o endpoint de criar simulação: Botão de simular abre a tela de detalhes da simulação;
    - Botão de "Simular novamente" abre a tela de criação de simulação.


- Incluir unit tests para o ViewModel de criação de simulação;

- Incluir animação de slide na transição entre telas de criação de simulação e detalhes de simulação;

- Suportar input nas casas decimais no campo de valor do investimento.

- Bloquear botão de "Simular" caso um dos campos tenha um erro;

- Extrair e refatorar lógica de observação dos EditTexts no CreateSimulationViewModel para classes separadas (métodos muito longos e complexos);

- Adicionar efeito ripple nos botões;

- Corrigir labels dos botões aparecendo com todas as letras maiúsculas

- Extrair lógica de resultado da simulação (`com.renanlukas.feature.simulator.presentation.overview`) para um módulo separado;

- Extrair Views customizadas (`com.renanlukas.investmentsimulator.presentation`) para um módulo separado;
