package com.example.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.Annotation;

@ExtendWith(MockitoExtension.class)
class ProjectApplicationTest {

    @Test
    @DisplayName("given valid arguments when main then SpringApplication.run is invoked with ProjectApplication class")
    void givenValidArgs_whenMain_thenSpringApplicationRunInvoked() {
        // Arrange
        String[] args = {"--spring.profiles.active=test"};
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            // Act
            ProjectApplication.main(args);

            // Assert
            mocked.verify(() -> SpringApplication.run(ProjectApplication.class, args), times(1));
        }
    }

    @Test
    @DisplayName("given null arguments when main then SpringApplication.run is invoked with null args")
    void givenNullArgs_whenMain_thenSpringApplicationRunInvoked() {
        // Arrange
        String[] args = null;
        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
            // Act
            ProjectApplication.main(args);

            // Assert
            mocked.verify(() -> SpringApplication.run(ProjectApplication.class, args), times(1));
        }
    }

    @Test
    @DisplayName("given ProjectApplication class when checking annotations then it is annotated with @SpringBootApplication and @EnableScheduling")
    void givenProjectApplicationClass_whenCheckAnnotations_thenHasSpringBootApplicationAndEnableScheduling() {
        // Arrange
        Class<ProjectApplication> clazz = ProjectApplication.class;

        // Act
        Annotation springBootAnnotation = clazz.getAnnotation(SpringBootApplication.class);
        Annotation enableSchedulingAnnotation = clazz.getAnnotation(EnableScheduling.class);

        // Assert
        assertNotNull(springBootAnnotation, "@SpringBootApplication annotation should be present");
        assertNotNull(enableSchedulingAnnotation, "@EnableScheduling annotation should be present");
    }
}