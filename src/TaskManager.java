import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private static int id = 0;// уникальный нумератор
    private static HashMap<Integer, Task> tasks = new HashMap<>(); // Список задач
    private static HashMap<Integer, Epic> epics = new HashMap<>();// Список эпиков

    /**
     * Получение списка всех подзадач
     * @return
     */
    public static ArrayList<Subtask> getSubtasksList(){
        ArrayList <Subtask> subtasks = new ArrayList<>();
        // добавляем все подзадачи из всех эпиков
        for (Map.Entry entry:epics.entrySet()){
            Epic epic= (Epic) entry.getValue();
            subtasks.addAll(epic.getSubtasks());
        }
        return  subtasks;
    }

    /**
     * Удаляет все подзадачи
     */
    public static void deleteAllSubtasks(){
        for (Map.Entry entry:epics.entrySet()){
            Epic epic= (Epic) entry.getValue();
            epic.deleteSubtasks();
        }
    }

    /**
     * Добавляет новую подзадачу
     * @param subtask
     * @return
     */
    public static Subtask addNewSubtask(Subtask subtask) {
        subtask.setId(++id);
        subtask.setStatus(Status.NEW);
        Epic epic = getEpicByID(subtask.getEpicID());
        ArrayList<Subtask> subtasks = getSubtasksByEpic(epic);
        subtasks.add(subtask);
        epic.setSubtasks(subtasks);
        updateEpicStatus(epic);
        return subtask;

    }

    /**
     * Обновление статус подзадачи
     *
     * @param subtask
     * @param status
     */
    public static void updateSubtaskStatus(Subtask subtask, Status status) {
        subtask.setStatus(status);
        updateEpicStatus(getEpicByID(subtask.getEpicID()));
    }


    /**
     * Добавляет новый эпик
     *
     * @param epic
     * @return epic
     */
    public static Epic addNewEpic(Epic epic) {
        epic.setId(++id);
        epics.put(epic.getId(), epic);
        // updateEpicStatus(epic);
        return epic;
    }

    /**
     * Возвращает список всех эпиков
     * @return epics
     */
    public static ArrayList<Epic> getEpicsList() {
        return new ArrayList<>(epics.values());
    }

    /**
     * Возвращает эпик по идентификатору
     * @param epicID
     * @return epic
     */
    public static Epic getEpicByID(int epicID){
    Epic epic=epics.get(epicID);
           return  epic;
    }

    /**
     * Возвращает подзадачи по эпику
     * @param epic
     * @return
     */
    public static ArrayList<Subtask> getSubtasksByEpic(Epic epic){
        return  new ArrayList<Subtask>(epic.getSubtasks());
    }

    /**
     * Возвращает подзадачи по ID эпика
     * @param epicID
     * @return
     */
    public static ArrayList<Subtask> getSubtasksByEpicID(int epicID){
        Epic epic=getEpicByID(epicID);

        if (epic!=null){
            return epic.getSubtasks();
        }else{
            return new ArrayList<Subtask>();
        }
    }

    /**
     * Обновление эпика
     * @param epic
     */
    public static void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            return;
        }
        epics.put(epic.getId(), epic);
    }

    /**
     * Удаление эпика по идентификатору
     * @param epicID
     */
    public static void deleteEpicByID(int epicID) {
        Epic epic=epics.get(epicID);
        if (epic != null) {
            epics.remove(epicID);
        }
    }

    /**
     * выполняет расчет статуса эпика в зависимости от статусов подзадач
     * @param epic
     */
    public static void updateEpicStatus(Epic epic) {
        /**
         если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
         если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
         во всех остальных случаях статус должен быть IN_PROGRESS.
         */

        boolean hasNew = false;
        boolean hasInProgress = false;
        boolean hasDone = false;
        Status status = null;

        ArrayList<Subtask> subtasks = getSubtasksByEpic(epic);

        if (subtasks == null) {
            epic.setStatus(Status.NEW);
            return;
        }
        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == Status.NEW) {
                hasNew = true;
            }
            if (subtask.getStatus() == Status.DONE) {
                hasDone = true;
            }
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                hasInProgress = true;
            }
        }

        if (hasDone == true & hasNew == false & hasInProgress == false) {
            status = Status.DONE;
        } else if (hasNew == true & hasDone == false & hasInProgress == false) {
            status = Status.NEW;
        } else {
            status = Status.IN_PROGRESS;
        }
        epic.setStatus(status);
    }

    /**
     * Возвращает список всех задач
     *
     * @return tasks
     */
    public static ArrayList<Task> getTasksList() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * Удаляет все задачи
     */
    public static void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * возвращает задачу по указанному ID
     *
     * @param taskID
     * @return Task
     */
    public static Task getTaskByID(int taskID) {
        Task task = tasks.get(taskID);
        return task;
    }

    /**
     * Добавляет новую задачу
     * @param task
     * @return task
     */
     public static Task addNewTask(Task task) {
        task.setId(++id);
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    /**
     * Удаляет задачу по указанному ID
     *
     * @param taskID
     */
    public static void deleteTaskByID(int taskID) {
        Task task = tasks.get(id);
        if (task != null) {
            tasks.remove(taskID);
        }
    }

    /**
     * Обновление задачи
     *
     * @param task
     */
    public static void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            return;
        }

        tasks.put(task.getId(), task);
    }


    /**
     * Обновление статус задачи
     *
     * @param task
     * @param status
     */
    public static void updateTaskStatus(Task task, Status status) {
        task.setStatus(status);
    }

}
