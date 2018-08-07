package net.kemitix.naolo.run.spring;

import org.assertj.core.api.WithAssertions;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static java.lang.String.format;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Tests.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                Main.class
        })
@AutoConfigureMockMvc
class RunIT implements WithAssertions {

    @Autowired
    MockMvc mvc;

    @Test
    void contextStarts() {
        assertThat(true).isTrue();
    }

    @Test
    void getAllVets() throws Exception {
        mvc.perform(get("/vets/"))
                .andExpect(status().isOk())
                .andExpect(content().string(new AssertionMatcher<String>() {
                    @Override
                    public void assertion(String actual) throws AssertionError {
                        assertSoftly(s -> IntStream.rangeClosed(1, 10).forEach(i ->
                                s.assertThat(actual).contains(format("{\"id\":%d,", i))));
                    }
                }));
    }

}
