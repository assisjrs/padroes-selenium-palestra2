#language: pt
Funcionalidade: Listar os usuários

  Narrativa: Eu como usuario do sistema posso visualizar os usuários.

  Cenário: Eu que sou um usuário posso visualizar todos os outros usuários
    Dado que o sistema tenha o usuário "Francisco" cadastrado
    Quando eu visualizo a lista de usuários
    Então Devo reconhecer na lista o usuário "Francisco"

  Cenário: Eu que estou operando o sistema desejo saber se
  o email "<administrador@concrete.com.br>" encontra-se sempre cadastrado no banco.
    Dado que o sistema deve ter o administrador sempre cadastrado
    Quando eu consulto o banco de dados pelo email "administrador@concrete.com.br"
    Então Devo encontrar no o email associado ao usuário "Administrador"
