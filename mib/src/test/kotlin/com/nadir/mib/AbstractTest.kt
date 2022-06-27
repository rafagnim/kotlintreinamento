package com.nadir.mib

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class AbstractTest {

    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var webAppContext: WebApplicationContext

    @BeforeEach
    fun init() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.webAppContext)
            .build()
    }
}