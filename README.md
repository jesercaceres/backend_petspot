### Enunciado do Cenário

Imagine que você é um dono de animal de estimação que deseja centralizar e manter a saúde e bem-estar do seu pet. Para facilitar esse processo, a plataforma **Animal Spot** foi desenvolvida, visando simplificar e unificar o acesso a todas as informações essenciais do seu animal de estimação.

Considere um usuário que deseja registrar-se na plataforma para gerenciar as informações de saúde do seu pet. Se o usuário fornecer todas as informações necessárias corretamente, o sistema criará uma nova conta e permitirá o acesso às funcionalidades da plataforma. No entanto, se o usuário tentar registrar-se com um e-mail já cadastrado, fornecer senhas que não coincidem ou não atendam as validações necessárias. O sistema não permitirá o avanço para as próximas etapas e instruirá o usuário para preencher os dados conforme as regras do negócio.

Além disso, a plataforma permite que os usuários:

- Cadastrem-se
- Realizem Login
- Registrem seus pets.
- Cadastre as informações vitais sobre o pet
- Organizem os dados de forma acessível e centralizada.
- Atualizem as informações conforme necessário.

Em situações de emergência, veterinários podem acessar rapidamente o histórico completo do animal, independentemente da clínica, proporcionando cuidados mais eficientes e transparentes.

## Gherkin do Cenário

```gherkin
Feature: Sistema de Registro e Gerenciamento de Pets

  Scenario: Registro de novo usuário com sucesso
    Given que o usuário deseja se registrar no sistema
    And que o usuário fornece um email válido, senha, nome, sobrenome, data de nascimento, país e telefone
    When o usuário confirma o registro
    Then o sistema deve criar uma nova conta de usuário
    And o sistema deve retornar o email do usuário registrado

  Scenario: Registro de usuário com email duplicado
    Given que o usuário deseja se registrar no sistema
    And que o usuário fornece um email já cadastrado
    When o usuário confirma o registro
    Then o sistema deve retornar uma mensagem de erro indicando que o email já está cadastrado

  Scenario: Registro de usuário com senhas não coincidentes
    Given que o usuário deseja se registrar no sistema
    And que o usuário fornece senhas que não coincidem
    When o usuário confirma o registro
    Then o sistema deve retornar uma mensagem de erro indicando que as senhas não coincidem

  Scenario: Registro de usuário com senha menor que 8 caracteres
    Given que o usuário deseja se registrar no sistema
    And que o usuário fornece uma senha com menos de 8 caracteres
    When o usuário confirma o registro
    Then o sistema deve retornar uma mensagem de erro indicando que a senha deve conter no mínimo 8 caracteres

  Scenario: Registro de usuário com campos obrigatórios não preenchidos
    Given que o usuário deseja se registrar no sistema
    And que o usuário não preenche todos os campos obrigatórios
    When o usuário confirma o registro
    Then o sistema deve retornar uma mensagem de erro indicando que todos os campos obrigatórios devem ser preenchidos

  Scenario: Login com sucesso
    Given que o usuário possui uma conta registrada
    And que o usuário fornece um email e senha válidos
    When o usuário confirma o login
    Then o sistema deve autenticar o usuário
    And o sistema deve retornar o ID do tutor

  Scenario: Login com credenciais inválidas
    Given que o usuário possui uma conta registrada
    And que o usuário fornece um email ou senha inválidos
    When o usuário confirma o login
    Then o sistema deve retornar uma mensagem de erro indicando credenciais inválidas

  Scenario: Registro de novo pet com sucesso
    Given que o usuário está autenticado
    And que o usuário fornece os dados do pet
    When o usuário confirma o registro do pet
    Then o sistema deve criar um novo registro de pet
    And o sistema deve retornar os dados do pet registrado

  Scenario: Registro de pet duplicado
    Given que o usuário está autenticado
    And que o usuário fornece os dados de um pet já registrado
    When o usuário confirma o registro do pet
    Then o sistema deve retornar uma mensagem de erro indicando que o pet já está registrado

  Scenario: Registro de pet com campos obrigatórios não preenchidos
    Given que o usuário está autenticado
    And que o usuário não preenche todos os campos obrigatórios do pet
    When o usuário confirma o registro do pet
    Then o sistema deve retornar uma mensagem de erro indicando que todos os campos obrigatórios devem ser preenchidos

  Scenario: Atualização do nome do pet com sucesso
    Given que o usuário está autenticado
    And que o usuário possui um pet registrado
    And que o usuário fornece um novo nome para o pet
    When o usuário confirma a atualização do nome do pet
    Then o sistema deve atualizar o nome do pet
    And o sistema deve retornar uma mensagem de sucesso

  Scenario: Atualização do peso do pet com sucesso
    Given que o usuário está autenticado
    And que o usuário possui um pet registrado
    And que o usuário fornece um novo peso para o pet
    When o usuário confirma a atualização do peso do pet
    Then o sistema deve atualizar o peso do pet
    And o sistema deve retornar uma mensagem de sucesso

  Scenario: Busca de pets por nome com sucesso
    Given que o usuário está autenticado
    And que o usuário possui pets registrados
    And que o usuário fornece um nome parcial de pet
    When o usuário realiza a busca pelo nome do pet
    Then o sistema deve retornar uma lista de pets que correspondem ao nome fornecido
