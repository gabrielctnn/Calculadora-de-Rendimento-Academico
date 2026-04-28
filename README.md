# 📱 Calculadora de Rendimento Académico (UNIP)

Este projeto foi desenvolvido como parte da disciplina de Desenvolvimento de Dispositivos Móveis no curso de Ciência da Computação (UNIP). O objetivo principal é automatizar o cálculo das notas dos alunos seguindo o regimento da instituição.

## 🎯 Objetivo da Atividade
O desafio consistia em criar um aplicativo Android funcional que validasse a situação do aluno em tempo real, gerindo dinamicamente a interface e garantindo a estabilidade do sistema contra erros de input.

### Lógica de Aprovação
- **Aprovação Direta:** Média (N1 + N2) / 2 ≥ 7.0.
- **Exame:** Média < 7.0 (libera entrada para nota de exame).
- **Média Final:** (Média Semestral + Exame) / 2 ≥ 5.0 para aprovação após exame.
- **Reprovação por Falta:** Frequência < 75% resulta em reprovação imediata (vermelho), independentemente das notas.


## 💻 Tecnologias
- **Linguagem:** Java
- **Ambiente:** Android Studio
- **Layout:** XML (TableLayout para organização dos dados)
