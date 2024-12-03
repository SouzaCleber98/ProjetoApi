package com.exemplo.empresa.loja.controller;

import com.exemplo.empresa.loja.model.Produto;
import com.exemplo.empresa.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController//Indica que a classe é um controlador RESTful.
@RequestMapping("/api/produtos")//Define a URL base para todos os endpoints dessa classe. 
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping//Marca um método que responde a requisições HTTP do tipo GET.
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping//Marca um método que responde a requisições HTTP do tipo POST.
    public Produto criar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @DeleteMapping("/{id}")//Marca um método que responde a requisições HTTP do tipo DELETE.
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
    return service.atualizar(id, produtoAtualizado);
}

@PatchMapping("/{id}")
public Produto atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
    return service.atualizarParcial(id, campos);
}

}
