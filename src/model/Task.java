package model;

import  java.util.Objects;
/**
 * Задача
 * У задачи есть следующие свойства:
 * Название, кратко описывающее суть задачи (например, «Переезд»).
 * Описание, в котором раскрываются детали.
 * Уникальный идентификационный номер задачи, по которому её можно будет найти.
 * Статус, отображающий её прогресс. Мы будем выделять следующие этапы жизни задачи:
 * NEW — задача только создана, но к её выполнению ещё не приступили.
 * IN_PROGRESS — над задачей ведётся работа.
 * DONE — задача выполнена.
 */

public class Task {
    private int id;
    private String name;
    private String description;
    private Status status;

    public Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status);
    }

    @Override
    public String toString() {
        return "\n"+ "model.Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }


}