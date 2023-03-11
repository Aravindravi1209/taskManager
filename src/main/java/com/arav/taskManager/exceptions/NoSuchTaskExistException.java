package com.arav.taskManager.exceptions;

public class NoSuchTaskExistException extends IllegalArgumentException {
    public NoSuchTaskExistException(Long id) {
        super("No such task exist with id: " + id);
    }
}
