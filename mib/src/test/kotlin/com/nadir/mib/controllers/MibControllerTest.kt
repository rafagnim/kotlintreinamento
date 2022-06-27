package com.nadir.mib.controllers

import com.nadir.mib.AbstractTest
import com.nadir.mib.components.ProdutoComponent
import com.nadir.mib.integration.feign.client.CompraClient
import com.nadir.mib.integration.feign.client.ProdutoClient
import com.nadir.mib.integration.feign.client.UsuarioClient
import com.nadir.mib.models.Produto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MibControllerTest: AbstractTest() {

//    @InjectMocks
//    private lateinit var mibController: MibController
    @Mock
    private lateinit var produtoClient: ProdutoClient
//    private lateinit var usuarioClient: UsuarioClient
//    private lateinit var compraClient: CompraClient

//    private lateinit var produto: Produto
    private lateinit var produtoList: List<Produto>
    private val authorizationHeader = "Bearer efg..."

    @BeforeEach
    fun setup()
    {
        produtoList = listOf(
            ProdutoComponent.createProduto(),
            ProdutoComponent.createProduto(),
            ProdutoComponent.createProduto(),
            ProdutoComponent.createProduto()
        )
    }

    @Test
    fun `when get all products from feign client should return a list`() {
        val lista: List<Produto>?
        `when`(produtoClient.retrieveProdutos(authorizationHeader)).thenReturn(produtoList)
        lista = produtoClient.retrieveProdutos(authorizationHeader)
        assertEquals(produtoList, lista)
        assertEquals(produtoList.size, lista.size)
    }
}