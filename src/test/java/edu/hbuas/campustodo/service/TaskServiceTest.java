package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();

        var task = service.addTask("完成需求评审");

        assertEquals(1L, task.getId());
        assertEquals("完成需求评审", task.getTitle());
        assertFalse(task.isCompleted());
        assertEquals(1, service.listAll().size());
    }

    @Test
    void shouldRejectBlankTitle() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("   "));
    }

    // ---- Issue #1：任务优先级与筛选 ----

    @Test
    void shouldDefaultPriorityToMedium() {
        TaskService service = new TaskService();

        var task = service.addTask("写实验报告");

        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void shouldFilterTasksByPriority() {
        TaskService service = new TaskService();
        var high = service.addTask("处理线上事故", Priority.HIGH);
        service.addTask("例行周会", Priority.MEDIUM);
        service.addTask("整理笔记", Priority.LOW);

        var highOnly = service.filterByPriority(Priority.HIGH);

        assertEquals(List.of(high), highOnly);
    }

    @Test
    void shouldFilterEachPriorityIndependently() {
        TaskService service = new TaskService();
        var high = service.addTask("处理线上事故", Priority.HIGH);
        var medium = service.addTask("例行周会", Priority.MEDIUM);
        var low = service.addTask("整理笔记", Priority.LOW);

        assertEquals(List.of(high), service.filterByPriority(Priority.HIGH));
        assertEquals(List.of(medium), service.filterByPriority(Priority.MEDIUM));
        assertEquals(List.of(low), service.filterByPriority(Priority.LOW));
    }

    @Test
    void shouldReturnEmptyListWhenNoMatch() {
        TaskService service = new TaskService();
        service.addTask("例行周会", Priority.MEDIUM);

        var highOnly = service.filterByPriority(Priority.HIGH);

        assertTrue(highOnly.isEmpty());
    }

    @Test
    void shouldRejectNullPriority() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class,
                () -> service.filterByPriority(null));
    }
}
