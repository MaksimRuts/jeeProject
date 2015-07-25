function sendForm(ref) {
    document.taskForm.action.value = ref;
    document.taskForm.submit();
}

function setTaskId(id) {
    document.getElementById('taskId').value = id;
}

function sendFormWithTaskId(ref, id) {
    setTaskId(id);
    sendForm(ref);
}