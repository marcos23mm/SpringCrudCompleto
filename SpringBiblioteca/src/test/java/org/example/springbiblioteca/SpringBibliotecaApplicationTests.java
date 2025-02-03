package org.example.springbiblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootTest
@EnableCaching //Para que la cache funcione hay que poner esto en el main
class SpringBibliotecaApplicationTests {

    @Test
    void contextLoads() {
    }

}
