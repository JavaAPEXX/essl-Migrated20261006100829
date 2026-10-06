```java
package com.example.project.service;

import com.example.project.model.AttendanceReportLog;
import com.example.project.repository.AttendanceReportLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceReportLogServiceTest {

    @Mock
    private AttendanceReportLogRepository repository;

    @InjectMocks
    private AttendanceReportLogService service;

    @Test
    @DisplayName("Given a valid log, when saving, then return the saved log")
    void givenValidLog_whenSave_thenReturnSavedLog() {
        // Arrange
        AttendanceReportLog inputLog = new AttendanceReportLog();
        AttendanceReportLog savedLog = new AttendanceReportLog();
        when(repository.save(inputLog)).thenReturn(savedLog);

        // Act
        AttendanceReportLog result = service.save(inputLog);

        // Assert
        assertNotNull(result);
        assertSame(savedLog, result);
        verify(repository, times(1)).save(inputLog);
    }

    @Test
    @DisplayName("Given a null log, when saving, then return null")
    void givenNullLog_whenSave_thenReturnNull() {
        // Arrange
        when(repository.save(null)).thenReturn(null);

        // Act
        AttendanceReportLog result = service.save(null);

        // Assert
        assertNull(result);
        verify(repository, times(1)).save(null);
    }

    @Test
    @DisplayName("Given a repository exception, when saving, then throw the exception")
    void givenRepositoryException_whenSave_thenThrowException() {
        // Arrange
        AttendanceReportLog inputLog = new AttendanceReportLog();
        RuntimeException expectedException = new RuntimeException("Database error");
        when(repository.save(inputLog)).thenThrow(expectedException);

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.save(inputLog);
        });

        assertSame(expectedException, thrown);
        verify(repository, times(1)).save(inputLog);
    }

    @Test
    @DisplayName("Given existing logs, when finding all, then return the list of logs")
    void givenExistingLogs_whenFindAll_thenReturnListOfLogs() {
        // Arrange
        AttendanceReportLog log1 = new AttendanceReportLog();
        AttendanceReportLog log2 = new AttendanceReportLog();
        List<AttendanceReportLog> expectedLogs = Arrays.asList(log1, log2);
        when(repository.findAll()).thenReturn(expectedLogs);

        // Act
        List<AttendanceReportLog> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(expectedLogs, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given no logs, when finding all, then return an empty list")
    void givenNoLogs_whenFindAll_thenReturnEmptyList() {
        // Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<AttendanceReportLog> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given null return from repository, when finding all, then return null")
    void givenNullReturnFromRepository