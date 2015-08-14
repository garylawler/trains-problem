package com.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class AppConfigTest {

    @Test
    public void verifySpringConfigISCorrect() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        assertThat(context.isActive(), is(true));
    }
}
