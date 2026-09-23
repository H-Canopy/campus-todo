package edu.hbuas.campustodo.model;

import java.util.Objects;

/**
 * 校园待办任务。
 */
public class Task {
    private final long id;
    private final String title;
    private boolean completed;
  
    private final Priority priority;

 
    public Task(long id, String title) {
        if (id <= 0) {
            throw new IllegalArgumentException("任务编号必须为正数");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        this.id = id;
        this.title = title.trim();
        this.completed = false;
        this.priority = Priority.MEDIUM;
    }

    // 新增：带优先级的构造方法
    public Task(long id, String title, Priority priority) {
        if (id <= 0) {
            throw new IllegalArgumentException("任务编号必须为正数");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        if (priority == null) {
            throw new IllegalArgumentException("优先级不能为空");
        }
        this.id = id;
        this.title = title.trim();
        this.completed = false;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        completed = true;
    }

    // 新增：获取优先级
    public Priority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task task)) {
            return false;
        }
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
