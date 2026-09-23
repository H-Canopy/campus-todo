package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务应用服务。学生将在功能分支中逐步扩展该类。
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public Task addTask(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }

    /**
     * 新增任务并指定优先级。
     *
     * @param title    任务标题，不能为空
     * @param priority 任务优先级，不能为 null
     * @return 新创建的任务
     */
    public Task addTask(String title, Priority priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.add(task);
        return task;
    }

    public List<Task> listAll() {
        return List.copyOf(tasks);
    }

    /**
     * 按优先级筛选任务，返回结果保持任务加入顺序。
     *
     * @param priority 要筛选的优先级，不允许为 null
     * @return 匹配该优先级的任务列表；无匹配时返回空列表，不会返回 null
     * @throws IllegalArgumentException 当 priority 为 null 时
     */
    public List<Task> filterByPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("优先级不能为空");
        }
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }
}
