package com.exemplo.empresa.loja.service;

import com.exemplo.empresa.loja.model.Produto;
import com.exemplo.empresa.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map; // Para Map
import java.lang.reflect.Field; // Para manipulação de campos via reflexão
import org.springframework.util.ReflectionUtils; // Utilitário do Spring para reflexão


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service//Indica que essa classe é um componente de serviço do Spring.
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
/*
listarTodos()
Descrição: Recupera todos os produtos do banco de dados.
Implementação:
Chama o método findAll() do ProdutoRepository, que retorna uma lista de todos os registros na tabela produto.
Retorno: Uma lista de objetos da classe Produto.
Usado por: O método listarTodos() no ProdutoController. 
*/
    public List<Produto> listarTodos() {
        return repository.findAll();
    }
/*
salvar(Produto produto)
Descrição: Salva ou atualiza um produto no banco de dados.
Implementação:
Chama o método save() do ProdutoRepository, que salva um novo registro ou atualiza um registro existente (se o ID já estiver presente).
Se o objeto não tiver um ID, um novo será gerado automaticamente.
Parâmetro:
Um objeto da classe Produto contendo os dados a serem salvos.
Retorno: O objeto salvo, incluindo o ID gerado pelo banco de dados.
Usado por: O método criar() no ProdutoController.
 */
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }
/*
 deletar(Long id)
Descrição: Remove um produto do banco de dados com base no seu ID.
Implementação:
Chama o método deleteById() do ProdutoRepository, que exclui o registro correspondente ao ID fornecido.
Caso o ID não exista, o Spring lançará uma exceção.
Parâmetro:
O ID do produto que será excluído.
Retorno: Nenhum. O método é void.
Usado por: O método deletar() no ProdutoController.
 */
    public void deletar(Long id) {
        repository.deleteById(id);
    }
//Para atualizar o recurso inteiro.
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
    
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
    
        return repository.save(produtoExistente);
    }
   // Para atualizar apenas campos específicos.
   public Produto atualizarParcial(Long id, Map<String, Object> campos) {
    Produto produtoExistente = repository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

    // Atualiza apenas os campos fornecidos no Map
    campos.forEach((campo, valor) -> {
        Field field = ReflectionUtils.findField(Produto.class, campo);
        if (field != null) {
            field.setAccessible(true);
            ReflectionUtils.setField(field, produtoExistente, valor);
        }
    });

    return repository.save(produtoExistente);
}


}
