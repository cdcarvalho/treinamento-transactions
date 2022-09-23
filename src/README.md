## REQUIRED
 - É o padrão para o @Transactional
 - Usa a transação atual caso exista.
 - Cria uma nova se nenhuma existir. 

## SUPPORTS
- Usa a transação atual caso exista. 
- Execute não transacionalmente se nenhuma existir.

## MANDATORY
- Usa a transação atual caso exista.
- Lança uma exceção se nenhuma existir.

## REQUIRES_NEW
- Crie uma nova transação e suspenda a transação atual se existir.
- Lança uma exceção se nenhuma existir.

## NOT_SUPPORTED
- Execute não transacionalmente, suspenda a transação atual se existir.

## NEVER
- Execute não transacionalmente, lance uma exceção se existir uma transação.

## NESTED
- Execute dentro de uma transação aninhada se existir uma transação atual, se comporte como REQUIRED caso contrário.

## PONTOS PARA DISCUSSÃO
- Abordagem do uso do @Transactional
- Uso do REQUIRES_NEW de forma encadeada
- Chamda de métodos com o @Transactional na mesma classe 
- Uso do @Transactional nos métodos privados