package com.nadir.apiprodutos.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadir.apiprodutos.components.ProdutoComponent
import com.nadir.apiprodutos.entities.Produto
import com.nadir.apiprodutos.exceptions.NotFoundException
import com.nadir.apiprodutos.integration.feign.client.UsuarioClient
import com.nadir.apiprodutos.repositories.ProdutoRepository
import com.nadir.apiprodutos.requests.EstoqueRequest
import com.nadir.apiprodutos.requests.ProdutoRequest
import com.nadir.apiprodutos.services.ProdutoService
import com.nadir.apiprodutos.util.AbstractTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import java.math.BigDecimal
import javax.validation.Valid


private const val AUTHORIZATIONHEADER: String = "Bearer eh..."

@ExtendWith(MockitoExtension::class)
class ProdutoControllerTest(): AbstractTest() {

   // @Autowired
    //private lateinit var produtoRepository: ProdutoRepository


    private lateinit var produtoAtivo: Produto
    private lateinit var produtoInativo: Produto
    private var id: Long = 0
    private lateinit var produtoLista: List<Produto>
    private lateinit var produtoRequest: ProdutoRequest

    @MockBean
    private lateinit var usuarioClient: UsuarioClient

    @MockBean
    private lateinit var produtoService: ProdutoService

    @SpyBean
    private lateinit var produtoController: ProdutoController

    @Captor
    private lateinit var captor: ArgumentCaptor<Long>

    @BeforeEach
    fun setup() {
        produtoAtivo = ProdutoComponent.createActiveProdutoEntity()
        produtoInativo = ProdutoComponent.createInactiveProdutoEntity()
        produtoLista = listOf(
            produtoAtivo, produtoInativo
        )
        produtoRequest = ProdutoComponent.createProdutoRequest()
        //produtoRepository.saveAll(produtoLista)
        //produtoRepository.flush()

        `when`(usuarioClient.validaToken(AUTHORIZATIONHEADER)).thenReturn(1L )

    }

    @AfterEach
    fun apagaDB() {
        //produtoRepository.deleteAll()
    }

    @Test
    fun `quando getAll produtos do DB`() {
        `when`(produtoService.findAll()).thenReturn(produtoLista)

        this.mockMvc.perform(
            get("/api/v1/produtos")
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATIONHEADER)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].id").value(produtoAtivo.id))

        `when`(usuarioClient.validaToken(AUTHORIZATIONHEADER)).thenReturn(10L);
        assertEquals(10L, usuarioClient.validaToken(AUTHORIZATIONHEADER))

        verify(produtoController).getAll(AUTHORIZATIONHEADER)

        var ordem: InOrder = inOrder(produtoController)
        ordem.verify(produtoController).getAll(AUTHORIZATIONHEADER)

        verify(produtoController, times(1)).getAll(ArgumentMatchers.anyString())
    }

    @Test
    fun `quando nao ha interacoes na Controller`() {
        verifyNoInteractions(produtoController)
    }

    @Test
    fun `quando se busca um produto por id`() {
        `when`(produtoService.findById(1)).thenReturn(produtoAtivo)

        this.mockMvc.perform(
            get("/api/v1/produtos/{id}", 1L)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATIONHEADER)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.id").value(produtoAtivo.id))

        verify(produtoController, times(1)).getById(AUTHORIZATIONHEADER, 1L)

        verify(produtoService).findById(captor.capture())
        val idCapturado = captor.value
        assertEquals(1, idCapturado)
    }

    @Test
    fun `quando cria um produto retorna um produto criado, com id`() {
        `when`(produtoService.save(produtoRequest.toProdutoEntity(null))).thenReturn(
            produtoRequest.toProdutoEntity(null).also { it.id = 100L}
        )

        this.mockMvc.perform(
            post("/api/v1/produtos")
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATIONHEADER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(produtoRequest)))
                .andDo(print())
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.descricao").value(produtoRequest.descricao))
    }

    @Test
    fun `quando verifica estoque e este existe deve retornar true`() {
        val estoqueRequest = EstoqueRequest(produtoAtivo.id!!, BigDecimal.TEN)
        produtoAtivo.quantidade = BigDecimal.TEN
        `when`(produtoService.findById(estoqueRequest.idProduto)).thenReturn(produtoAtivo)

        this.mockMvc.perform(
            post("/api/v1/produtos/verificaestoque")
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATIONHEADER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(estoqueRequest)))
                .andDo(print())
                .andExpect(jsonPath("$").value(true))
    }
    
    @Test
    fun `quando verifica estoque e este não existe deve retornar false`() {
        val estoqueRequest = EstoqueRequest(produtoAtivo.id!!, BigDecimal.TEN)
        produtoAtivo.quantidade = BigDecimal.ONE
        `when`(produtoService.findById(estoqueRequest.idProduto)).thenReturn(produtoAtivo)

        this.mockMvc.perform(
            post("/api/v1/produtos/verificaestoque")
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATIONHEADER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(estoqueRequest)))
            .andDo(print())
            .andExpect(jsonPath("$").value(false))
    }
}