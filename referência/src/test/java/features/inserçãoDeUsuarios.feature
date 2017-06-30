#language: pt
Funcionalidade: Cadastrar o usuário

  Narrativa: Eu como administrador do sistema posso cadastrar um usuário.

  #@DatabaseSetup
  @DatabaseSetup("@inserçãoDeUsuarios.xml")
  Cenário: Eu que sou administrador insiro o usuário Assis Júnior
    Dado que eu tenha o nome do usuário como "Assis Júnior" e o email como "assisjrs@gmail.com"
    Quando eu insiro o usuário
    Então Deve exibir 2 usuários na lista
