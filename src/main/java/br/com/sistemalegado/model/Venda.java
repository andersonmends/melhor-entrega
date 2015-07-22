/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemalegado.model;

/**
 *
 * @author Anderson Mendes
 */
public class Venda {
    
    private long id;
    private String CPFCliente;
    private String CPFVendedor;
    private int valor;
    private String produto;
    private String nome;

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPFCliente() {
        return CPFCliente;
    }

    public void setCPFCliente(String CPFCliente) {
        this.CPFCliente = CPFCliente;
    }

    public String getCPFVendedor() {
        return CPFVendedor;
    }

    public void setCPFVendedor(String CPFVendedor) {
        this.CPFVendedor = CPFVendedor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
