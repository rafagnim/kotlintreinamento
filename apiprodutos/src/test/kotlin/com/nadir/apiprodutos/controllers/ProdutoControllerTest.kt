package com.nadir.apiprodutos.controllers

import com.nadir.apiprodutos.components.ProdutoComponent
import com.nadir.apiprodutos.entities.Produto
import com.nadir.apiprodutos.integration.feign.client.UsuarioClient
import com.nadir.apiprodutos.repositories.ProdutoRepository
import com.nadir.apiprodutos.util.AbstractTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.context.WebApplicationContext

class ProdutoControllerTest: AbstractTest() {

    @Autowired
    private lateinit var produtoRepository: ProdutoRepository


    private lateinit var produtoAtivo: Produto
    private lateinit var produtoInativo: Produto
    private var id: Long = 0
    private lateinit var produtoLista: List<Produto>

    @Mock
    private lateinit var usuarioClient: UsuarioClient

    @BeforeEach
    fun setup() {
        produtoAtivo = ProdutoComponent.createActiveProdutoEntity()
        produtoInativo = ProdutoComponent.createInactiveProdutoEntity()
        produtoLista = listOf(
            produtoAtivo, produtoInativo
        )
        produtoRepository.saveAll(produtoLista)
        produtoRepository.flush()

    }

    @AfterEach
    fun apagaDB() {
        produtoRepository.deleteAll()
    }

    @Test
    fun `quando getAll produtos do DB`() {

//        MockHttpServletResponse:
//        Status = 400
//        Error message = Required request header 'Authorization' for method parameter type String is not present
//        Headers = []
//        Content type = null
//        Body =
//            Forwarded URL = null
//        Redirected URL = null
//        Cookies = []

        val authorizationHeader: String = "Bearer eh..."
        //`when`(usuarioClient.validaToken(authorizationHeader)).thenReturn(1L )
        //val clientId = usuarioClient.validaToken(authorizationHeader)

        this.mockMvc.perform(
            get("/api/v1/produtos")
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            //.andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id))
    }
}